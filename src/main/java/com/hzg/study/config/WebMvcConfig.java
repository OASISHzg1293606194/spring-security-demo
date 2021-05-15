package com.hzg.study.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Package: com.hzg.study.config
 * @FileName: WebMvcConfig.java
 * @ClassName: WebMvcConfig
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-04-20 19:16
 * @Version: v1.0
 */
// @Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

}
