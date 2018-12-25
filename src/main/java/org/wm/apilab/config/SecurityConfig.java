package org.wm.apilab.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.wm.apilab.auth.CustomAuthenticationProvider;
import org.wm.apilab.filter.JWTAuthenticationFilter;
import org.wm.apilab.filter.JWTLoginFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 關閉CSRF驗證
        http.csrf().disable()
                // 認證請求
                .authorizeRequests()
                // "/login" 的POST請求 都放行
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                // 權限檢測
//                .antMatchers("/*/hello/*").hasAuthority("AUTH_WRITE")
                // 角色檢測
                .antMatchers("/userApi/register").permitAll()
                .antMatchers("/userApi/*").hasRole("ADMIN")
                .antMatchers("/userApi/hello/*").hasRole("GroupA")
                // 所有請求需要身份認證
//                .anyRequest().authenticated()
                // "/"所有請求都放行
              .antMatchers("/*").permitAll()
            .and()
                // 新增Filter "/login" 的請求透過 JWTLoginFilter 處理
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                // 新增Filter驗證其他請求的Token是否合法
                .addFilterBefore(new JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定義身份驗證
        auth.authenticationProvider(customAuthenticationProvider);

    }
}
