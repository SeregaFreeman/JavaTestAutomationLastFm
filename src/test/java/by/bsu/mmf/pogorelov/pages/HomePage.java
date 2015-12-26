package by.bsu.mmf.pogorelov.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPage{

    private final Logger logger = Logger.getLogger(HomePage.class);
    private final String BASE_URL = "http://last.fm/home";

    @FindBy(id = "site-search")
    private WebElement inputSearchQuery;

    @FindBy(className = "search-submit")
    private WebElement searchSubmitButton;

    @FindBy(className = "content-top-header")
    private WebElement searchResultText;

    /*----------------------------------------------------------------------------------*/

    public HomePage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage()
    {
        driver.navigate().to(BASE_URL);
    }

    public void search(String query)
    {
        inputSearchQuery.sendKeys(query);
        searchSubmitButton.click();
        logger.info("Search completed");
    }

    public String getSearchQueryResult(){return searchResultText.getText();}

}
