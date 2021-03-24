package com.sxsh.note;

import com.alibaba.fastjson.JSONObject;
import com.sxsh.exception.CommentException;
import com.sxsh.exception.CommentExceptionEnum;
import com.sxsh.exception.ServiceExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;


@Aspect
@Component
@Slf4j
public class SignVerifyAspect {

    private static final String secret = "YI9BRO2Z7M6GR4471Y9S4KAZCT4QXAXG";

    @Pointcut("@annotation(com.sxsh.note.SignVerify)")
    public void signVerifyAspect() {
    }


    @Around("signVerifyAspect()")
    public Object beforeAction(ProceedingJoinPoint proceeding) throws Throwable {
        Object[] objects = proceeding.getArgs();
        SignVerifyDTO signVerifyDTOS = (SignVerifyDTO)objects[0];
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(secret);
        stringBuffer.append("data");
        stringBuffer.append(signVerifyDTOS.getData());
        stringBuffer.append("timestamp");
        stringBuffer.append(signVerifyDTOS.getTimestamp());
        stringBuffer.append(secret);
        String sign = DigestUtils.md5Hex(stringBuffer.toString()).toUpperCase();
        if (sign.equals(signVerifyDTOS.getSign())) {
            return proceeding.proceed();
        } else {
            throw new CommentException(CommentExceptionEnum.SIGNVERIFY_ERROR);
        }
    }

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("timestamp","1608368979");
        jsonObject.put("sign","00FE27C7FE5270A11FB6AFF3B78E9919");
        jsonObject.put("data","{\"shopId\":\"11\",\"content\":{\"shop_tel\":\"13678029077\",\"order_no\":\"2012201630545398997\",\"original_price\":0.02,\"total_price\":0.02,\"customer_tel\":\"13678976543\",\"privacy_number\":\"\",\"shop_name\":\"这是一个店铺名称\",\"deliver_fee\":0,\"order_note\":\"这里是备注\",\"order_time\":1608453054,\"customer_name\":\"撒地方\",\"customer_address\":\"天府五街(地铁站)撒旦教奥奥大所大\",\"customer_tag\":\"104.06951,30.537107\",\"plat_name\":\"松鼠速客\",\"pay_method\":\"在线支付\",\"order_content\":\"[{\\\"name\\\":\\\"ceshi1\\\\u30100.2\\\\u6298-\\\\u539f\\\\u4ef71\\\\u3011\\\",\\\"price\\\":0.02,\\\"quantity\\\":1,\\\"pocket\\\":1,\\\"spu_code\\\":\\\"1212\\\"}]\",\"order_mark\":4,\"order_send\":\"送货上门：尽快送达\",\"order_addition\":\"[{\\\"name\\\":\\\"\\\\u5c0f\\\\u8ba1\\\",\\\"price\\\":0.02,\\\"quantity\\\":1}]\",\"predict_code\":\"\",\"callback_url\":\"http://o2o-api.xd0.co/index/OrderPrint/printCallback\"}}");
        //SignVerifyDTO signVerifyDTOS = (SignVerifyDTO)objects[0];
        SignVerifyDTO signVerifyDTOS = JSONObject.parseObject(jsonObject.toString(),SignVerifyDTO.class);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(secret);
        stringBuffer.append("data");
        stringBuffer.append(signVerifyDTOS.getData());
        stringBuffer.append("timestamp");
        stringBuffer.append(signVerifyDTOS.getTimestamp());
        stringBuffer.append(secret);
        String sign = DigestUtils.md5Hex(stringBuffer.toString()).toUpperCase();
    }
}
