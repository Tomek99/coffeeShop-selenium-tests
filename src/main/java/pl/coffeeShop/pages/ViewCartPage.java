package pl.coffeeShop.pages;

import com.github.dockerjava.api.model.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pl.coffeeShop.utils.DriverFactory;

import java.util.List;

public class ViewCartPage {


    @FindBy(xpath = "//button[p[text()='Clear the cart']]")
    private WebElement clearTheCartBtn;

    @FindBy(xpath = "//button[contains(text(), 'Return to products page')]")
    private WebElement returnProductsPageBtn;

    @FindBy(xpath = "//div[@class='ProductItem_Item__Wrqrs']//button")
    private List<WebElement> deleteProductBtn;

    @FindBy (linkText = "Checkout")
    private WebElement checkoutBtn;

    @FindBy (xpath =  "//button[span[text()='Do you have a discount?']]")
    private  WebElement discountBtn;

    @FindBy (className = "CartSummary_inputDiscount__qwKUh")
    private  WebElement discountInput;

    @FindBy (xpath = "//button[text()='Active']")
    private WebElement activeBtn;

    @FindBy (xpath = "//div[@class='ProductItem_leftSide__DA1FO']//p")
    private List<WebElement> openProductDetailsBtn;
    @FindBy (className = "ProductItem_dropdownEl__j3cqc")
    private List<WebElement> productQuantitySelect;
    @FindBy (className = "FillCart_cartHeader__tOR23")
    private WebElement cartQuantity;

    @FindBy (xpath = "//p[contains(text(), 'Wrong code')]")
    private WebElement errorDiscount;

    @FindBy (className = "EmptyCart_cartHeader__wyhcy")
    private WebElement emptyCartHeader;

    @FindBy
    WebDriver driver;


    public ViewCartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;

    }

    public ViewCartPage clearTheCart() {
        clearTheCartBtn.click();
        return  this;
    }

    public ViewCartPage clickOnReturnProductPage() {
        returnProductsPageBtn.click();
        return  this;
    }

    public ViewCartPage deleteProductFromCart(int productNumber) {
        deleteProductBtn.get(productNumber).click();
        return  this;
    }

    public ViewCartPage clickOnCheckout() {
        checkoutBtn.click();
        return  this;
    }

    public ViewCartPage clickOnDiscount() {
        discountBtn.click();
        return  this;
    }

    public ViewCartPage fillInputDiscount(String text) {
        discountInput.sendKeys(text);
        return  this;
    }

    public ViewCartPage clickOnActiveDiscount() {
        activeBtn.click();
        return  this;
    }

    public ProductDetailsPage clickOnProductDetails(int productNumber) {
        openProductDetailsBtn.get(productNumber).click();
        return  new ProductDetailsPage(driver);
    }

    public String getEmptyCartMessage() {
        return emptyCartHeader.getText();
    }

    public String cartQuantity() {
        return cartQuantity.getText();
    }

    public String getErrorDiscount() {
        return errorDiscount.getText();
    }


    public ViewCartPage changeProductQuantity(int productNumber, String quantity) {
        Select objSelect = new Select(productQuantitySelect.get(productNumber));
        objSelect.selectByValue(quantity);
        return this;
    }
}
