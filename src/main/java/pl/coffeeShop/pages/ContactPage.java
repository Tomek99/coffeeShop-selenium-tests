package pl.coffeeShop.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.coffeeShop.utils.SeleniumHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ContactPage {
    @FindBy(xpath = "//header[text()='contact us']")
    private WebElement header;
    @FindBy(name = "fullName")
    private List<WebElement> nameInputs;
    @FindBy(name = "email")
    private WebElement email_input_one;
    @FindBy(name = "number")
    private WebElement phone_input_two;
    @FindBy(id = "message0")
    private WebElement textarea_comment_one;
    @FindBy(id = "message1")
    private WebElement textarea_comment_two;
    @FindBy(id = "btnFormContact23")
    private WebElement btnFormContact1;
    @FindBy(id = "btnFormContact1")
    private WebElement btn_form_contact2;

    @FindBy(className = "ErrMessage_errorText__1OrwW")
    List<WebElement> errors;


    WebDriver driver;

    public ContactPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    public ContactPage fillFirstForm(String name, String email, String comment) {
        nameInputs.get(0).sendKeys(name);
        email_input_one.sendKeys(email);
        textarea_comment_one.sendKeys(comment);
        return this;

    }

    public ContactPage fillSecondForm(String name, String phone, String comment) {
        nameInputs.get(1).sendKeys(name);
        phone_input_two.sendKeys(phone);
        textarea_comment_two.sendKeys(comment);
        return this;
    }

    public ContactPage handleFirstForm() {
        btnFormContact1.click();
        return this;
    }

    public ContactPage handleSecondForm() {
        btn_form_contact2.click();
        return this;
    }

    public List<String> getErrors() {

        return errors.stream()
                .map(WebElement::getText)
                .toList().stream()
                .map(String::trim)
                .filter(trim -> !trim
                        .isEmpty())
                .collect(Collectors.toList());
    }

    public String handleAlert() {
        SeleniumHelper.waitForAlert(driver);
        String text = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        return text;

    }

    public String getHeaderText() {
        return header.getText();
    }
}
