package com.magnus.futbot.selenium.models;

public class LoginResponseDTO {
    private LoginResponseType loginStatus;

    public LoginResponseDTO(LoginResponseType loginStatus) {
        this.loginStatus = loginStatus;
    }

    public LoginResponseType getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(LoginResponseType loginStatus) {
        this.loginStatus = loginStatus;
    }
}
