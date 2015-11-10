package com.ericsson.eea.inv.jbehave.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

@Component
public class FacebookLoginPage extends BasePage{

    public static final String PAGE_TITLE = "Facebook";
    public static final String PAGE_URL = "http://www.facebook.com/login.php";

    @FindBy(css = ".pam.login_error_box.uiBoxRed>div:first-child")
    WebElement errorHeader;

    @Autowired
    public FacebookLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean checkErrorHeader(String text) {
            return errorHeader.getText().equals(text);
    }

}
