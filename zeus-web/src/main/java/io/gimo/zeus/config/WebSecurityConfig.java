package io.gimo.zeus.config;

import io.gimo.zeus.web.security.ZeusAuthenticationFailureHandler;
import io.gimo.zeus.web.security.ZeusAuthenticationSuccessHandler;
import io.gimo.zeus.web.security.ZeusPermissionEvaluator;
import io.gimo.zeus.web.security.ZeusUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return new ZeusUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new ZeusAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new ZeusAuthenticationFailureHandler();
    }

    @Bean
    public PermissionEvaluator permissionEvaluator() {
        return new ZeusPermissionEvaluator();
    }

    @Bean
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler(){
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(permissionEvaluator());
        return handler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 参照 https://www.cnblogs.com/yhtboke/p/5764697.html配置
        // https://blog.csdn.net/yuanlaijike/article/details/80327880
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
                // 表单的用户名参数名称
                .usernameParameter("username")
                // 表单的密码参数名称
                .passwordParameter("password")
                // 登陆的请求地址
                .loginPage("/login")
                // 处理登陆的地址
                .loginProcessingUrl("/handleLogin")
                // 默认成功登陆的页面
                .defaultSuccessUrl("/index", true)
                // 登陆失败的地址
                .failureUrl("/login")
                // 自定义登录成功处理器
                .successHandler(authenticationSuccessHandler())
                // 自定义登录失败处理器
                .failureHandler(authenticationFailureHandler())
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
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 所有用户均可访问的资源
        web.ignoring()
                .antMatchers("/assets/**", "/app/**")
                .and()
           .expressionHandler(webSecurityExpressionHandler());
    }

}

