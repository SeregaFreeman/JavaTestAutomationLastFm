package by.bsu.mmf.pogorelov;

import by.bsu.mmf.pogorelov.steps.Steps;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

public class LastFmTest {

    private Steps steps;

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
        this.steps.loginLastFm("SerP_94", "psdaalastfm94");
        Assert.assertTrue(steps.isLoggedIn("SerP_94"));
    }

    @Test(
            description = "Seacrh on site"
    )
    public void oneCanSearchOnLastFm() {
        this.steps.siteSearch("Muse");
        Assert.assertTrue(steps.isQueryCompleted());
    }

    @Test(
            description = "Post comment on the wall"
    )
    public void oneCanPostToLastFm() {
        this.steps.loginLastFm("SerP_94", "psdaalastfm94");
        this.steps.postComment("My test comment");
        Assert.assertTrue(steps.isCommentPosted("My test comment", "SerP_94"));
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
        this.steps.loginLastFm("SerP_94", "psdaalastfm94");
        this.steps.deleteSong();
        Assert.assertTrue(steps.isDeleted());
    }

    @Test(
            description = "Logout from Last.fm"
    )
    public void oneCanLogoutFromLastFm() {
        this.steps.loginLastFm("SerP_94", "psdaalastfm94");
        Assert.assertTrue(steps.isLoggedIn("SerP_94"));
        this.steps.logoutFromLastFm();
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