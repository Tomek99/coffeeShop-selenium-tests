package pl.coffeeShop.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.coffeeShop.pages.HomePage;

public class ViewCartTest extends BaseTest {

    @Test
    public void verifyCartQuantityTest() {
        String expectedText = "Cart (2)";
        String actualText = new HomePage(driver)
                .openProductsPage()
                .addProductCart(1)
                .addProductCart(3)
                .openViewCart()
                .cartQuantity();

        Assert.assertEquals(actualText, expectedText, "Displayed cart quantity does not match expected.");
    }

    @Test
    public void clearTheCartTest() {
        String expectedText = "Your cart is empty";
        String actualText = new HomePage(driver)
                .openProductsPage()
                .addProductCart(1)
                .openViewCart()
                .clearTheCart()
                .getEmptyCartMessage();

        Assert.assertEquals(actualText, expectedText);
    }

    @Test
    public void deleteTheSingleProductFromCartTest() {
        String expectedText = "Cart (1)";
        String actualText = new HomePage(driver)
                .openProductsPage()
                .addProductCart(1)
                .addProductCart(3)
                .openViewCart()
                .deleteProductFromCart(1)
                .cartQuantity();

        Assert.assertEquals(actualText, expectedText, "Displayed cart quantity does not match expected.");
    }

    @Test
    public void changeProductQuantityTest() {
        String expectedText = "Cart (5)";
        String actualText = new HomePage(driver)
                .openProductsPage()
                .addProductCart(1)
                .openViewCart()
                .changeProductQuantity(0, "5")
                .cartQuantity();

        Assert.assertEquals(actualText, expectedText, "Displayed cart quantity does not match expected.");
    }

    @Test
    public void addDiscountTest() {
        String expectedText = "Wrong code, try again";
        String actualText = new HomePage(driver)
                .openProductsPage()
                .addProductCart(1)
                .openViewCart()
                .clickOnDiscount()
                .fillInputDiscount("15discount")
                .clickOnActiveDiscount()
                .getErrorDiscount();

        Assert.assertEquals(actualText, expectedText);
    }

    @Test
    public void openProductDetailsTest() {
        String expectedText = "Caffe Crema Dolce, 1 kg";
        String actualText = new HomePage(driver)
                .openProductsPage()
                .addProductCart(1)
                .openViewCart()
                .clickOnProductDetails(0)
                .productName();

        Assert.assertEquals(actualText, expectedText);
    }
}
