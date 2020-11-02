package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class YandexMarketTest extends BaseTest{
    private static Logger logger = LogManager.getLogger(YandexMarketTest.class);

    private static final String SAMSUNG = "Samsung";
    private static final String XIAOMI = "Xiaomi";

    private final By catalogElectronikaLocator = By.cssSelector("a[href=\"/catalog--elektronika/54440\"]");
    private final By smartphonesButtonLocator = By.xpath("//ul[@data-autotest-id='subItems']//a[text()='Смартфоны']");
    private final By sortPriceLocator = By.cssSelector("button[data-autotest-id=\"dprice\"]");
    private final By popupInformerLocator = By.xpath("//div[@data-apiary-widget-name=\"@market/PopupInformer\"]/div");
    private final By equalsButtonLocator = By.xpath("//a[@href=\"/my/compare-lists\"]");
    private final By snippetListLocator = By.xpath("//div[@data-zone-name=\"snippetList\"]/../div[2]");

    @Test
    public void equalsSmartphones() {
        driver.get("https://market.yandex.ru/");
        logger.info("Opened Yandex.Market");

        driver.findElement(catalogElectronikaLocator).click();
        logger.info("Opened page Electronics");
        driver.findElement(smartphonesButtonLocator).click();
        logger.info("Opened page Smartfons");

        filterBy(SAMSUNG);
        filterBy(XIAOMI);

        driver.findElement(sortPriceLocator).click();
        logger.info("Sorted by price");

        new WebDriverWait(driver, EXPLICITLY_WAIT)
                .until(ExpectedConditions.invisibilityOfElementLocated(snippetListLocator));

        String firstSamsung = getArticleName(SAMSUNG);
        addElementToEquals(SAMSUNG);

        assertNotNull(driver.findElement(popupInformerLocator));

        String firstXiaomi = getArticleName(XIAOMI);
        addElementToEquals(XIAOMI);

        assertNotNull(driver.findElement(popupInformerLocator));

        new WebDriverWait(driver, EXPLICITLY_WAIT).until(ExpectedConditions.elementToBeClickable(equalsButtonLocator));

        clickWithJS(driver.findElement(equalsButtonLocator));

        assertNotNull(getLinkElementByText(firstSamsung));
        logger.info("Found Samsung");
        assertNotNull(getLinkElementByText(firstXiaomi));
        logger.info("Found Xiaomi");
    }

    private String getArticleName(String name) {
        By articleNameLocator = By.xpath(
                String.format("(//article[@data-autotest-id=\"product-snippet\"]" +
                        "//span[contains(text(), \"%s\")])[1]", name)
        );
        return driver.findElement(articleNameLocator).getText();
    }

    private void filterBy(String name) {
        By filterLocator = By.xpath(String.format("//fieldset[@data-autotest-id='7893318']//span[text()='%s']", name));
        driver.findElement(filterLocator).click();
        logger.info(String.format("Filtered by %s", name));
    }

    private void addElementToEquals(String name) {
        By equalsButtonLocator = By.xpath(
                String.format("(//article[@data-autotest-id=\"product-snippet\"]" +
                        "//span[contains(text(), \"%s\")])[1]/../../../../.." +
                        "//div[@data-tid-prop=\"dc1632ca\"][1]", name)
        );
        driver.findElement(equalsButtonLocator).click();
        logger.info(String.format("Add %s element", name));
    }
}
