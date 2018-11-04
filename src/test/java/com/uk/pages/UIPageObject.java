package com.uk.pages;

import com.uk.utils.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UIPageObject extends PageObject {

    public UIPageObject(WebDriver driver)

    {
        super(driver);
    }

    @FindBy(xpath = "//*[@title='Login']")
    private WebElement loginToolBarButton;

    @FindBy(name = "username")
    private WebElement userNameField;

    @FindBy(name= "password")
    private WebElement passwordField;

    @FindBy(css = ".button-login")
    private WebElement loginSubmitButton;

    @FindBy(css = ".output")
    private WebElement loginErrorMessage;

    public void clickLoginToolBarButton(){
        waitForElement(loginToolBarButton);
        loginToolBarButton.click();
    }

    public void enterUserName(String usernameIn){
        waitForElement(userNameField);
        userNameField.sendKeys(usernameIn);
    }


    public void enterPassword(String passwordIn){
        waitForElement(passwordField);
        passwordField.sendKeys(passwordIn);
    }

    public void clickLoginSubmitButton(){
        waitForElement(loginSubmitButton);
        loginSubmitButton.click();
    }

    public void loginIn(String userNameIn, String passwordIn){
        clickLoginToolBarButton();
        enterUserName(userNameIn);
        enterPassword(passwordIn);
        clickLoginSubmitButton();
    }

    public String getLoginErrorActualMessage(){
        waitForElement(loginErrorMessage);
        return loginErrorMessage.getText();
    }
}
