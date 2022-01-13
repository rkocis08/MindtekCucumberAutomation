package steps;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jdk.swing.interop.SwingInterOpUtils;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import pages.EtsyAppHomePage;
import pages.WebOrdersHomePage;
import pages.WebOrdersLoginPage;
import pages.WebOrdersOrderPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.List;
import java.util.Map;

public class WebOrdersSteps {
    WebDriver driver = Driver.getDriver();
    WebOrdersLoginPage webOrdersLoginPage = new WebOrdersLoginPage();
    WebOrdersHomePage webOrdersHomePage = new WebOrdersHomePage();
    WebOrdersOrderPage webOrdersOrderPage = new WebOrdersOrderPage();
    int numberOfRows;
    List<Map<String, Object>> data;

    @Given("user navigates to the weborders application")
    public void user_navigates_to_the_weborders_application() {

        driver.get(ConfigReader.getProperty("WebOrdersURL"));
    }

    @When("user provides username {string} and password {string} clicks on login button")
    public void user_provides_username_and_password_clicks_on_login_button(String username, String password) {
        webOrdersLoginPage.username.sendKeys(username);
        webOrdersLoginPage.password.sendKeys(password);
        webOrdersLoginPage.loginButton.click();
    }

    @Then("user validates application is logged in")
    public void user_validates_application_is_logged_in() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Web Orders";

        Assert.assertEquals(expectedTitle, actualTitle);
        driver.quit();

    }

    @Then("user validates error message {string}")
    public void user_validates_error_message(String errorMessage) {
        String actualErrorMessage = webOrdersLoginPage.errorMessage.getText();
        Assert.assertEquals(errorMessage, actualErrorMessage);
        driver.quit();
    }

    @And("user click on order module")
    public void userClickOnOrderModule() {
        webOrdersHomePage.orderModule.click();
    }

    @And("user selects {string} with {int} quantity")
    public void userSelectsWithQuantityQuantity(String product, int quantity) {
        BrowserUtils.selectByValue(webOrdersOrderPage.ordersProductDropdown, product);
        webOrdersOrderPage.quantityBox.sendKeys(Keys.BACK_SPACE);
        webOrdersOrderPage.quantityBox.sendKeys(quantity + "" + Keys.ENTER);

    }

    @And("user validates total is calculated correctly for quantity {int}")
    public void userValidatesTotalIsCalculatedCorrectlyForQuantityQuantity(int quantity) {
        String pricePerUnit = webOrdersOrderPage.pricePerUnit.getAttribute("value");
        //System.out.println(pricePerUnit);
        int expectedTotal = 0;
        String discountAmount = webOrdersOrderPage.discountBox.getAttribute("value");
        int discountAmountInt = Integer.parseInt(discountAmount);
        int pricePerUnitInt = Integer.parseInt(pricePerUnit);

        if (discountAmountInt == 0) {
            expectedTotal = quantity * pricePerUnitInt;

        } else {
            expectedTotal = quantity * pricePerUnitInt;
            expectedTotal = expectedTotal - expectedTotal * discountAmountInt / 100;
        }
        String actualTotalAmount = webOrdersOrderPage.totalBox.getAttribute("value");
        int actualTotalAmountInt = Integer.parseInt(actualTotalAmount);
        Assert.assertEquals(expectedTotal, actualTotalAmountInt);
    }

    @When("user creates order with data")
    public void user_creates_order_with_data(DataTable dataTable) {
        data = dataTable.asMaps(String.class, Object.class);
        System.out.println(data.get(0).get("order"));
        BrowserUtils.selectByValue(webOrdersOrderPage.ordersProductDropdown, data.get(0).get("order").toString());
        webOrdersOrderPage.quantityBox.sendKeys(Keys.BACK_SPACE);
        webOrdersOrderPage.quantityBox.sendKeys(data.get(0).get("quantity").toString());
        webOrdersOrderPage.name.sendKeys(data.get(0).get("name").toString());
        webOrdersOrderPage.street.sendKeys(data.get(0).get("street").toString());
        webOrdersOrderPage.city.sendKeys(data.get(0).get("city").toString());
        webOrdersOrderPage.state.sendKeys(data.get(0).get("state").toString());
        webOrdersOrderPage.zip.sendKeys(data.get(0).get("zip").toString());
        webOrdersOrderPage.visaCheckBox.click();
        webOrdersOrderPage.cardNumber.sendKeys(data.get(0).get("cc").toString());
        webOrdersOrderPage.expireDate.sendKeys(data.get(0).get("expire date").toString());
        webOrdersOrderPage.processButton.click();
    }

    @Then("user validates success message {string}")
    public void user_validates_success_message(String expectedMessage) {
        String actualMessage = webOrdersOrderPage.succesMessage.getText();
        Assert.assertEquals(expectedMessage, actualMessage);
    }


    @And("user validates that the added to list of Orders")
    public void user_validates_that_the_added_to_list_of_orders() {
        webOrdersHomePage.viewAllOrdersModule.click();
        int numberOfRowsAfterCreation = webOrdersHomePage.numberOfRows.size();
        Assert.assertEquals(numberOfRowsAfterCreation - numberOfRows, 1);
        String actualName = webOrdersHomePage.firstNameInRow.getText();
        Assert.assertEquals(data.get(0).get("name").toString(),actualName);
        // homework is to do the rest of the validation for the rest of hte data in a row
    }

    @And("user counts number of orders in table")
    public void userCountsNumberOfOrdersInTable() {
        numberOfRows = webOrdersHomePage.numberOfRows.size();
    }
}
