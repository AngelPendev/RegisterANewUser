import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class RegisterANewUser {
    WebDriver driver;
    @BeforeMethod

    public void setUp() {
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://shop.pragmatic.bg/");


    }
    @Test

    public void registerAnAccount(){
        WebElement MyAccountButton = driver.findElement(By.xpath("//span[text()='My Account']"));
        MyAccountButton.click();
        WebElement RegisterAccount = driver.findElement(By.xpath("//*[text()='Register']"));
        RegisterAccount.click();
        driver.findElement(By.id("input-firstname")).sendKeys("Angel");
        driver.findElement(By.id("input-lastname")).sendKeys("Pendev");
        String prefix = RandomStringUtils.randomAlphanumeric(10);
        String suffix = RandomStringUtils.randomAlphabetic(4);
        String domain = RandomStringUtils.randomAlphabetic(3);
        String email = prefix + "@" + suffix + "." + domain;
        driver.findElement(By.id("input-email")).sendKeys(email);
        String phone = RandomStringUtils.randomNumeric(10);
        driver.findElement(By.id("input-telephone")).sendKeys(phone);
        String password=RandomStringUtils.randomAlphanumeric(8);
        driver.findElement(By.id("input-password")).sendKeys(password);
        driver.findElement(By.id("input-confirm")).sendKeys(password);
        driver.findElement(By.xpath("//input[@name='agree']")).click();
        driver.findElement(By.xpath("//*[@value='Continue']")).click();
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement SuccessTextNotification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content > h1")));
        String ActualSuccessText = SuccessTextNotification.getText();
        Assert.assertEquals(ActualSuccessText,"Your Account Has Been Created!");


    }

   @AfterMethod

   public void tearDown() {
       driver.quit();
    }
}
