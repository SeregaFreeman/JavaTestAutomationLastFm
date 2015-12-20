package by.bsu.mmf.pogorelov;

import by.bsu.mmf.pogorelov.steps.Steps;
import junit.framework.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

    @Test(
            description = "Login to Last.fm"
    )
    public void oneCanLoginLastFm() {
        this.steps.loginLastFm("SerP_94", "");
        Assert.assertTrue(steps.isLoggedIn("SerP_94"));
    }

    @Test(
            description = "Seacrh on site"
    )
    public void oneCanSearchOnLastFm() {
        this.steps.siteSearch("Muse");
        Assert.assertTrue(steps.isQueryCompleted());
    }

    @AfterMethod(
            description = "Stop Browser"
    )
    public void stopBrowser() {
        this.steps.closeDriver();
    }
}