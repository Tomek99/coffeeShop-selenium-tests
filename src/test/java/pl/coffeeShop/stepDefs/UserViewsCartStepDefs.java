package pl.coffeeShop.stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pl.coffeeShop.pages.HomePage;
import pl.coffeeShop.pages.ProductDetailsPage;
import pl.coffeeShop.pages.ProductsPage;
import pl.coffeeShop.pages.ViewCartPage;
import pl.coffeeShop.utils.DriverFactory;
import pl.coffeeShop.utils.SeleniumHelper;

import java.time.Duration;

public class UserViewsCartStepDefs {
    WebDriver driver;
    HomePage homePage;
    ProductsPage productsPage;

    ViewCartPage viewCartPage;

    ProductDetailsPage productDetailsPage;


    @Before
    public void setup() {
        driver = DriverFactory.getDriver();
        driver
                .manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
        driver
                .manage()
                .window()
                .maximize();
    }

    @After
    public void tearDown() {
        driver.quit();
    }


    @Given("The user is located on main page")
    public void the_user_is_located_on_main_page() {
        homePage = new HomePage(driver);
        homePage.openHomePage();
    }

    @When("The user opens product page")
    public void the_user_opens_product_page() {
        homePage.openProductsPage();
    }

    @When("The user adds products to cart")
    public void the_user_adds_products_to_cart() {

        productsPage = new ProductsPage(driver)
                .addProductCart(1)
                .addProductCart(3);


    }

    @When("The user opens preview cart")
    public void the_user_opens_preview_cart() {
        productsPage.openPreviewCart();
    }

    @When("The user opens View Cart page")
    public void the_user_opens_view_cart_page() {
        productsPage.openViewCart();
        viewCartPage = new ViewCartPage(driver);

    }

    @Then("The sum quantity of products displayed in the cart should be correct")
    public void the_sum_quantity_of_products_displayed_in_the_cart_should_be_correct() {
        String actualText = viewCartPage.cartQuantity();
        String expectedText = "Cart (2)";
        Assert.assertEquals(actualText, expectedText);
    }

    @When("The user clears the cart")
    public void the_user_clears_the_cart() {
        viewCartPage.clearTheCart();
    }

    @Then("The title about empty cart is displayed")
    public void the_title_about_empty_cart_is_displayed() {
        String expectedText = "Your cart is empty";
        String actualText = viewCartPage.getEmptyCartMessage();
        Assert.assertEquals(actualText, expectedText);
    }

    @And("The user deletes product from the cart")
    public void theUserDeletesProductFromTheCart() {
        viewCartPage.deleteProductFromCart(1);
    }

    @Then("The notification about deleted products is displayed")
    public void theNotificationAboutDeletedProductsIsDisplayed() {
        String expectedText = "Cart (1)";
        String actualText = viewCartPage.cartQuantity();
        Assert.assertEquals(actualText, expectedText, "Displayed cart quantity does not match expected.");
    }

    @And("The user changes product quantity")
    public void theUserChangesProductQuantity() {
        viewCartPage.changeProductQuantity(0, "5");

    }

    @Then("The sum quantity of the products displayed in the cart should be correct")
    public void theSumQuantityOfTheProductsDisplayedInTheCartShouldBeCorrect() {
        String expectedText = "Cart (6)";
        String actualText = viewCartPage.cartQuantity();
        Assert.assertEquals(actualText, expectedText, "Displayed cart quantity does not match expected.");
    }

    @And("The user inputs incorrect discount")
    public void theUserInputsIncorrectDiscount() {
        viewCartPage
                .clickOnDiscount()
                .fillInputDiscount("15discount")
                .clickOnActiveDiscount();
    }

    @Then("The notification about incorrect discount is displayed")
    public void theNotificationAboutIncorrectDiscountIsDisplayed() {
        String expectedText = "Wrong code, try again";
        String actualText = viewCartPage.getErrorDiscount();
        Assert.assertEquals(actualText, expectedText);
    }

    @And("The user opens product details")
    public void theUserOpensProductDetails() {
        viewCartPage.clickOnProductDetails(0);
        productDetailsPage = new ProductDetailsPage(driver);
    }

    @Then("The product details is displayed")
    public void theProductDetailsIsDisplayed() {
        String expectedText = "Caffe Crema Dolce, 1 kg";
        String actualText = productDetailsPage.productName();

        Assert.assertEquals(actualText, expectedText);
    }
}
