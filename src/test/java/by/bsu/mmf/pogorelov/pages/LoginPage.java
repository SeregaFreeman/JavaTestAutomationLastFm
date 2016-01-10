package by.bsu.mmf.pogorelov.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

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
    private List<WebElement> authConfirm;

    public LoginPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage()
    {
        driver.navigate().to(BASE_URL);
        logger.info("Login page opened");
    }

    public void login(String username, String password)
    {
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);
        buttonSubmit.click();
    }

    public boolean isLogged(){
        return (!authConfirm.isEmpty());
    }

    public String getLoggedInUserName()
    {
        if (isLogged())
            return authConfirm.get(0).getAttribute("alt");
        else
            return null;
    }

}