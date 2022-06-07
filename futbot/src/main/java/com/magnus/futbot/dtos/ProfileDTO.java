package com.magnus.futbot.dtos;

public class ProfileDTO {
    private String email;
    private String password;

    private String userId;

    public ProfileDTO() {
    }

    public ProfileDTO(String email, String password, String userId) {
        this.email = email;
        this.password = password;
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
