package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditPersonalInfoTest extends BaseTest{

    private final String FIRST_NAME = "Мила";
    private final String LAST_NAME = "Краснощёкова";
    private final String FIRST_LATIN_NAME = "Mila";
    private final String LAST_LATIN_NAME = "Krasnoshchekova";
    private final String USER_NAME = "Mila";
    private final String DATE_OF_BIRTH = "09.11.1995";
    private final String COUNTRY = "Россия";
    private final String CITY = "Санкт-Петербург";
    private final String ENGLISH_LEVEL = "Ниже среднего (Pre-Intermediate)";

    private static Logger logger = LogManager.getLogger(EditPersonalInfoTest.class);
    SignInPage signInPage;
    PersonalInfoPage personalInfoPage;

    @BeforeEach
    void setUpEach() {
        initPageObjects();
    }

    @Test
    public void editPersonalInfo() {
        signInPage
                .openPage()
                .signIn(email, password)
        ;

        personalInfoPage
                .openPage()
                .inputFirstName(FIRST_NAME)
                .inputLastName(LAST_NAME)
                .inputFirstNameLatin(FIRST_LATIN_NAME)
                .inputLastNameLatin(LAST_LATIN_NAME)
                .inputUserName(USER_NAME)
                .inputDateOfBirth(DATE_OF_BIRTH)
                .selectCountry(COUNTRY)
                .selectCity(CITY)
                .selectEnglishLevel(ENGLISH_LEVEL)
                .save()
        ;

        reInitDriver();
        initPageObjects();

        signInPage
                .openPage()
                .signIn(email, password)
        ;

        personalInfoPage.openPage();

        assertEquals(FIRST_NAME, personalInfoPage.getFirstName());
        assertEquals(LAST_NAME, personalInfoPage.getLastName());
        assertEquals(FIRST_LATIN_NAME, personalInfoPage.getFirstNameLatin());
        assertEquals(LAST_LATIN_NAME, personalInfoPage.getLastNameLatin());
        assertEquals(USER_NAME, personalInfoPage.getUserName());
        assertEquals(DATE_OF_BIRTH, personalInfoPage.getDateOfBirth());
        assertEquals(COUNTRY, personalInfoPage.getCountrySelector());
        assertEquals(CITY, personalInfoPage.getCitySelector());
        assertEquals(ENGLISH_LEVEL, personalInfoPage.getEnglishLevelSelector());
        logger.info("EditPersonalInfoTest completed");
    }

    private void initPageObjects() {
        signInPage = new SignInPage(driver);
        personalInfoPage = new PersonalInfoPage(driver);
    }

}
