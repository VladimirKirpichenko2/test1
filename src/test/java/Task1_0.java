
// Make it without sleeps

// Use xpathes

// Load game, click FSS and click spin

// Refactoring: do not use diver in test


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Task1_0 {
    public static WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "chromedriver/chromedriver.exe");
    }

    @After
    public void tearDown(){
        driver.close();
    }

    @Test
    public void testerPage() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("http://sta-kiv-gt1-setup01-spp-01.nix.cydmodule.com:8080/admin/tester.jsp");
        Thread.sleep(500);

        WebElement login = driver.findElement(By.cssSelector("input[type='text'][name='login']"));
        login.sendKeys("net_manager");
        Thread.sleep(500);

        WebElement password = driver.findElement(By.cssSelector("input[type='password'][name='password']"));
        password.sendKeys("net_manager");
        Thread.sleep(500);

        WebElement button = driver.findElement(By.cssSelector("input[type='submit'][value='Login']"));
        button.click();
        Thread.sleep(500);

        WebElement userName = driver.findElement(By.cssSelector("input[type='text'][name='username']"));
        userName.sendKeys("ivan");
        Thread.sleep(500);

        WebElement loginUserButton = driver.findElement(By.cssSelector("input[type='submit'][value='login']"));
        loginUserButton.click();
        Thread.sleep(2000);

        WebElement runPyramid = driver.findElement(By.linkText("pyramid_not_mobile"));
        Thread.sleep(500);
        runPyramid.click();
        Thread.sleep(10000);

        driver.navigate().back();
        Thread.sleep(2000);

        WebElement logoutUserButton = driver.findElement(By.cssSelector("input[type='submit'][value='logout']"));
        logoutUserButton.click();
        Thread.sleep(5000);


    }
}



    /*public void GameEvent(WebDriver driver) {
        this.driver = driver;
        this.initEventsMap();
    }


    public void waitFor(String eventKey) throws Exception {

        String rezult = "";
        String eventName = eventsMap.get(eventKey);

        JavascriptExecutor js = null;
        if (driver instanceof JavascriptExecutor) {
            js = (JavascriptExecutor) driver;
        } // else throw...

        js.executeScript("window.TEST_EVENT_FIRED = false;" +
                "window.TEST_EVENT = new Sys.Observable();" +
                "window.TEST_EVENT.addListener('" + eventName + "', " +
                "function(){" +
                "window.TEST_EVENT.removeListener('" + eventName + "');" +
                "window.TEST_EVENT_FIRED = true; " +
                "});");


        while (!(rezult == "true")) {
            rezult = js.executeScript("return window.TEST_EVENT_FIRED").toString();
            Thread.sleep(100);
        }
    }

    public void waitGameLoaded() throws Exception {
        this.waitFor("gameLoaded");
    }
    private void initEventsMap() {
        eventsMap.put("gameLoaded", "notify:stateHandler.leavingSetupGameState");
        eventsMap.put("roundStarted", "notify:spin.roundStart");
        eventsMap.put("paytableShown", "notify:paytable.shown");
        eventsMap.put("bigWinStarted", "notify:stateHandler.enteringBigWinState");
        eventsMap.put("AUTOPLAYSTOP", "notify:autoPlayer.stopped");
        eventsMap.put("enteringFeatureSplashState", "notify:stateHandler.enteringFeatureSplashState");
        eventsMap.put("spinAnimationComplete", "notify:spin.spinAnimationComplete");
        eventsMap.put("showingDialog", "notify.dialogWindow.showingDialog");

    }
*/