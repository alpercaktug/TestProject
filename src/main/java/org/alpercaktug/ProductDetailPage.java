package org.alpercaktug;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ProductDetailPage extends BasePage{


    By addCardButton = By.id("addBasket");

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOnProductDetailPage() {
        return isDisplayed(addCardButton);
    }

    public void clickSize(){
        WebElement variationContainer = driver.findElement(By.className("m-variation"));

        List<WebElement> variationItems = variationContainer.findElements(By.className("m-variation__item"));

        List<WebElement> enabledVariationItems = variationItems.stream()
                .filter(item -> !item.getAttribute("class").contains("-disabled"))
                .collect(Collectors.toList());

        int randomIndex = (int) (Math.random() * enabledVariationItems.size());

        enabledVariationItems.get(randomIndex).click();

    }
    public void addToCard() throws InterruptedException {
        Thread.sleep(1000);
        clickSize();
        Thread.sleep(1000);
        WebElement addToCardButton = driver.findElement(By.cssSelector("button.m-addBasketFavorite__basket"));
        addToCardButton.click();
    }

    public void goToCard() throws InterruptedException {
        Thread.sleep(5000);
        WebElement cardButton = driver.findElement(By.cssSelector("a.bwi-cart-o.-cart"));
        cardButton.click();
    }
}
