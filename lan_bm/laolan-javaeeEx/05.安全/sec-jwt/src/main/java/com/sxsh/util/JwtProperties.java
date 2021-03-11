package com.sxsh.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * @Description
 * @Author lhy
 * @Date 2020/12/3
 * @Version 1.0.0
 */
@ConfigurationProperties(prefix = JwtProperties.JWT_PREFIX)
@Data
@Configuration
public class JwtProperties {

    public static final String JWT_PREFIX = "jwt";


    private String secret;


    private Duration expireTime;

}
