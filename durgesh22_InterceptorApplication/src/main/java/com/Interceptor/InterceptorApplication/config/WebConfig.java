package com.Interceptor.InterceptorApplication.config;

import com.Interceptor.InterceptorApplication.Interceptor.CustomInterceptor1;
import com.Interceptor.InterceptorApplication.Interceptor.CustomInterceptor2;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new CustomInterceptor1())
                .addPathPatterns("/home");

        registry.addInterceptor(new CustomInterceptor2())
                .addPathPatterns("/welcome");
    }
}
