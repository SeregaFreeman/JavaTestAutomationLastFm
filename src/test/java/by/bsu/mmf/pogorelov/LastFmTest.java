package by.bsu.mmf.pogorelov;

import by.bsu.mmf.pogorelov.steps.Steps;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LastFmTest {

    private Steps steps;
    private final Logger logger = Logger.getLogger(LastFmTest.class);
    private final String login = "SerP_94";
    private final String password = "psdaalastfm94";
    private final String searchQuery = "Muse";
    private final String testComment = "My test comment 1!";

    public LastFmTest() {
    }

    @BeforeMethod(
            description = "Init browser"
    )
    public void setUp() {
        this.steps = new Steps();
        this.steps.initBrowser();
    }

    /*-------------------------------------------------------------*/

    @Test(
            description = "Login to Last.fm"
    )
    public void oneCanLoginLastFm() {
        this.steps.loginLastFm(login, password);
        Assert.assertTrue(steps.isLoggedIn(login));
        boolean actualState = steps.isLoggedIn(login);
        if(actualState)
            logger.info("User "+ login + " is logged in successfully");
        else
            logger.error("User "+ login + " is not logged in");
        Assert.assertTrue(actualState);
    }

    @Test(
            description = "Seacrh on site"
    )
    public void oneCanSearchOnLastFm() {
        this.steps.siteSearch(searchQuery);
        boolean actualState = steps.isQueryCompleted(searchQuery);
        if(actualState)
            logger.info("Search is completed successfully");
        else
            logger.error("Search is not completed");
        Assert.assertTrue(steps.isQueryCompleted(searchQuery));
    }

    @Test(
            description = "Post comment on the wall"
    )
    public void oneCanPostToLastFm() {
        this.steps.loginLastFm(login, password);
        Assert.assertTrue(steps.isLoggedIn(login));

        this.steps.postComment(testComment);

        boolean actualState = steps.isCommentPosted(testComment, login);
        if(actualState)
            logger.info("Comment is posted successfully");
        else
            logger.error("Comment is not posted");

        Assert.assertTrue(steps.isCommentPosted(testComment, login));
    }

    /*@Test(
            description = "Like a song on Last.fm"
    )
    public void oneCanLikeSongOnLastFm() {
        this.steps.loginLastFm("SerP_94", "psdaalastfm94");
        this.steps.likeSong();
        Assert.assertTrue(steps.isLiked());
    }*/

    @Test(
            description = "Delete a song from user library on Last.fm"
    )
    public void oneCanDeleteSongOnLastFm() {
        this.steps.loginLastFm(login, password);
        Assert.assertTrue(steps.isLoggedIn(login));

        this.steps.deleteSong();
        boolean actualState = steps.isSongDeleted();
        if(actualState)
            logger.info("Song is deleted successfully");
        else
            logger.error("Song is not deleted");

        Assert.assertTrue(steps.isSongDeleted());
    }

    @Test(
            description = "Logout from Last.fm"
    )
    public void oneCanLogoutFromLastFm() {
        this.steps.loginLastFm(login, password);
        Assert.assertTrue(steps.isLoggedIn(login));
        this.steps.logoutFromLastFm();

        boolean actualState = steps.isLoggedOut();
        if(actualState)
            logger.info("User is logged out successfully");
        else
            logger.error("User is not logged out");

        Assert.assertTrue(steps.isLoggedOut());
    }

    /*------------------------------------------------------------------------*/
    @AfterMethod(
            description = "Stop Browser"
    )
    public void stopBrowser() {
        this.steps.closeDriver();
    }
}