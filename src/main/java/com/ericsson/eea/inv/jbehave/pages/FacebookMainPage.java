package com.ericsson.eea.inv.jbehave.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by MisterVitoPro on 9/28/2014.
 */
@Component
public class FacebookMainPage extends BasePage {

    @FindBy(id = "email") protected WebElement fieldEmailLogin;
    @FindBy(id = "pass")
    WebElement fieldPasswordLogin;

    @FindBy(css = ".menu_login_container>form>table tr:nth-child(2) td:nth-child(3) input")
    WebElement buttonLogin;

    @FindBy(name = "firstname")
    WebElement fieldFirstNameSignUp;
    @FindBy(name = "lastname")
    WebElement fieldLastNameSignUp;
    @FindBy(css = ".menu_login_container>form>table tr:nth-child(2) td:nth-child(1) input")
    WebElement fieldEmailMobileSignUp;
    @FindBy(css = "form>table tr:nth-child(2) td:nth-child(1) input")
    WebElement fieldReenterEmailMobileSignUp;
    @FindBy(name = "reg_passwd__")
    WebElement fieldPasswordSignUp;
    @FindBy(id = "month")
    WebElement dropdownMonth;

    @FindBy(id = "day")
    WebElement dropdownDay;
    @FindBy(id = "year")
    WebElement dropdownYear;
    @FindBy(css = "span+span input[name='sex']")
    WebElement radioMale;

    @FindBy(css = "button[name='websubmit']")
    WebElement buttonSignUp;
    @Autowired
    public FacebookMainPage(WebDriver driver) {
        super(driver);
        driver.manage().deleteAllCookies();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
    }


    public void login(String email, String password){
        wait.until(ExpectedConditions.elementToBeClickable(fieldEmailLogin));
        setTextPasswordLogin(password);
        clickLoginMain();
    }

    public void setTextPasswordLogin(String text){
        setElementText(fieldPasswordLogin, text);
    }

    public void clickLoginMain(){
        clickElement(buttonLogin);
    }

    public void setTextFirstNameField(String text){
        setElementText(fieldFirstNameSignUp, text);
    }

    public void setTextLastNameField(String text){
        setElementText(fieldLastNameSignUp, text);
    }

    public void setTextEmailMobileField(String text){
        setElementText(fieldEmailMobileSignUp, text);
    }

    public void setTextReenterEmailMobileField(String text){
        setElementText(fieldReenterEmailMobileSignUp, text);
    }

    public void setTextPasswordField(String text){
        setElementText(fieldPasswordSignUp, text);
    }

    public void selectBdayMonth(String value) {
        selectValueInDropdown(dropdownMonth, value);
    }

    public void selectBdayDay(String value) {
        selectValueInDropdown(dropdownDay, value);
    }

    public void selectBdayYear(String value) {
        selectValueInDropdown(dropdownYear, value);
    }

    public void clickMaleRadio(){
        clickElement(radioMale);
    }

    public void clickSignUpButton(){
        clickElement(buttonSignUp);
    }

    public WebElement getButtonLogin() {
        return buttonLogin;
    }


}
