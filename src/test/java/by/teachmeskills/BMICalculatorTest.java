package by.teachmeskills;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static by.teachmeskills.Constants.*;

public class BMICalculatorTest extends CommonTest {

    @Test(dataProvider = "Valid weight, height and calculations data provider")
    public void testCategoryIsValid(String weight, String height,
                                    String expectedSIUnits,
                                    String expectedUSUnits,
                                    String expectedUKUnits,
                                    String expectedCategory) {
        sendKeys(WEIGHT_INPUT_LOCATOR, weight);
        sendKeys(HEIGHT_INPUT_LOCATOR, height);
        clickButton(CALCULATE_BUTTON_LOCATOR);
        Assert.assertEquals(getAttributeValue(SI_UNITS_INPUT_LOCATOR, VALUE_ATTRIBUTE), expectedSIUnits);
        Assert.assertEquals(getAttributeValue(US_UNITS_INPUT_LOCATOR, VALUE_ATTRIBUTE), expectedUSUnits);
        Assert.assertEquals(getAttributeValue(UK_UNITS_INPUT_LOCATOR, VALUE_ATTRIBUTE), expectedUKUnits);
        Assert.assertEquals(getAttributeValue(CATEGORY_DESCRIPTION_LOCATOR, VALUE_ATTRIBUTE), expectedCategory);
    }

    @DataProvider(name = "Valid weight, height and calculations data provider")
    public static Object[][] validWeightAndHeightAndCalculationsDataProvider() {
        return new Object[][]{
                {"75", "176", "24.21", "24.62", "153.73", NORMAL_CATEGORY_MESSAGE},
                {"    75", "174", "24.77", "25.19", "157.29", NORMAL_CATEGORY_MESSAGE},
                {"75", "201     ", "18.56", "18.88", "117.86", NORMAL_CATEGORY_MESSAGE},
                {"200", "200", "50", "50.84", "317.5", OBESE_CATEGORY_MESSAGE},
                {"100", "190", "27.7", "28.17", "175.9", OVERWEIGHT_CATEGORY_MESSAGE},
                {"30", "180", "9.26", "9.42", "58.8", STARVATION_CATEGORY_MESSAGE},
                {"50", "175", "16.33", "16.6", "103.7", UNDERWEIGHT_CATEGORY_MESSAGE}
        };
    }

    @Test(dataProvider = "Valid weight and height data provider")
    public void testUnitsAndCategoryFieldsAreReadonly(String weight, String height) {
        sendKeys(WEIGHT_INPUT_LOCATOR, weight);
        sendKeys(HEIGHT_INPUT_LOCATOR, height);
        clickButton(CALCULATE_BUTTON_LOCATOR);
        Assert.assertEquals(getAttributeValue(SI_UNITS_INPUT_LOCATOR, READONLY_ATTRIBUTE), TRUE_VALUE);
        Assert.assertEquals(getAttributeValue(US_UNITS_INPUT_LOCATOR, READONLY_ATTRIBUTE), TRUE_VALUE);
        Assert.assertEquals(getAttributeValue(UK_UNITS_INPUT_LOCATOR, READONLY_ATTRIBUTE), TRUE_VALUE);
        Assert.assertEquals(getAttributeValue(CATEGORY_DESCRIPTION_LOCATOR, READONLY_ATTRIBUTE), TRUE_VALUE);
    }

    @DataProvider(name = "Valid weight and height data provider")
    public static Object[][] validWeightAndHeightDataProvider() {
        return new Object[][]{
                {"75", "176"},
                {"200", "200"},
                {"100", "190"},
                {"30", "180"},
                {"50", "175"}
        };
    }

    @Test
    public void testWeightConversionFromKilogramsToPoundsIsValid() {
        sendKeys(WEIGHT_INPUT_LOCATOR, "70");
        selectDropdownOptionByValue(WEIGHT_DROPDOWN_LOCATOR, WEIGHT_DROPDOWN_POUNDS_OPTION_VALUE);
        Assert.assertEquals(getAttributeValue(WEIGHT_INPUT_LOCATOR, VALUE_ATTRIBUTE), "154");
    }

    @Test
    public void testWeightConversionFromPoundsToKilogramsIsValid() {
        selectDropdownOptionByValue(WEIGHT_DROPDOWN_LOCATOR, WEIGHT_DROPDOWN_POUNDS_OPTION_VALUE);
        sendKeys(WEIGHT_INPUT_LOCATOR, "500");
        selectDropdownOptionByValue(WEIGHT_DROPDOWN_LOCATOR, WEIGHT_DROPDOWN_KILOGRAMS_OPTION_VALUE);
        Assert.assertEquals(getAttributeValue(WEIGHT_INPUT_LOCATOR, VALUE_ATTRIBUTE), "227");
    }

    @Test(dataProvider = "Weight less than expected or empty data provider")
    public void testWhenWeightLessExpectedOrEmptyThenAlertAppears(String weight, String expectedResult) {
        sendKeys(WEIGHT_INPUT_LOCATOR, weight);
        clickButton(CALCULATE_BUTTON_LOCATOR);
        Assert.assertTrue(isAlertPresent());
        Assert.assertEquals(getAlertText(), expectedResult);
    }

    @DataProvider(name = "Weight less than expected or empty data provider")
    public static Object[][] weightLessExpectedOrEmptyDataProvider() {
        return new Object[][]{
                {"10", WEIGHT_GREATER_THAN_10KG_MESSAGE},
                {"9", WEIGHT_GREATER_THAN_10KG_MESSAGE},
                {"0", WEIGHT_GREATER_THAN_10KG_MESSAGE},
                {"-1", WEIGHT_GREATER_THAN_10KG_MESSAGE},
                {"-100500", WEIGHT_GREATER_THAN_10KG_MESSAGE},
                {" ", WEIGHT_GREATER_THAN_10KG_MESSAGE},
                {"", ENTER_VALUE_FOR_WEIGHT}
        };
    }

    @Test(dataProvider = "Height less than expected or empty data provider")
    public void testWhenHeightLessExpectedOrEmptyThenAlertAppears(String height) {
        sendKeys(WEIGHT_INPUT_LOCATOR, "75");
        sendKeys(HEIGHT_INPUT_LOCATOR, height);
        clickButton(CALCULATE_BUTTON_LOCATOR);
        Assert.assertTrue(isAlertPresent());
        Assert.assertEquals(getAlertText(), HEIGHT_TALLER_THAN_33_CMS_MESSAGE);
    }

    @DataProvider(name = "Height less than expected or empty data provider")
    public static Object[][] heightLessThanExpectedOrEmptyDataProvider() {
        return new Object[][]{
                {"32"},
                {"10"},
                {"0"},
                {"-1"},
                {"-100500"},
                {" "},
                {""}
        };
    }

    @Test(dataProvider = "Invalid data data provider")
    public void testWhenWeightInvalidThenNoCalculation(String weight) {
        sendKeys(WEIGHT_INPUT_LOCATOR, weight);
        sendKeys(HEIGHT_INPUT_LOCATOR, "180");
        clickButton(CALCULATE_BUTTON_LOCATOR);
        Assert.assertEquals(getAttributeValue(SI_UNITS_INPUT_LOCATOR, VALUE_ATTRIBUTE), NAN_VALUE);
        Assert.assertEquals(getAttributeValue(US_UNITS_INPUT_LOCATOR, VALUE_ATTRIBUTE), NAN_VALUE);
        Assert.assertEquals(getAttributeValue(UK_UNITS_INPUT_LOCATOR, VALUE_ATTRIBUTE), NAN_VALUE);
    }

    @DataProvider(name = "Invalid data data provider")
    public static Object[][] invalidDataDataProvider() {
        return new Object[][]{
                {"50 10"},
                {"adgfhdfjdkfkdgdlgl"},
                {"ABC"},
                {"fgHvFFb"},
                {"vfvf121212"},
                {"йцуцуецуцун"},
                {"描摹; 临摹; 复写; 复制"},
                {"vfvf121212"},
                {"!"},
                {"@"},
                {"#"},
                {"$"},
                {"%"},
                {"^"},
                {"&"},
                {"*"},
                {"()"},
                {"_+-="},
                {"{}"},
                {"[]"},
                {"\\"},
                {"|"},
                {",."},
                {"<>"},
                {"/"},
                {"?"},
                {"~"}
        };
    }

    @Test(dataProvider = "Invalid data data provider")
    public void testWhenHeightInvalidThenNoCalculation(String height) {
        sendKeys(WEIGHT_INPUT_LOCATOR, "75");
        sendKeys(HEIGHT_INPUT_LOCATOR, height);
        clickButton(CALCULATE_BUTTON_LOCATOR);
        Assert.assertEquals(getAttributeValue(SI_UNITS_INPUT_LOCATOR, VALUE_ATTRIBUTE), NAN_VALUE);
        Assert.assertEquals(getAttributeValue(US_UNITS_INPUT_LOCATOR, VALUE_ATTRIBUTE), NAN_VALUE);
        Assert.assertEquals(getAttributeValue(UK_UNITS_INPUT_LOCATOR, VALUE_ATTRIBUTE), NAN_VALUE);
    }

    @Test
    public void testHeightConversionFromFeetAndInchesToCmsIsValid() {
        selectDropdownOptionByValue(HEIGHT_FEET_DROPDOWN_LOCATOR, HEIGHT_FEET_DROPDOWN_4_OPTION_VALUE);
        Assert.assertEquals(getAttributeValue(HEIGHT_INPUT_LOCATOR, VALUE_ATTRIBUTE), "122");
        selectDropdownOptionByValue(HEIGHT_INCHES_DROPDOWN_LOCATOR, HEIGHT_INCHES_DROPDOWN_11_OPTION_VALUE);
        Assert.assertEquals(getAttributeValue(HEIGHT_INPUT_LOCATOR, VALUE_ATTRIBUTE), "150");
    }

    private String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    private String getAttributeValue(By locator, String attributeValue) {
        return driver.findElement(locator).getAttribute(attributeValue);
    }

    private void sendKeys(By locator, String inputValue) {
        WebElement input = driver.findElement(locator);
        input.sendKeys(inputValue);
    }

    private void clickButton(By locator) {
        driver.findElement(locator).click();
    }

    private void selectDropdownOptionByValue(By locator, String optionValue) {
        Select weightDropdown = new Select(driver.findElement(locator));
        weightDropdown.selectByValue(optionValue);
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }
}
