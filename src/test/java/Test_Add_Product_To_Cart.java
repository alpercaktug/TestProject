import org.alpercaktug.CardPage;
import org.alpercaktug.HomePage;
import org.alpercaktug.ProductDetailPage;
import org.alpercaktug.ProductsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class Test_Add_Product_To_Cart extends BaseTest{

    HomePage homePage ;
    ProductsPage productsPage ;
    ProductDetailPage productDetailPage ;
    CardPage cardPage;

    @Test
    @Order(1)
    public void navigate_to_homepage(){
        homePage = new HomePage(driver);
        homePage.acceptCookies();
        homePage.selectGender();
        Assertions.assertTrue(homePage.isOnHomePage(), "Not on HomePage");
    }

    @Test
    @Order(2)
    public void search_a_product() throws InterruptedException {
        productsPage = new ProductsPage(driver);
        homePage.search("şort");
        Thread.sleep(2000);
        homePage.clear();
        homePage.search("gömlek");
        homePage.pressEnter();
        Assertions.assertTrue(productsPage.isOnProductPage() ,"Not on products page");
    }

    @Test
    @Order(3)
    public void select_a_product() throws InterruptedException {
        productDetailPage = new ProductDetailPage(driver);
        Thread.sleep(500);
        productsPage.selectProduct();
        Thread.sleep(1000);
        Assertions.assertTrue(productDetailPage.isOnProductDetailPage(),"Not on product detail page");
    }


    @Test
    @Order(4)
    public void add_product_to_cart() throws InterruptedException {
        Thread.sleep(1000);
        productDetailPage.addToCard();
        Thread.sleep(1000);
        Assertions.assertTrue(homePage.isProductCountUp(),"Product count did not increase!");
    }


    @Test
    @Order(5)
    public void go_to_cart() throws InterruptedException {
        Thread.sleep(1000);
        cardPage = new CardPage(driver);
        productDetailPage.goToCard();
        Thread.sleep(1000);
        //Assertions.assertTrue(cartPage.checkIfProductAdded() ,"Product was not added to cart!");
    }



}
