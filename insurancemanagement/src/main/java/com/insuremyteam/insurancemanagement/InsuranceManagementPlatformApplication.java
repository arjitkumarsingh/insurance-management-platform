package com.insuremyteam.insurancemanagement;

import com.insuremyteam.insurancemanagement.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InsuranceManagementPlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(InsuranceManagementPlatformApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<JwtFilter> getFilterBean() {
        FilterRegistrationBean<JwtFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new JwtFilter());
        filterRegistrationBean.addUrlPatterns("/api/v1/users");
        filterRegistrationBean.addUrlPatterns("/api/v1/user/*");
        return filterRegistrationBean;
    }
}