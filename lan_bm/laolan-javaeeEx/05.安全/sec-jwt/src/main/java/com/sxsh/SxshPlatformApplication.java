package com.sxsh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author Administrator
 */
@SpringBootApplication(scanBasePackages ={"com.sxsh"})
@ServletComponentScan
public class SxshPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(SxshPlatformApplication.class);
    }

}
