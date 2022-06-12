package com.magnus.futbot.selenium.models;

public class LoginResponseDTO {
    private int loginStatus;

    public LoginResponseDTO(int loginStatus) {
        this.loginStatus = loginStatus;
    }

    public int getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(int loginStatus) {
        this.loginStatus = loginStatus;
    }
}
