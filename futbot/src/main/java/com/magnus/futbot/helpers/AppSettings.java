package com.magnus.futbot.helpers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppSettings {

    private String userId;

    public AppSettings() {
    }

    public AppSettings(String userId) {
        this.userId = userId;
    }

    @Bean
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
