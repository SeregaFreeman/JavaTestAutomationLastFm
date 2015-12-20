package by.bsu.mmf.pogorelov.steps;

import by.bsu.mmf.pogorelov.pages.LoginPage;
import by.bsu.mmf.pogorelov.pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;
import java.lang.*;
//import org.apache.log4j.Logger;

public class Steps
{

    private WebDriver driver;

    private final String QUERY = "Muse";

    public void initBrowser()
    {
        driver = new FirefoxDriver();

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //logger.info("Browser started");
    }

    public void closeDriver()
    {
        driver.quit();
    }

    public void loginLastFm(String username, String password)
    {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(username, password);
    }

    public void siteSearch(String query){
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.search(query);
    }

    public boolean isQueryCompleted(){
        MainPage mainPage = new MainPage(driver);
        return (mainPage.getSearchQueryResult().contains(QUERY));
    }

    public boolean isLoggedIn(String username)
    {
        LoginPage loginPage = new LoginPage(driver);
        return (loginPage.getLoggedInUserName().equals(username));
    }

}
