package com.magnus.futbot.helpers;

import org.bson.types.ObjectId;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppSettings {

    private ObjectId userId;

    public AppSettings() {
    }

    public AppSettings(ObjectId userId) {
        this.userId = userId;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }
}
