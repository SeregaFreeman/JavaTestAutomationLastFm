package by.bsu.mmf.pogorelov.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserPage extends AbstractPage{

    private final Logger logger = Logger.getLogger(UserPage.class);
    private final String UserName = "Serp_94";
    private final String BASE_URL = "http://last.fm/user/"+UserName;

    @FindBy(id = "site-search")
    private WebElement inputSearchQuery;

    @FindBy(className = "search-submit")
    private WebElement searchSubmitButton;

    @FindBy(className = "content-top-header")
    private WebElement searchResultText;

    @FindBy(css="div[class='dropdown-hoverable site-auth dropdown--js-active']")
    private WebElement hoverDropdown;
    @FindBy(css="a[class='dropdown-menu-hoverable-item js-logout-button']")
    private WebElement logoutButton;

    @FindBy(className = "comment-input")
    private WebElement commentField;

    @FindBy(className = "shout-btn")
    private WebElement commentButton;

    @FindBy(xpath="//*[@id='comments-container']/li/div/div/div[3]")
    private WebElement postedCommentText;

    @FindBy(xpath="//*[@id='comments-container']/li/div/div/div[1]")
    private WebElement postedCommentUserName;

    @FindBy(css="a[class='auth-link auth-login-link']")
    private WebElement loginlink;

    /*----------------------------------------------------------------------------------*/

    public UserPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage()
    {
        driver.navigate().to(BASE_URL);
    }

    public void postComment(String comment){
        commentField.sendKeys(comment);
        commentButton.click();
        logger.info("Comment posted");
    }

    public String getPostedCommentText(){
        return(postedCommentText.getText());
    }

    public String getPostedCommentUsername(){
        return(postedCommentUserName.getText());
    }

    public void logout(){
        Actions action = new Actions(driver);
        action.moveToElement(hoverDropdown).perform();
        logoutButton.click();
        logger.info("User logged out");
    }

    public boolean checkLoginLink(){
        return(loginlink.isDisplayed());
    }

}