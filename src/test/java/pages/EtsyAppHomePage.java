package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class EtsyAppHomePage {

//First we call the no-parameter Constructor, inside we get the driver from our driver class, and using PageFactory.initElements(driver, this) we initialize elements. @FindBy is used to find the elements.
    public EtsyAppHomePage() {
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "global-enhancements-search-query")
    public WebElement searchBox;

    @FindBy(id="catnav-primary-link-10855")
    public WebElement jeweleryAndAccesories;

    @FindBy(xpath = "//span[@class='wt-display-table-cell wt-text-left-xs']")
    public WebElement endOfYearSalesEvent;

    @FindBy(id="catnav-primary-link-10923")
    public WebElement clothingAndShoes;

    @FindBy(id="catnav-primary-link-891")
    public WebElement homeAndLiving;

    @FindBy(id="catnav-primary-link-10983")
    public WebElement weddingAndParty;

    @FindBy(id="catnav-primary-link-11049")
    public WebElement toysAndEntertainment;

    @FindBy(id="catnav-primary-link-66")
    public WebElement artAndCollectibles;

    @FindBy(id="catnav-primary-link-562")
    public WebElement craftSupplies;

    @FindBy(id="catnav-primary-link--10")
    public WebElement giftsAndGiftCards;
}
