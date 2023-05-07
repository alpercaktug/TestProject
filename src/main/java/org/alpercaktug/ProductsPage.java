package org.alpercaktug;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class ProductsPage extends BasePage{

    By productNameLocator = new By.ByCssSelector("div.o-productList__item");
    By productList = By.id("productListTitle");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOnProductPage() {
        return isDisplayed(productList);
    }

    private List<WebElement> getAllProducts(){
        return findAll(productNameLocator);
    }

    public void selectProduct() {
        Random random = new Random();
        int randomInt = random.nextInt(getAllProducts().size());
        getAllProducts().get(randomInt).click();
    }

}
