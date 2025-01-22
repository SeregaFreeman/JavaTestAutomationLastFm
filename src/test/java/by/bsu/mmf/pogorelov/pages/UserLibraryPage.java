package by.bsu.mmf.pogorelov.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserLibraryPage extends AbstractPage{

    private final Logger logger = Logger.getLogger(UserLibraryPage.class);
    private final String UserName = "";
    private final String BASE_URL = "http://last.fm/user/"+UserName+"/library";

    @FindBy(className = "love-button")
    private WebElement likeSongButton;

    @FindBy(className = "love-button-toggle")
    private WebElement likeSongButtonToggle;

    @FindBy(className = "love-button love-button--loved")
    private WebElement unlikeSongButton;

    @FindBy(xpath = ".//*[@id='mantle_skin']//table/tbody//td[3]/span/a")
    private WebElement songName;

    @FindBy(xpath = "//*[@id='mantle_skin']/div[4]/div/div[1]/section[1]/table/tbody/tr/td[2]/div/div[1]/form")
    private WebElement songCanBeLiked;

    @FindBy(xpath = ".//*[@id='user-loved-tracks-section']/table/tbody/tr[1]/td[3]/span/a")
    private WebElement favPageSongName;

    @FindBy(className = "chartlist-delete-button")
    private WebElement deleteButton;
    /*----------------------------------------------------------------------------------*/

    public String deletedSong;

    public UserLibraryPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage()
    {
        driver.navigate().to(BASE_URL);
        logger.info("User library page opened");
    }

    /*
    public void openFavpage(){
        driver.navigate().to("http://last.fm/user/SerP_94/loved");
    }

    public boolean checkFavPage(){
        return(getSongTitle().replace(" ", "").equals(getSongTitle().replace(" ", "")));
    }
    */

    public String getSongTitle(){
        String song = songName.getAttribute("title");
        return song;
    }

    /*public String getSongTitle(WebElement attr){
        String song = attr.getAttribute("title");
        return song;
    }

    public String getSongStatus(){
        String status;
        String str1 = getSongTitle().replace(" ", "");
        String str2 = songCanBeLiked.getAttribute("action").replace("/", "").replace("+", "").replace("music", "").replace("_", "—");
        if (str2.contains(str1+"love")){
            status = "Like";
        }
        else
            status = "Dislike";
        return status;
    }

    public void likeSong(){
        if(getSongStatus().equals("Like")){
            Actions action = new Actions(driver);
            action.moveToElement(likeSongButtonToggle).perform();
            likeSongButton.click();
        }
        logger.info("Song liked");
    }*/

    public void deleteSong(){
        deletedSong = getSongTitle();
        Actions action = new Actions(driver);
        action.moveToElement(deleteButton).perform();
        deleteButton.click();
    }
}