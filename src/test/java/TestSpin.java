import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by vladimir.kirpichenko on 22.02.2017.
 */
public class TestSpin extends TestBase {

    //public static String[] LANGUAGES = {"bg", "br", "cn", "cs", "da", "de", "el", "en", "es", "et", "fi", "fr", "hr", "hu", "it", "nl", "no", "pl", "pt", "ro", "ru", "sk", "sv", "tr"};
    public static String[] LANGUAGES = {"bg", "br"};

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver/chromedriver.exe");
    }


    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testerPage() throws Exception {
        openEnv();
        loginSpp("net_manager");

        for (String lang : LANGUAGES) {
            loginTesterPage("ivan1");
            chooseLang(lang);
            launchGame("pyramid_not_mobile");
            waitGameLoaded();
            closeFSS();
            float cashBefore = getCash();
            makeSpin();
            waitFor("idle");
            Thread.sleep(500);
            float cashAfter = getCash();
            float diff = cashBefore - getBet() + getWin();
            Assert.assertEquals("Assert cash failed", diff, cashAfter, 0.009);


            //Assert difference
            System.out.println("Before = " + cashBefore);
            System.out.println("After = " + cashAfter);
            System.out.println(cashAfter - cashBefore);
            logout();
        }

    }

}
