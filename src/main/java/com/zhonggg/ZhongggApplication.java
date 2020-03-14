package com.zhonggg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@MapperScan("com.zhonggg.*.dao")
@SpringBootApplication(scanBasePackages = "com")
public class ZhongggApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ZhongggApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ZhongggApplication.class, args);
    }

}
