import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class TestBase {
    public static WebDriver driver;
    private Map<String, String> eventsMap = new HashMap<String, String>();


    public void openEnv() {
        driver = new ChromeDriver();
        driver.get(Config.ENV_LINK);
        driver.manage().window().maximize();
    }

    public void loginSpp(String user) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement login = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='content']/table/tbody/tr/td/form/table/tbody/tr[1]/td[2]/input")));
        //WebElement login = wait.until(ExpectedConditions.elementToBeClickable(By.name("login")));
        login.sendKeys(user);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='content']/table/tbody/tr/td/form/table/tbody/tr[2]/td[2]/input")));
        password.sendKeys(user);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='content']/table/tbody/tr/td/form/table/tbody/tr[3]/td/input")));
        button.click();

    }

    public void loginTesterPage(String userTP) {
        WebDriverWait wait = new WebDriverWait(driver, 20);

        WebElement userName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='defaultForm']/input[1]")));
        userName.sendKeys(userTP);

        WebElement loginUserButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='defaultForm']/input[3]")));
        loginUserButton.click();
    }

    public void chooseLang(String lang) throws InterruptedException {
        //WebDriverWait wait = new WebDriverWait(driver, 20);
        Select language = new Select(driver.findElement(By.id("languageid")));
        language.selectByValue(lang);
        System.out.println(lang);
        Select language1 = new Select(driver.findElement(By.id("languageid")));
        WebElement selectedOption = language1.getFirstSelectedOption();
        System.out.println(selectedOption.getText());
    }


    public void launchGame(String gameName) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement runGame = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(gameName)));
        runGame.click();
        WebElement canvas = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='canvasAnimationManager']")));
    }

    public void closeFSS() {
        WebElement canvas = driver.findElement(By.xpath(".//*[@id='canvasAnimationManager']"));
        new Actions(driver)
                .moveToElement(canvas, 960, 735)
                .click()
                .perform();
    }

    public void makeSpin() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement canvas = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='canvasAnimationManager']")));
        WebElement gameRules = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class = 'interface-desktopSettingsMenu_button interface-gameRules_icon_uri']")));
        new Actions(driver)
                .moveToElement(canvas, 880, 885)
                .click()
                .perform();
        //Thread.sleep(10000);
    }

    public void logout() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 20);

        driver.navigate().back();

        WebElement logoutUserButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='defaultForm']/input[1]")));
        logoutUserButton.click();
        Thread.sleep(1000);
    }

    public float getCash() {
        WebElement Cash = driver.findElement(By.cssSelector(".value"));
        //System.out.println(Cash.getText());
        return toFloat(Cash.getText());
    }

    public float getBet() {
        WebElement Bet = driver.findElement(By.xpath(".//*[@id='gameFooter']/xhtml:div/xhtml:div[1]/xhtml:span[2]/xhtml:div/xhtml:span[2]"));
        //System.out.println(Bet.getText());
        return toFloat(Bet.getText());
    }
    public float getWin() {
        WebElement Bet = driver.findElement(By.xpath(".//*[@id='gameFooter']/xhtml:div/xhtml:div[1]/xhtml:span[3]/xhtml:div/xhtml:span[2]"));
        //System.out.println(Bet.getText());
        return toFloat(Bet.getText());
    }

    public float toFloat(String str) {
        return Float.parseFloat(str.substring(1).replace(",", ""));
    }


    public TestBase() {
        this.initEventsMap();
    }


    public void waitFor(String eventKey) throws Exception {

        String rezult = "";
        String eventName = eventsMap.get(eventKey);

        JavascriptExecutor js = null;
        if (driver instanceof JavascriptExecutor) {
            js = (JavascriptExecutor) driver;
        } // else throw...

        assert js != null;
        js.executeScript("window.TEST_EVENT_FIRED = false;" +
                "window.TEST_EVENT = new Sys.Observable();" +
                "window.TEST_EVENT.addListener('" + eventName + "', " +
                "function(){" +
                "window.TEST_EVENT.removeListener('" + eventName + "');" +
                "window.TEST_EVENT_FIRED = true; " +
                "});");

        while (!("true".equals(rezult))) {
            rezult = js.executeScript("return window.TEST_EVENT_FIRED").toString();
            Thread.sleep(500);
        }
    }

    public void waitGameLoaded() throws Exception {
        this.waitFor("gameLoaded");
    }

    private void initEventsMap() {
        eventsMap.put("gameLoaded", "notify:stateHandler.leavingSetupGameState");
        eventsMap.put("roundStarted", "notify:spin.roundStart");
        eventsMap.put("idle", "notify:stateHandler.enteringIdleState");
        eventsMap.put("paytableShown", "notify:paytable.shown");
        eventsMap.put("bigWinStarted", "notify:stateHandler.enteringBigWinState");
        eventsMap.put("AUTOPLAYSTOP", "notify:autoPlayer.stopped");
        eventsMap.put("enteringFeatureSplashState", "notify:stateHandler.enteringFeatureSplashState");
        eventsMap.put("spinAnimationComplete", "notify:spin.spinAnimationComplete");
        eventsMap.put("showingDialog", "notify.dialogWindow.showingDialog");
        eventsMap.put("showingDialog", "notify.dialogWindow.showingDialog");
        eventsMap.put("showingDialog", "notify.dialogWindow.showingDialog");

    }
}