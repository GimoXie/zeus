package io.gimo.zeus.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by zmxie on 2018/12/5.
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("/view/login");
        registry.addViewController("/index").setViewName("/view/index");
        registry.addViewController("/dashboard").setViewName("/view/dashboard");
    }

}
