import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class OpenBrowserSoft {
    WebDriver webDriver = new ChromeDriver();

    @BeforeTest
    public void OpenBrowser() throws InterruptedException {
        webDriver.manage().window().maximize();
        Thread.sleep(3000);
    }


    @Test
    public void ValidData()
    {
        webDriver.get("https://the-internet.herokuapp.com/login");
        webDriver.findElement(By.id("username")).clear();
        webDriver.findElement(By.id("username")).sendKeys("tomsmith");
        webDriver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        webDriver.findElement(By.id("password")).sendKeys(Keys.ENTER);
        String ExpectedResult = "You logged into a secure area!";
        String ActualResult = webDriver.findElement(By.id("flash")).getText();


        ///////////// Soft Assertions \\\\\\\\\
        SoftAssert softAssert = new SoftAssert();
        // First Assertion
        softAssert.assertEquals(ActualResult.contains(ExpectedResult),true);

        // Second Assertion
        softAssert.assertTrue(webDriver.findElement(By.cssSelector("a[href=\"/logout\"]")).isDisplayed());

        // Third Assertion
        softAssert.assertEquals(webDriver.getCurrentUrl(),"https://the-internet.herokuapp.com/secure");

        softAssert.assertAll();
    }


    @Test
    public void InValidData()
    {
        webDriver.get("https://the-internet.herokuapp.com/login");
        webDriver.findElement(By.id("username")).clear();
        webDriver.findElement(By.id("username")).sendKeys("invalid");
        webDriver.findElement(By.id("password")).sendKeys("Super");
        webDriver.findElement(By.id("password")).sendKeys(Keys.ENTER);

        String ExpectedResult = "Your username is invalid!";
        String ActualResult = webDriver.findElement(By.id("flash")).getText();
        Assert.assertEquals(ActualResult.contains(ExpectedResult),true);
    }


    @AfterTest
    public void CloseBrowser() throws InterruptedException {
        Thread.sleep(3000);
        webDriver.quit();
    }
}
