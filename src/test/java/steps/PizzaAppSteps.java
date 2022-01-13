package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.PizzaAppPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

import java.text.spi.BreakIteratorProvider;
import java.util.Map;
import java.util.PrimitiveIterator;

public class PizzaAppSteps {

    WebDriver driver = Driver.getDriver();
    Map<String,Object> data;
    PizzaAppPage pizzaAppPage = new PizzaAppPage();
    String cost;

    @Given("user navigates to pizza application")
    public void user_navigates_to_pizza_application() {
        driver.get(ConfigReader.getProperty("PizzaAppURL"));

    }

    @When("user creates pizza order with data")
    public void user_creates_pizza_order_with_data(DataTable dataTable) {
        data = dataTable.asMap(String.class, Object.class);
        // using this data print all values in console
        //for(Object object: data.values()){
        //  System.out.println(object);2
        BrowserUtils.selectByValue(pizzaAppPage.pizza1Box,data.get("Pizza").toString());   // in our pizza app feature file we have the "Small 6 slices - no toppings" which connects to the velea in out data tabel ocnnected to the key we gacve
        BrowserUtils.selectByValue(pizzaAppPage.toppings1Box,data.get("Toppings 1").toString());// the value would be a string so we must convert to string
        BrowserUtils.selectByValue(pizzaAppPage.toppings2Box, data.get("Toppings 2").toString());
        pizzaAppPage.pizza1QtyBox.sendKeys(data.get("Quantity").toString());
        pizzaAppPage.nameBox.sendKeys(data.get("Name").toString());
        pizzaAppPage.emailBox.sendKeys(data.get("Email").toString());
        pizzaAppPage.phoneBox.sendKeys(data.get("Phone").toString());
        if(data.get("Payment Type").toString().equalsIgnoreCase("Cash on Pickup")) {
            pizzaAppPage.cashpaymentRadioButton.click();
        }else if (data.get("Payment Type").toString().equalsIgnoreCase("Credit Card")){
            pizzaAppPage.ccpaymentRadioButton.click();
        }
        cost = pizzaAppPage.pizza1CostBox.getAttribute("value");// so we can fetch value that is generated for cost once we plug in quantity
        pizzaAppPage.placeOrderButton.click();
    }

    @Then("user Validates that order is validated with success message {string}")
    public void user_Validates_that_order_is_validated_with_success_message(String expectedMessage) {

    }

    @Then("user Validates that order is created with success message {string}{string}")
    public void user_Validates_that_order_is_validated_with_success_message(String success,String pizza) {

    }

    @Then("user Validates that order is created with success message {string} {string}")
    public void userValidatesThatOrderIsValidatedWithSuccessMessagePizza(String success,String pizza) {
        String expectedSuccesMessage= success + cost +" "+pizza;
        String actualSuccesMessage= pizzaAppPage.dialogWindow.getText();
        Assert.assertEquals(expectedSuccesMessage,actualSuccesMessage);
    }
}
