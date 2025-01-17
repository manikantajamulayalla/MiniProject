package com.FeedLink.MiniProject.Service;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public class SecurityConfigaration {
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .dispatcherTypeMatchers(HttpMethod.valueOf("/donor/addDonation")).permitAll() // Public endpoint
                .anyRequest().authenticated();            // All other requests require authentication
    }
}
