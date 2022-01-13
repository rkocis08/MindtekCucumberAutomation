package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.TestSheepHomePage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

public class TestSheepSteps {
    WebDriver driver = Driver.getDriver();
    TestSheepHomePage testSheepHomePage = new TestSheepHomePage();

    @Given("user navigates toTestSheepApplication")
    public void user_navigates_toTestSheepApplication() {
        driver.get(ConfigReader.getProperty("TestSheepURL"));

    }

    @When("user enters {string} as first number")
    public void user_enters_as_first_number(String int1) {
        testSheepHomePage.number1.sendKeys(int1.toString());// we can implement to string because this is an object not a primitive

    }

    @When("user enters {string} as second number")
    public void user_enters_as_second_number(String int1) {
        testSheepHomePage.number2.sendKeys(int1.toString());
    }

    @When("user selects {string} operator")
    public void user_selects_add_operator(String operator) {
        BrowserUtils.selectByText(testSheepHomePage.operatorDropDown, operator);
    }

    @When("user clicks on Calculate Button")
    public void user_clicks_on_Calculate_Button() {
        testSheepHomePage.calculateButton.click();
    }

    @Then("user validates output is {int}")
    public void user_validates_output_is(Integer int1) {
        String actualAnswer = testSheepHomePage.answerField.getAttribute("value"); // you can't do text because this is a generated value attribute not a gettext()
        Assert.assertEquals(int1.toString(), actualAnswer);// gotta convert the int object to string, so we can Assert
    }


      @Then("user validates {string} error message")
    public void userValidatesErrorMessage(String string) {
        String actualMessage = testSheepHomePage.errorMessage.getText();
        Assert.assertEquals(string, actualMessage);
    }
}
