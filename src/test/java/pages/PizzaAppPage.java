package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.Driver;

public class PizzaAppPage {

    public PizzaAppPage() {
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="pizza1Pizza")
    public WebElement pizza1Box;

    @FindBy(css = ".toppings1")
    public WebElement toppings1Box;

    @FindBy(css = ".toppings2")
    public WebElement toppings2Box;

    @FindBy(id = "pizza1Qty")
    public WebElement pizza1QtyBox;

    @FindBy(id = "pizza1Cost")
    public WebElement pizza1CostBox;

    @FindBy(id = "name")
    public WebElement nameBox;

    @FindBy(id = "email")
    public WebElement emailBox;

    @FindBy(id = "phone")
    public WebElement phoneBox;

    @FindBy(id = "cashpayment")
    public WebElement cashpaymentRadioButton;

    @FindBy(id = "ccpayment")
    public WebElement ccpaymentRadioButton;

    @FindBy(id = "placeOrder")
    public WebElement placeOrderButton;

    @FindBy(tagName = "p")
    public WebElement dialogWindow;
;
}
