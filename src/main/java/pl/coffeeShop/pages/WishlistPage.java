package pl.coffeeShop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WishlistPage {
    @FindBy(xpath = "//button[text()='View']")
    WebElement btnView;

    @FindBy(xpath = "//button[text()='Delete']")
    WebElement btnDelete;

    @FindBy(className = "WishProducts_WishProducts__kFZj6")
    List<WebElement> wishlistElements;
    WebDriver driver;

    public WishlistPage(WebDriver driver) {
        PageFactory.initElements(driver, this);

        this.driver = driver;
    }


    public ProductDetailsPage clickOnViewBtn() {
        btnView.click();
        return new ProductDetailsPage(driver);
    }

    public WishlistPage clickOnDeleteBtn() {
        btnDelete.click();
        return this;
    }

    public int getWishlist() {
        return wishlistElements.size();
    }
}
