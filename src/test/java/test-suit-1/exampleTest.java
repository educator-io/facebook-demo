import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class exampleTest extends BaseTest {
    private WebDriver driver;
    private By emailFl = By.id("email");
    private By passFl = By.id("pass");
    private By loginBtn = By.id("loginbutton");

    private By myNameLink = By.xpath("//span[text()='Mark']");
    private By profilePhotoImg = By.xpath("//img[@alt='your Profile Photo']");
    private By homeBtn = By.xpath("//a[text()='Home']");
    private By userNavBtn = By.xpath("//div[@id='userNav']");
    private By postBox = By.xpath("//textarea[@name='xhpc_message']");
    private By presentation = By.xpath("//div[@role='dialog']//div[contains(@class, 'navigationFocus') and @role='presentation']");
    private By sendPresentationBtn = By.xpath("//button[@data-testid='react-composer-post-button']");
    private By threePointsOptionsBtn = By.xpath("//a[@data-testid='post_chevron_button']");
    private By deletePostLink = By.xpath("//a[@data-feed-option-name='FeedDeleteOption']");
    private By deleteBtn = By.xpath("//button[text()='Delete']");

    @BeforeTest
    public void beforeTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        driver = new ChromeDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void exampleTest() throws InterruptedException {
        driver.get("https://www.facebook.com");

        driver.findElement(emailFl).sendKeys("eshapovalov298@gmail.com");
        driver.findElement(passFl).sendKeys("Tester1234");
        driver.findElement(loginBtn).click();
        driver.findElement(myNameLink).click();

        Thread.sleep(2000);
        WebElement photoElement = driver.findElement(profilePhotoImg);
        Assert.assertTrue(photoElement.isDisplayed(), "The Profile page is not opened");

        driver.findElement(homeBtn).click();

        Thread.sleep(2000);
        WebElement element2 = driver.findElement(userNavBtn);
        Assert.assertTrue(element2.isDisplayed(), "The Home page is not opened");

        driver.findElement(postBox).click();

        Thread.sleep(2000);
        WebElement element = driver.findElement(presentation);
        Actions act = new Actions(driver);
        act.moveToElement(element).click().sendKeys("Hello world " + Math.random()).build().perform();

        driver.findElement(sendPresentationBtn).click();

        Thread.sleep(2000);
        driver.findElement(threePointsOptionsBtn).click();

        Thread.sleep(2000);
        driver.findElement(deletePostLink).click();

        Thread.sleep(2000);
        driver.findElement(deleteBtn).click();

        Thread.sleep(5000);
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
