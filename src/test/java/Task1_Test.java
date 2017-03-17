
// Make it without sleeps                       *

// Use xpathes                                  *

// Load game, click FSS and click spin          *

// Refactoring: do not use diver in test

// Use js executor (event gameLoaded)

// Make it full screen                           *

// Use Game rules button clickable               *


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Task1_Test {
    public static WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "chromedriver/chromedriver.exe");
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void testerPage() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("http://sta-kiv-gt1-setup01-spp-01.nix.cydmodule.com:8080/admin/tester.jsp");
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, 20);
         WebElement login = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='content']/table/tbody/tr/td/form/table/tbody/tr[1]/td[2]/input")));
        //WebElement login = wait.until(ExpectedConditions.elementToBeClickable(By.name("login")));
        login.sendKeys("net_manager");


        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='content']/table/tbody/tr/td/form/table/tbody/tr[2]/td[2]/input")));
        password.sendKeys("net_manager");


        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='content']/table/tbody/tr/td/form/table/tbody/tr[3]/td/input")));
        button.click();


        WebElement userName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='defaultForm']/input[1]")));
        userName.sendKeys("ivan");


        WebElement loginUserButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='defaultForm']/input[3]")));
        loginUserButton.click();


        WebElement runPyramid = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'pyramid_not_mobile')]")));
        runPyramid.click();

        /*WebElement soundSettings = driver.findElement(By.id("soundSettingsButton"));
        soundSettings.click();*/


        WebElement canvas = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='canvasAnimationManager']")));
       // Thread.sleep(2000);

       // WebElement soundSettings = driver.findElement(By.id("soundSettingsButton"));
       // soundSettings.click();

        // Close FSS
        new Actions(driver)
                .moveToElement(canvas, 960, 735)
                .click()
                .perform();


        //WebElement gameSettings = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='gameSettingsSettingsButton']")));
        //gameSettings.click();

        // Waiting until game rules button becomes enabled and therefore spin button is active:
        WebElement Cash = driver.findElement(By.cssSelector(".value"));
        String bablo = Cash.getText();
        System.out.println(bablo);



        WebElement Bet = driver.findElement(By.xpath(".//*[@id='gameFooter']/xhtml:div/xhtml:div[1]/xhtml:span[2]/xhtml:div/xhtml:span[2]"));
        String cashBet = Bet.getText();
        System.out.println(cashBet);


        WebElement gameRules = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class = 'interface-desktopSettingsMenu_button interface-gameRules_icon_uri']")));


        // Click spin button

        new Actions(driver)
                .moveToElement(canvas, 880, 885)
                .click()
                .click()
                .perform();
        Thread.sleep(10000);



        driver.navigate().back();

        WebElement logoutUserButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='defaultForm']/input[1]")));
        logoutUserButton.click();
        Thread.sleep(1000);
    }
}
