import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OtusPageTest {
    protected static WebDriver driver;
    private static Logger logger = LogManager.getLogger(OtusPageTest.class);

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Driver up");
    }

    @Test
    public void openPage() {
        driver.get("https://otus.ru/");
        logger.info("Open page Otus.ru");
        assertEquals(driver.getTitle(), "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям");
    }

    @AfterAll
    public static void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
