package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.Drivers;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {

    public static WebDriver create(String webDriverName, String... options) {
        webDriverName = webDriverName
                .toLowerCase()
                .replace("'", "")
                .replace("`", "")
                .replace("\"", "");
        String[] optionsFormatted = new String[options.length];
        for (int i = 0; i < options.length; i++) {
            optionsFormatted[i] = options[i]
                    .replace("'", "")
                    .replace("`", "")
                    .replace("\"", "");
        }

        if (webDriverName.equals(Drivers.FIREFOX.toString())) {
            FirefoxOptions opt = new FirefoxOptions();
            opt.addArguments(optionsFormatted);
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver(opt);
        }

        ChromeOptions opt = new ChromeOptions();
        opt.addArguments(optionsFormatted);
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(opt);
    }
}
