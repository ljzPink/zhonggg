package com.zhonggg.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;


/**
 * 关于web的一些配置
 *
 * @author wangxiaolong
 * @date 2019/6/26.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${static.file}")
    private String staticFile;
    @Value("${static.download.file}")
    private String downloadFile;
    @Value("${static.upload.file}")
    private String uploadFile;

    /**
     * 跨域支持
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600 * 24);
    }

    /**
     * 添加静态资源
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/staticFile/**").addResourceLocations("file:" + staticFile);
        registry.addResourceHandler("/downloadFile/**").addResourceLocations("file:" + downloadFile);
        registry.addResourceHandler("/uploadFile/**").addResourceLocations("file:" + uploadFile);
    }

    /**
     * 将返回前端的null转成空串
     *
     * @return
     */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeString(StringUtils.EMPTY);
            }
        });
        return objectMapper;
    }

    /**
     * 过滤器
     *
     * @author wangxiaolong
     * @date 2019/12/6 14:21
     */

}
