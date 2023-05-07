package org.alpercaktug;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends BasePage {

    @FindBy(className = "o-header__userInfo--count" )
    WebElement cardCountLocator;
    @FindBy(id = "onetrust-accept-btn-handler")
    WebElement acceptCookiesButton;
    @FindBy(id = "genderWomanButton")
    WebElement selectGenderButton;
    @FindBy(xpath = "/html/body/header/div/div/div[2]/div/div/div/input")
    WebElement searchBoxLocator;
    @FindBy(id = "onetrust-banner-sdk" )
    WebElement cookiesBanner;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isOnHomePage() {
        String pageTitle = driver.getTitle();
        return pageTitle.equals("Beymen.com – Türkiye’nin Tek Dijital Lüks Platformu");
    }

    public void acceptCookies(){
        waitForElementToBeVisible(cookiesBanner);
        click(acceptCookiesButton);
    }

    public void selectGender() {
        waitForElementToBeVisible(selectGenderButton);
        click(selectGenderButton);
    }

    public boolean isProductCountUp() {
        return getCartCount() > 0 ;
    }


    private int getCartCount(){
        String countString = cardCountLocator.getText();
        String[] parts = countString.split("\\(");
        String part = parts[1].replaceAll("\\)", "");
        int number = Integer.parseInt(part);
        return number;
    }

    public void search(String text) {
       searchBoxLocator.sendKeys(text);
    }

    public void clear() {
        searchBoxLocator.sendKeys(Keys.CONTROL + "a");
        searchBoxLocator.sendKeys(Keys.DELETE);
    }

    public void pressEnter(){
        searchBoxLocator.sendKeys(Keys.ENTER);
    }
}

