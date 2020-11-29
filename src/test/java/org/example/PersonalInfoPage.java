package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalInfoPage {
    WebDriver driver;
    private static Logger logger = LogManager.getLogger(PersonalInfoPage.class);

    public PersonalInfoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "id_fname")
    private WebElement firstName;

    @FindBy(id = "id_lname")
    private WebElement lastName;

    @FindBy(id = "id_fname_latin")
    private WebElement firstNameLatin;

    @FindBy(id = "id_lname_latin")
    private WebElement lastNameLatin;

    @FindBy(id = "id_blog_name")
    private WebElement userName;

    @FindBy(name = "date_of_birth")
    private WebElement dateOfBirth;

    @FindBy(xpath = "//input[@name='country']/following::div")
    private WebElement countrySelector;

    @FindBy(xpath = "//input[@name='city']/following::div")
    private WebElement citySelector;

    @FindBy(xpath = "//input[@name='english_level']/following::div")
    private WebElement englishLevelSelector;

    @FindBy(className = "js-lk-cv-custom-select-add")
    private WebElement contactAddButton;

    @FindBy(name = "continue")
    private WebElement saveButton;

    public PersonalInfoPage openPage() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.header2-menu__icon")));
        driver.get("https://otus.ru/lk/biography/personal/");
        logger.info("Open personal info page");
        return this;
    }

    public PersonalInfoPage inputFirstName(String firstName) {
        this.firstName.clear();
        this.firstName.sendKeys(firstName);
        logger.info("Input first name");
        return this;
    }

    public PersonalInfoPage inputLastName(String lastName) {
        this.lastName.clear();
        this.lastName.sendKeys(lastName);
        logger.info("Input last name");
        return this;
    }

    public PersonalInfoPage inputFirstNameLatin(String firstNameLatin) {
        this.firstNameLatin.clear();
        this.firstNameLatin.sendKeys(firstNameLatin);
        logger.info("Input first latin name");
        return this;
    }

    public PersonalInfoPage inputLastNameLatin(String lastNameLatin) {
        this.lastNameLatin.clear();
        this.lastNameLatin.sendKeys(lastNameLatin);
        logger.info("Input last latin name");
        return this;
    }

    public PersonalInfoPage inputUserName(String userName) {
        this.userName.clear();
        this.userName.sendKeys(userName);
        logger.info("Input user name");
        return this;
    }

    public PersonalInfoPage inputDateOfBirth(String dateOfBirth) {
        this.dateOfBirth.clear();
        this.dateOfBirth.sendKeys(dateOfBirth);
        logger.info("Input date of birthday");
        return this;
    }

    public PersonalInfoPage selectCountry(String country) {
        countrySelector.click();
        driver.findElement(By.cssSelector(String.format("[title=\"%s\"]", country))).click();
        logger.info("Input country");
        return this;
    }

    public PersonalInfoPage selectCity(String city) {
        citySelector.click();
        driver.findElement(By.cssSelector(String.format("[title=\"%s\"]", city))).click();
        logger.info("Input city");
        return this;
    }

    public PersonalInfoPage selectEnglishLevel(String englishLevel) {
        englishLevelSelector.click();
        driver.findElement(By.cssSelector(String.format("[title=\"%s\"]", englishLevel))).click();
        logger.info("Input english level");
        return this;
    }

    public PersonalInfoPage save() {
        saveButton.click();
        logger.info("Clicked save button");
        return this;
    }

    public String getFirstName() {
        return firstName.getAttribute("value");
    }

    public String getLastName() {
        return lastName.getAttribute("value");
    }

    public String getFirstNameLatin() {
        return firstNameLatin.getAttribute("value");
    }

    public String getLastNameLatin() {
        return lastNameLatin.getAttribute("value");
    }

    public String getUserName() {
        return userName.getAttribute("value");
    }

    public String getDateOfBirth() {
        return dateOfBirth.getAttribute("value");
    }

    public String getCountrySelector() {
        return countrySelector.getText();
    }

    public String getCitySelector() {
        return citySelector.getText();
    }

    public String getEnglishLevelSelector() {
        return englishLevelSelector.getText().trim();
    }
}
