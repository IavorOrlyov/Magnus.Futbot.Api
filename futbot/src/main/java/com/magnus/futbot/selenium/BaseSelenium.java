package com.magnus.futbot.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseSelenium {
    protected WebDriver webDriver;

    public BaseSelenium() {
        System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver.exe");
        webDriver = new ChromeDriver();
    }

}
