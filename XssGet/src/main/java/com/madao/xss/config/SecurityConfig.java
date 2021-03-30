package com.madao.xss.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login.html")   //自定义登录页面
                .loginProcessingUrl("/zzz")
                .defaultSuccessUrl("/home", true)   //登录成功跳转页面
                .and()
                .authorizeRequests()
                //.antMatchers("/login.html","/static/**","/recive/**","/api/z/**").permitAll() //放行的页面
                //.anyRequest().authenticated()   //除了放行的，其他都需要认证
                .antMatchers("/api/z/**").permitAll()    //读取js文件页面放行
                .antMatchers("/api/**","/home/**").authenticated()   //需要认证的页面
                .anyRequest().permitAll()    //除了需要认证的页面其余的全放行
                .and()
                .csrf().disable();  //禁用csrf
    }
}
