import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OpenBrowser {
    WebDriver webDriver = new ChromeDriver();
    LoginPage loginPage ;

    @BeforeTest
    public void OpenBrowser() throws InterruptedException {
        webDriver.manage().window().maximize();
        Thread.sleep(3000);
        loginPage = new LoginPage(webDriver);
    }


    @Test
    public void ValidData()
    {
        webDriver.get("https://the-internet.herokuapp.com/login");

        loginPage.LoginSteps("tomsmith","SuperSecretPassword!");

        String ExpectedResult = "You logged into a secure area!";
        String ActualResult = loginPage.flashEle().getText();


        ///////////// Hard Assertions \\\\\\\\\
        // First Assertion
        Assert.assertEquals(ActualResult.contains(ExpectedResult),true);

        // Second Assertion
        Assert.assertTrue(webDriver.findElement(loginPage.logoutEle()).isDisplayed());

        // Third Assertion
        Assert.assertEquals(webDriver.getCurrentUrl(),"https://the-internet.herokuapp.com/secure");
    }


    @Test
    public void InValidData()
    {
        webDriver.get("https://the-internet.herokuapp.com/login");

        loginPage.LoginSteps("invalid","Super");

        String ExpectedResult = "Your username is invalid!";
        String ActualResult = loginPage.flashEle().getText();

        ///////////// Hard Assertions \\\\\\\\\
        // First Assertion
        Assert.assertEquals(ActualResult.contains(ExpectedResult),true);
    }


    @AfterTest
    public void CloseBrowser() throws InterruptedException {
        Thread.sleep(3000);
        webDriver.quit();
    }
}
