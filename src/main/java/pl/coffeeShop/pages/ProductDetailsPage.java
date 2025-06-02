package pl.coffeeShop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage {
    @FindBy(xpath = "//h1")
    private WebElement productName;

    WebDriver driver;

    public ProductDetailsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String getProductName(String productName) {
        String xpathExpression = "//h1[text()='" + productName + "']";
        return driver
                .findElement(By.xpath(xpathExpression))
                .getText();
    }

    public String productName() {
        return productName.getText();
    }
}
