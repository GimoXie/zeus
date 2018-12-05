package io.gimo.zeus.config;

import io.gimo.zeus.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 参照 https://www.cnblogs.com/yhtboke/p/5764697.html配置
        http.csrf().disable()
            // 请求拦截配置
            .authorizeRequests()
                // 所有用户均可访问的资源
                .antMatchers("/assets/**", "/app/**").permitAll()
                // 不在上述配置中的请求会被鉴权
                .anyRequest().authenticated()
                .and()
            // 异常处理配置
            .exceptionHandling()
                // 403的地址
                .accessDeniedPage("/403")
                .and()
            // 登陆配置
            .formLogin()
                // 登陆的请求地址
                .loginPage("/login")
                // 处理登陆的地址
                .loginProcessingUrl("/handleLogin")
                // 默认成功登陆的页面
                .defaultSuccessUrl("/index", true)
                // 登陆失败的地址
                .failureUrl("/login")
                // 表单的用户名参数名称
                .usernameParameter("username")
                // 表单的密码参数名称
                .passwordParameter("password")
                .permitAll()
                .and()
            // 注销配置
            .logout()
                // 注销的请求地址
                .logoutUrl("/logout")
                // 注销成功后的请求地址
                .logoutSuccessUrl("/login")
                // 是否注销会话
                .invalidateHttpSession(true);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

    }

}

