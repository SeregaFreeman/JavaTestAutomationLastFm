package by.bsu.mmf.pogorelov.steps;

import by.bsu.mmf.pogorelov.pages.HomePage;
import by.bsu.mmf.pogorelov.pages.LoginPage;
import by.bsu.mmf.pogorelov.pages.UserLibraryPage;
import by.bsu.mmf.pogorelov.pages.UserPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Steps
{

    private WebDriver driver;

    private final Logger logger = Logger.getLogger(Steps.class);
    private final String QUERY = "Muse";


    public void initBrowser()
    {
        driver = new FirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        logger.info("Browser started");
    }

    public void closeDriver()
    {
        driver.quit();
        logger.info("Browser closed");
    }

    /*-------------------------------------------------------------------*/
    public void loginLastFm(String username, String password)
    {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(username, password);
    }

    public void logoutFromLastFm(){
        UserPage userPage = new UserPage(driver);
        userPage.openPage();
        userPage.logout();
    }

    public void siteSearch(String query){
        HomePage homePage = new HomePage(driver);
        homePage.openPage();
        homePage.search(query);
    }

    public void postComment(String comment){
        UserPage userPage = new UserPage(driver);
        userPage.openPage();
        userPage.postComment(comment);
    }

    /*public void likeSong(){
        UserLibraryPage userLibraryPage = new UserLibraryPage(driver);
        userLibraryPage.openPage();
        userLibraryPage.likeSong();
    }
    */

    public void deleteSong(){
        UserLibraryPage userLibraryPage = new UserLibraryPage(driver);
        userLibraryPage.openPage();
        userLibraryPage.deleteSong();
    }

    /*------------------------------------------------------------------------------------------*/

    public boolean isQueryCompleted(){
        HomePage homePage = new HomePage(driver);
        return (homePage.getSearchQueryResult().contains(QUERY));
    }

    public boolean isLoggedIn(String username)
    {
        LoginPage loginPage = new LoginPage(driver);
        return (loginPage.getLoggedInUserName().equals(username));
    }

    public boolean isCommentPosted(String comment, String username){
        UserPage userPage = new UserPage(driver);
        return ((userPage.getPostedCommentText().equals(comment))&&(userPage.getPostedCommentUsername().equals(username)));
    }

    public boolean isLoggedOut(){
        UserPage userPage = new UserPage(driver);
        //loginPage.openPage();
        return (userPage.checkLoginLink());
    }

    /*public boolean isLiked(){
        UserLibraryPage userLibraryPage = new UserLibraryPage(driver);
        openNewTab();
        userLibraryPage.openFavpage();
        return(userLibraryPage.checkFavPage());
    }*/

    public boolean isDeleted(){
        UserLibraryPage userLibraryPage = new UserLibraryPage(driver);
        openNewTab();
        userLibraryPage.openPage();
        String check = userLibraryPage.getSongTitle();
        return(!(check.equals(userLibraryPage.deletedSong)));
    }

    /*--------------------------------------------------------------------------------------------------*/
    private void openNewTab(){
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL+"t");
    }
    private void refreshTab(){
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL+"r");
    }
}