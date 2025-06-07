package pl.coffeeShop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.coffeeShop.utils.SeleniumHelper;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ProductsPage {
    @FindBy(className = "LatestProduct_LatestProduct__2nlVY")
    private List<WebElement> listOfProducts;
    @FindBy(xpath = "//li/a[text()='1']")
    private WebElement btnOne;
    @FindBy(xpath = "//li/a[text()='2']")
    private WebElement btnTwo;
    @FindBy(xpath = "//li/a[text()='Next ']")
    private WebElement btnNext;

    @FindBy(xpath = "//a[@aria-label='Previous page']")
    private WebElement btnArrowLeft;

    @FindBy (xpath = "//div[@class='BtnsProduct_BtnsProduct__Gi5nT']//button[2]")
    private List<WebElement> cartBtn;

    @FindBy(className = "NavigationBar_quantityProductsInBasket__UCI0e")
    private WebElement productsQuantity;

    @FindBy(xpath = "//div/a[@href='/wish-list']")
    private WebElement btnWishlist;


    @FindBy(id = "cartBtnOpen132")
    private WebElement previewCartBtn;

    @FindBy(linkText = "View my cart")
            private  WebElement cartViewBtn;

    WebDriver driver;

    public ProductsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;


    }

    public ProductsPage clickOnBtnOne() {
        SeleniumHelper.handleJavaExecutor(driver, btnOne);
        return this;
    }

    public ProductsPage clickOnBtnTwo() {
        SeleniumHelper.handleJavaExecutor(driver, btnTwo);
        return this;
    }

    public ProductsPage clickOnBtnNext() {
        SeleniumHelper.handleJavaExecutor(driver, btnNext);
        return this;
    }

    public ProductsPage clickOnBtnArrowLeft() {
        SeleniumHelper.handleJavaExecutor(driver, btnArrowLeft);
        return this;
    }

    public ProductDetailsPage openProductDetailsPage(String productId) {
        driver.findElement(By.id(productId)).click();
        return new ProductDetailsPage(driver);
    }

    public ProductsPage openPreviewCart() {
        previewCartBtn.click();
        return new ProductsPage(driver);
    }

    public ViewCartPage openViewCart() {
        cartViewBtn.click();

        return new ViewCartPage(driver);
    }

    public ProductDetailsPage openProductDetailsPageByText(String productName) {
        driver.findElement(By.xpath("//h3[text()='" + productName + "']"))
                .click();
        return new ProductDetailsPage(driver);
    }

    public ProductsPage addProductCart(int productNumber) {

        cartBtn.get(productNumber).click();
        return this;
    }

    public ProductsPage addProductWishList(String wishlistId) {
        driver.findElement(By.id(wishlistId)).click();
        return this;
    }

    public String getMessageText() {
        String alertXPath = "//div[@role='alert']/div[2]";

        SeleniumHelper.waitForVisibility(driver, By.xpath(alertXPath));
        return driver.findElement(By.xpath(alertXPath)).getText();
    }


    public String getProductsQuantityInCart() {
        return productsQuantity.getText();
    }


    public HomePage addProductsWithoutDuplicateCart(int numberOfProducts) {
        Set<Integer> uniqueRandomNumbers = generateUniqueRandomNumbers(numberOfProducts);

        for (int number : uniqueRandomNumbers) {
            String idProduct = "cartFillId" + number;
            WebElement cartBtn = driver.findElement(By.id(idProduct));
            SeleniumHelper.handleJavaExecutor(driver, cartBtn);
        }

        return new HomePage(driver);
    }

    public HomePage addDuplicateProductsCart(int numberOfProducts) {
        for (int i = 0; i < numberOfProducts; i++) {
            int randomProductTwice = new Random().nextInt(2);

            String idProduct = "cartFillId" + randomProductTwice;
            WebElement cartBtn = driver.findElement(By.id(idProduct));
            SeleniumHelper.handleJavaExecutor(driver, cartBtn);
        }

        return new HomePage(driver);
    }

    private Set<Integer> generateUniqueRandomNumbers(int size) {
        Set<Integer> uniqueNumbers = new HashSet<>();
        Random random = new Random();

        while (uniqueNumbers.size() < size) {
            int randomNumber = random.nextInt(size);
            uniqueNumbers.add(randomNumber);
        }
        return uniqueNumbers;
    }


    public int getProducts() {
        return listOfProducts.size();
    }


    public WishlistPage openWishlistPage () {
        btnWishlist.click();
        return new WishlistPage(driver);
    }


}
