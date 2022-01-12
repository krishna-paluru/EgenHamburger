package com.krishna.TexasHamburger.config;

import com.krishna.TexasHamburger.Interceptor.ExecutionTimeInterceptor;
import com.krishna.TexasHamburger.model.ExecutionTime;
import com.krishna.TexasHamburger.repository.ExecutionTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private  ExecutionTimeRepository executionTimeRepository;
    @Autowired
    private  ExecutionTime executionTime;
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ExecutionTimeInterceptor(executionTimeRepository,executionTime)).addPathPatterns("/TexasHamburger/**");
    }
}
