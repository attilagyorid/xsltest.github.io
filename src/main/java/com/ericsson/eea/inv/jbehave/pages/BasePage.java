package com.ericsson.eea.inv.jbehave.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;


public class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;
    protected JavascriptExecutor jsExecutor;

    private String pageURL;
    private String pageTitle;

    public BasePage(WebDriver driver){
        this.driver = driver;
        jsExecutor = ((JavascriptExecutor) driver);
        wait = new WebDriverWait(driver, 5);
    }

    public void loadPage(){
        driver.get(getPageURL());
        assertEquals(getPageTitle(),driver.getTitle());
    }

    public boolean verifyElementIsPresent(WebElement element){
        try{
            element.getTagName();
            return true;
        }catch(NoSuchElementException e){
            return false;
        }
    }

    public void clickElement(WebElement element){
        element.click();
    }

    public void setElementText(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
        assertEquals(text,element.getAttribute("value"));
    }

    public void selectValueInDropdown(WebElement dropdown, String value) {
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }

    public String getPageTitle(){
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPageURL() {
        return pageURL;
    }

    public void setPageURL(String pageURL) {
        this.pageURL = pageURL;
    }

    public void closeBrowser () {
        driver.quit();
    }

    public String getCurrentPagetitle () {
        return driver.getTitle();
    }

}
