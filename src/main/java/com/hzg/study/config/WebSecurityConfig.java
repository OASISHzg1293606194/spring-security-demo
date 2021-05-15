package com.hzg.study.config;

import com.hzg.study.service.impl.MyAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Package: com.hzg.study.config
 * @FileName: WebSecurityConfig.java
 * @ClassName: WebSecurityConfig
 * @Description: 基于实现类配置SpringSecurity的用户名、密码
 * @Author: HuangZhiGao
 * @CreateDate: 2021-04-18 13:24
 * @Version: v1.0
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * MyUserDetailsServiceImpl的别名@Service("userDetailsService")与此一致
     */
    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 注销退出
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.html")
                .invalidateHttpSession(true) // 指定是否在登出的时候使session失效，默认为true
                .deleteCookies().permitAll(); // 删除指定的 Cookie

        // 自定义403页面
        // http.exceptionHandling().accessDeniedPage("/403.html");
        http.exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler("/403.html"));

        http.formLogin() // 表单登录
                .loginPage("/login.html") // 自定义登录页面设置
                .loginProcessingUrl("/user/login") // 登录的访问地址(由SpringSecurity处理)
                .defaultSuccessUrl("/test/index") // 登录成功后需要跳转的路径
                .permitAll() // 指定URL无需保护
                .and().authorizeRequests()
                .antMatchers("/js/*", "/css/*", "/img/*", "/mainicon/*", "/test/hello", "/user/login", "/40*.html") // 不需要认证的访问路径(包括静态资源)
                .permitAll()
                .antMatchers("/test/queryList").hasAuthority("admin") // 需要用户带有admin的权限
                .antMatchers("/test/queryOne").hasAnyAuthority("admin,role") // 需要用户带有admin或role任一权限
                .antMatchers("/test/update").hasRole("manager") // 需要用户为manager的角色
                .antMatchers("/test/selectAll").hasAnyRole("manager,employee") // 需要用户带有manager或employee任一角色
                .anyRequest().authenticated() // 其他请求需要认证
                .and().csrf().disable(); // 关闭CSRF防护

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
