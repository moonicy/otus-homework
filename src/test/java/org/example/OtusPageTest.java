package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OtusPageTest {
    protected static WebDriver driver;
    private static Logger logger = LogManager.getLogger(OtusPageTest.class);

    @BeforeAll
    public static void setUp() {
        String driverName = System.getProperty("browser");
        String[] options = System.getProperty("options").split(";");
        driver = WebDriverFactory.create(driverName, options);
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
