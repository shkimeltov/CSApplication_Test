package com.example.jpatestapp.Configuration;

import com.example.jpatestapp.Component.MemberInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        MemberInterceptor memberInterceptor = new MemberInterceptor();
        registry.addInterceptor(memberInterceptor)
                .excludePathPatterns(memberInterceptor.loginInessential);
    }
}
