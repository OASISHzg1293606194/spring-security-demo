package com.hzg.study.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;


/**
 * @Package: com.hzg.study.config
 * @FileName: WebSecurityDataSourceConfig.java
 * @ClassName: WebSecurityDataSourceConfig
 * @Description: 数据源
 * @Author: HuangZhiGao
 * @CreateDate: 2021-05-21 14:44
 * @Version: v1.0
 */
@Configuration
public class WebSecurityDataSourceConfig {

    /**
     * 注入数据源
     */
    @Autowired
    private DataSource dataSource;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        // 设置数据源
        jdbcTokenRepository.setDataSource(dataSource);
        // 自动建表 persistent_logins
        // jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

}
