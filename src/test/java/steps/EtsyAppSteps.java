package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.EtsyAppHomePage;
import pages.EtsyAppSearchPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.List;

public class EtsyAppSteps {

    WebDriver driver = Driver.getDriver();
    EtsyAppHomePage etsyAppHomePage = new EtsyAppHomePage();
    EtsyAppSearchPage etsyAppSearchPage = new EtsyAppSearchPage();

    @Given("user navigates to Etsy application")
    public void user_navigates_to_Etsy_application() {
        driver.get(ConfigReader.getProperty("EtsyURL"));

    }

    @When("user searches for {string}")
    public void
    user_searches_for(String item) {
        etsyAppHomePage.searchBox.sendKeys(item + Keys.ENTER);

    }

    @When("user applies filter over {int}")
    public void user_applies_filter_over(Integer price) {
        etsyAppSearchPage.filterButton.click();
        etsyAppSearchPage.priceRadioButton.click();
        etsyAppSearchPage.applyButton.click();
    }

    @Then("user validates that items prices are over {int}")
    public void user_validates_that_items_prices_are_over(Integer price) throws InterruptedException {
        Thread.sleep(3000);
        List<WebElement> prices = etsyAppSearchPage.prices; //let's store all those values in to a webelement, so it's easier to work with

        for (WebElement element : prices) {
            System.out.println(element.getText());//"32,402.00"
            String priceStr = element.getText().replace(",", ""); //"32,402.00"-->32402.00
            double doublePrice = Double.parseDouble(priceStr);
            System.out.println(doublePrice);

            Assert.assertTrue(doublePrice >= price);
        }

    }

    @Then("user validates search result items contain keyword {string}")
    public void user_validates_search_result_items_contain_keyword(String item) throws InterruptedException {
        Thread.sleep(3000);
        List<WebElement> itemNames = etsyAppSearchPage.itemNames; //storing the webelements into an object in this class

        for (WebElement element : itemNames) {
            if (element.getText().toLowerCase().contains(item)) {
                StringBuilder newItemName = new StringBuilder();
                newItemName.append(element.getText());
                System.out.println(newItemName);
                Assert.assertTrue(newItemName.toString().toLowerCase().contains(item));
            }
        }

    }

    @When("user clicks on {string} section")
    public void userClicksOnSection(String section) {

        if (section.equalsIgnoreCase("Jewelry and Accessories")) {
            etsyAppHomePage.jeweleryAndAccesories.click();
        } else if (section.equalsIgnoreCase("End of Year Sales Event")) {
            etsyAppHomePage.endOfYearSalesEvent.click();
        } else if (section.equalsIgnoreCase("Clothing and Shoes")) {
            etsyAppHomePage.clothingAndShoes.click();
        } else if (section.equalsIgnoreCase("Home and Living")) {
            etsyAppHomePage.homeAndLiving.click();
        } else if (section.equalsIgnoreCase("Wedding and Party")) {
            etsyAppHomePage.weddingAndParty.click();
        } else if (section.equalsIgnoreCase("Toys and Entertainment")) {
            etsyAppHomePage.toysAndEntertainment.click();
        } else if (section.equalsIgnoreCase("Art and Collectibles")) {
            etsyAppHomePage.artAndCollectibles.click();
        } else if (section.equalsIgnoreCase("Craft Supplies and Tools")) {
            etsyAppHomePage.craftSupplies.click();
        } else if (section.equalsIgnoreCase("Gift and Gift Cards")) {
            etsyAppHomePage.giftsAndGiftCards.click();
        }
    }

    @Then("user validates title is {string}")
    public void userValidatesTitleIs(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);

    }

}

