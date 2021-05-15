package com.hzg.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Package: com.hzg.study.config
 * @FileName: SecurityConfig.java
 * @ClassName: SecurityConfig
 * @Description: 基于配置类配置SpringSecurity的用户名、密码
 * @Author: HuangZhiGao
 * @CreateDate: 2021-04-20 11:41
 * @Version: v1.0
 */
// @Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String USER_NAME = "Lily";
    private static final String USER_PASSWORD = "0207";
    private static final String USER_ROLE = "admin";


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePswd = encoder.encode(USER_PASSWORD);
        auth.inMemoryAuthentication()
                .withUser(USER_NAME)
                .password(encodePswd)
                .roles(USER_ROLE);
    }

    /**
     * @return org.springframework.security.crypto.password.PasswordEncoder
     * @throws
     * @author HuangZhiGao
     * @date 2021/4/20/020 11:27
     * @description 注入PasswordEncoder类到spring容器中<br               />
     * 否则直接使用BCryptPasswordEncoder会报错<br/>
     * java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
