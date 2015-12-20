package by.bsu.mmf.pogorelov.steps;

import by.bsu.mmf.pogorelov.pages.LoginPage;
import by.bsu.mmf.pogorelov.pages.HomePage;
import by.bsu.mmf.pogorelov.pages.UserLibraryPage;
import by.bsu.mmf.pogorelov.pages.UserPage;
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

    public void likeSong(){
        UserLibraryPage userLibraryPage = new UserLibraryPage(driver);
        userLibraryPage.openPage();
        userLibraryPage.likeSong();
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

    public boolean isLiked(){
        UserLibraryPage userLibraryPage = new UserLibraryPage(driver);
        userLibraryPage.openPage();
        return(userLibraryPage.getSongStatus().equals("Dislike"));
    }

}
