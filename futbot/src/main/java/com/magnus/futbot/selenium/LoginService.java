package com.magnus.futbot.selenium;

import com.magnus.futbot.selenium.models.LoginResponseDTO;
import com.magnus.futbot.selenium.models.LoginResponseType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

@Service
public class LoginService extends BaseSelenium {
    public LoginResponseDTO Login(String username, String password) throws InterruptedException {

        webDriver.get("https://www.ea.com/fifa/ultimate-team/web-app/");
        Thread.sleep(4000);
        WebElement element = null;
        do {
            try {
                element = webDriver.findElement(new By.ByCssSelector("#Login > div > div > button.btn-standard.call-to-action"));
            } catch (Exception ex) {}
        }
        while (!(element != null && element.isDisplayed() && element.isEnabled()));
        element.click();
        Thread.sleep(2000);

        WebElement emailInput = webDriver.findElement(new By.ByCssSelector("#email"));
        emailInput.sendKeys(username);

        WebElement passwordInput = webDriver.findElement(new By.ByCssSelector("#password"));
        passwordInput.sendKeys(password);

        WebElement rememberMeInput = webDriver.findElement(new By.ByCssSelector("#rememberMe"));
        if (!rememberMeInput.isSelected()) {
            rememberMeInput.click();
        }

        WebElement signInButton = webDriver.findElement(new By.ByCssSelector("#logInBtn"));
        signInButton.click();
        Thread.sleep(1500);

        WebElement wrongCredentials = null;
         try {
             wrongCredentials = webDriver.findElement(new By.ByCssSelector("#online-general-error > p"));
         } catch (Exception ex) {}

         if (wrongCredentials != null) return new LoginResponseDTO(LoginResponseType.WrongCredentials);

        return new LoginResponseDTO(LoginResponseType.Successful);
    }
}
