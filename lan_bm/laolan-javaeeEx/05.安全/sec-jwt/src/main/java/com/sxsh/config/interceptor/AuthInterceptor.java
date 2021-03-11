package com.sxsh.config.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.sxsh.annotation.Anoymous;
import com.sxsh.token.entity.UserLocal;
import com.sxsh.exception.CommonException;
import com.sxsh.exception.CommonExceptionEnum;
import com.sxsh.util.AuthLocalTokenUtil;
import com.sxsh.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Description 权限校验器
 * @Author lhy
 * @Date 2020/12/3
 * @Version 1.0.0
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 鉴权
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String reqURL = request.getRequestURL().toString();
        log.info("请求 URL：{}", reqURL);

        if(request.getMethod().toUpperCase().equals("OPTIONS")){
            return true;
        }

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //添加 @Anoymous 注解的接口不需要鉴权
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        if(isAnoymous(handlerMethod)){
            return true;
        }

        String token;
        try {
            token = request.getHeader("Authorization").split(" ")[1];
        } catch (Exception e) {
            throw new CommonException(CommonExceptionEnum.NOT_TOKEN_ERROR);
        }
        if (StringUtils.isEmpty(token)) {
            throw new CommonException(CommonExceptionEnum.NOT_AUTH_ERROR);
        }

        //解析token
        DecodedJWT jwt = jwtUtil.deToken(token);
        if (jwt == null) {
            throw new CommonException(CommonExceptionEnum.AUTH_ERROR);
        }
        Integer shopId = jwt.getClaim("shop_id").asInt();
        Integer shopUserId = jwt.getClaim("user_id").asInt();
        Integer spreadId = jwt.getClaim("spread_id").asInt();
        Integer platformUserId = jwt.getClaim("platform_user_id").asInt();
        Integer agentSid = null;
        if (jwt.getClaim("agent_sid") != null) {
            agentSid = jwt.getClaim("agent_sid").asInt();
        }
        //设置用户信息
        AuthLocalTokenUtil.setUser(new UserLocal().setShopId(shopId).setShopUserId(shopUserId).setSpreadId(spreadId).setAgentSid(agentSid).setPlatformUserId(platformUserId));

        Date expiresAt = jwt.getExpiresAt();
        //token过期
        if (expiresAt != null) {
            if (new Date().after(expiresAt)) {
                throw new CommonException(CommonExceptionEnum.EXPIRE_ERROR);
            }
        }
        return true;
    }

    private boolean isAnoymous(HandlerMethod handlerMethod){
        Object bean=handlerMethod.getBean();
        //类
        Class clazz=bean.getClass();
        if(clazz.getAnnotation(Anoymous.class)!=null){
            return true;
        }
        //接口
        Method method=handlerMethod.getMethod();
        return method.getAnnotation(Anoymous.class)!=null;
    }
}
