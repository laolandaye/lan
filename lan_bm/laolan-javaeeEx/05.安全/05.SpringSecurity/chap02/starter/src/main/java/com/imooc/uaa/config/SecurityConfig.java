package com.imooc.uaa.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.uaa.security.filter.RestAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ObjectMapper objectMapper;

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests(req -> req
////                .antMatchers("/api/**").hasRole("USER")
//                .anyRequest().authenticated())
////            .formLogin(AbstractHttpConfigurer::disable) // 默认登录页
//            .formLogin(from ->
//                from.loginPage("/login")
//                    .successHandler(jsonLoginSuccessHandler())
//                    .failureHandler(jsonLoginFailureHandler())
//                    .permitAll()) // 指定登录页
//            .httpBasic(withDefaults())
//            .csrf(withDefaults())// 显示浏览器对话框，需要禁用 CSRF ，或添加路径到忽略列表
//            .logout(logout ->
//                logout.logoutUrl("perform_out")
//            )
//            .rememberMe(rememberMe ->
//                rememberMe.tokenValiditySeconds(30*24*3600)
//            )
//        ;
//    }

    // 加入自定义拦截器链路, 以前默认session的处理策略，废弃
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(req -> req
                .antMatchers("/authorize/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/api/**").hasRole("USER")
                .anyRequest().authenticated()
            )
            .addFilterAt(restAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .csrf(csrf -> csrf.disable())// 显示浏览器对话框，需要禁用 CSRF ，或添加路径到忽略列表
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("user")
            .password(passwordEncoder().encode("12345678"))
            .roles("USER", "ADMIN");
    }

    @Bean
    PasswordEncoder passwordEncoder() { // 密码加密
        return new BCryptPasswordEncoder();
    }

    private AuthenticationSuccessHandler jsonLoginSuccessHandler() {
        return (req, res, auth) -> {
            ObjectMapper objectMapper = new ObjectMapper();
            res.setStatus(HttpStatus.OK.value());
            res.getWriter().println(objectMapper.writeValueAsString(auth));
            log.debug("认证成功");
        };
    }

    private AuthenticationFailureHandler jsonLoginFailureHandler() {
        return (req, res, exp) -> {
            res.setStatus(HttpStatus.UNAUTHORIZED.value());
            res.setContentType(MediaType.APPLICATION_JSON_VALUE);
            res.setCharacterEncoding("UTF-8");
//            val errData = Map.of(
//                "title", "认证失败",
//                "details", exp.getMessage()
//            );
            Map<String, String> errData = new HashMap() {{
                put("title", "认证失败");
                put("details", exp.getMessage());
            }};
            res.getWriter().println(objectMapper.writeValueAsString(errData));
        };
    }

    private RestAuthenticationFilter restAuthenticationFilter() throws Exception {
        RestAuthenticationFilter filter = new RestAuthenticationFilter(objectMapper);
        filter.setAuthenticationSuccessHandler(jsonLoginSuccessHandler());
        filter.setAuthenticationFailureHandler(jsonLoginFailureHandler());
        filter.setAuthenticationManager(authenticationManager());
        filter.setFilterProcessesUrl("/authorize/login");
        return filter;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/public/**")
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
