package by.bsu.mmf.pogorelov.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage
{
    private final Logger logger = Logger.getLogger(LoginPage.class);
    private final String BASE_URL = "https://secure.last.fm/login";

    @FindBy(id = "id_username")
    private WebElement inputUsername;

    @FindBy(id = "id_password")
    private WebElement inputPassword;

    @FindBy(name = "submit")
    private WebElement buttonSubmit;

    @FindBy(className = "auth-avatar-desktop")
    private WebElement authConfirm;

    public LoginPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage()
    {
        driver.navigate().to(BASE_URL);
    }

    public void login(String username, String password)
    {
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);
        buttonSubmit.click();
        logger.info("User logged in");
    }

    public boolean isLogged(){
        return (authConfirm.isDisplayed());
    }

    public String getLoggedInUserName()
    {
        return authConfirm.getAttribute("alt");
    }

}

