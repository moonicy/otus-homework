package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public abstract class BaseTest {

    protected static String email;
    protected static String password;
    protected static WebDriver driver;
    private static Logger logger = LogManager.getLogger(BaseTest.class);

    private static final long IMPLICITLY_WAIT = 3;
    protected static final long EXPLICITLY_WAIT = 10;

    @BeforeAll
    public static void setUp() {
        initDriver();
    }

    @AfterAll
    public static void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }



    protected void clickWithJS(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", element);
    }

    protected WebElement getLinkElementByText(String text) {
        By linkElementByTextLocator = By.xpath(String.format("//a[text()=\"%s\"]", text));
        return driver.findElement(linkElementByTextLocator);
    }

    protected static void reInitDriver() {
        driver.quit();
        initDriver();
    }

    protected static void initDriver() {
        email = System.getProperty("email");
        password = System.getProperty("password");

        String driverName = System.getProperty("browser") == null
                ? Drivers.CHROME.toString()
                : System.getProperty("browser");
        String optionsString = System.getProperty("options");
        String[] options = optionsString == null ? new String[]{"--start-maximized"} : optionsString.split(";");
        driver = WebDriverFactory.create(driverName, options);
        driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT, TimeUnit.SECONDS);
        logger.info("Driver up");
    }
}
