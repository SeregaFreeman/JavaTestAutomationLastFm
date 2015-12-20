package by.bsu.mmf.pogorelov.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractPage{

    private final String BASE_URL = "http://last.fm/home";

    @FindBy(id = "site-search")
    private WebElement inputSearchQuery;

    @FindBy(className = "search-submit")
    private WebElement searchSubmitButton;

    @FindBy(className = "content-top-header")
    private WebElement searchResultText;

    public MainPage(WebDriver driver)
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
    }

    public String getSearchQueryResult(){return searchResultText.getText();}
}
