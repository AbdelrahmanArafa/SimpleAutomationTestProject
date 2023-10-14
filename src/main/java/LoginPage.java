import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver webDriver ;
    public LoginPage(WebDriver webDriver)
    {
        this.webDriver = webDriver;
    }

    public WebElement usernameEle()
    {
        By username = By.id("username");
        WebElement usernameEle = webDriver.findElement(username);
        return usernameEle;
    }

    public WebElement passwordEle()
    {
        By password = By.id("password");
        WebElement passwordEle = webDriver.findElement(password);
        return passwordEle;
    }


    public WebElement flashEle()
    {
        By flash = By.id("flash");
        WebElement flashEle = webDriver.findElement(flash);
        return flashEle;
    }


    public By logoutEle()
    {
        By logout = By.cssSelector("a[href=\"/logout\"]");
        return logout;
    }


    public void LoginSteps(String username , String password)
    {
        //Enter UserName using POM
        usernameEle().clear();
        usernameEle().sendKeys(username);

        //Enter Password using POM
        passwordEle().sendKeys(password);
        passwordEle().sendKeys(Keys.ENTER);

    }
}
