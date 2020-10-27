package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OtusPageTest {
    protected static WebDriver driver;
    private static Logger logger = LogManager.getLogger(OtusPageTest.class);

    private static final String SAMSUNG = "Samsung";
    private static final String XIAOMI = "Xiaomi";

    @BeforeAll
    public static void setUp() {
        String driverName = System.getProperty("browser") == null
                ? Drivers.CHROME.toString()
                : System.getProperty("browser");
        String optionsString = System.getProperty("options");
        String[] options = optionsString == null ? new String[]{"--start-maximized"} : optionsString.split(";");
        driver = WebDriverFactory.create(driverName, options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        logger.info("Driver up");
    }

    @Test
    public void openPage() {
        driver.get("https://otus.ru/");
        logger.info("Open page Otus.ru");
        assertEquals(driver.getTitle(), "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям");
    }

    @Test
    public void yandexMarketTest() {
        driver.get("https://market.yandex.ru/");
        logger.info("Opened Yandex.Market");

        try {
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'какой товар вам нужен')]")));
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[contains(text(), 'какой товар вам нужен')]")));
        } catch (Exception e) {
            logger.info("Infobox not found. It's OK!");
        }

        driver.findElement(By.cssSelector("a[href=\"/catalog--elektronika/54440\"]")).click();
        logger.info("Opened page Electronics");
        driver.findElement(By.xpath("//ul[@data-autotest-id='subItems']//a[text()='Смартфоны']")).click();
        logger.info("Opened page Smartfons");

        filterBy(SAMSUNG);
        filterBy(XIAOMI);

        driver.findElement(By.cssSelector("button[data-autotest-id=\"dprice\"]")).click();
        logger.info("Sorted by price");

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@data-zone-name=\"snippetList\"]/../div[2]")));

        String firstSamsung = getArticleName(SAMSUNG);
        addElementToEquals(SAMSUNG);

        assertNotNull(driver.findElement(By.xpath("//div[@data-apiary-widget-name=\"@market/PopupInformer\"]/div")));

        String firstXiaomi = getArticleName(XIAOMI);
        addElementToEquals(XIAOMI);

        assertNotNull(driver.findElement(By.xpath("//div[@data-apiary-widget-name=\"@market/PopupInformer\"]/div")));

        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href=\"/my/compare-lists\"]")));
        driver.findElement(By.xpath("//a[@href=\"/my/compare-lists\"]")).click();

        assertNotNull(driver.findElement(By.xpath(String.format("//a[text()=\"%s\"]", firstSamsung))));
        logger.info("Found Samsung");
        assertNotNull(driver.findElement(By.xpath(String.format("//a[text()=\"%s\"]", firstXiaomi))));
        logger.info("Found Xiaomi");
    }

    @AfterAll
    public static void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private String getArticleName(String name) {
        return driver
                .findElement(
                        By.xpath(
                                String.format("(//article[@data-autotest-id=\"product-snippet\"]" +
                                        "//span[contains(text(), \"%s\")])[1]", name)
                        )
                )
                .getText();

    }

    private void filterBy(String name) {
        driver
                .findElement(By.xpath(String.format("//fieldset[@data-autotest-id='7893318']//span[text()='%s']", name)))
                .click();
        logger.info(String.format("Filtered by %s", name));
    }

    private void addElementToEquals(String name) {
        driver
                .findElement(
                        By.xpath(
                                String.format("(//article[@data-autotest-id=\"product-snippet\"]" +
                                        "//span[contains(text(), \"%s\")])[1]/../../../../.." +
                                        "//div[@data-tid-prop=\"dc1632ca\"][1]", name)
                        )
                )
                .click();
        logger.info(String.format("Add %s element", name));
    }
}
