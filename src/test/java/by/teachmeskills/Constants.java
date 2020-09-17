package by.teachmeskills;

import org.openqa.selenium.By;

public abstract class Constants {

    private Constants() {
        System.out.println("This class never should be instantiated");
    }

    public static final By WEIGHT_DROPDOWN_LOCATOR = By.name("opt1");
    public static final By HEIGHT_FEET_DROPDOWN_LOCATOR = By.name("opt2");
    public static final By HEIGHT_INCHES_DROPDOWN_LOCATOR = By.name("opt3");
    public static final By WEIGHT_INPUT_LOCATOR = By.name("wg");
    public static final By HEIGHT_INPUT_LOCATOR = By.name("ht");
    public static final By SI_UNITS_INPUT_LOCATOR = By.name("si");
    public static final By US_UNITS_INPUT_LOCATOR = By.name("us");
    public static final By UK_UNITS_INPUT_LOCATOR = By.name("uk");
    public static final By CALCULATE_BUTTON_LOCATOR = By.xpath("//input[@value='Calculate']");
    public static final By CATEGORY_DESCRIPTION_LOCATOR = By.xpath("//form[@name='bmi']//input[@name='desc']");

    public static final String WEIGHT_DROPDOWN_POUNDS_OPTION_VALUE = "pounds";
    public static final String WEIGHT_DROPDOWN_KILOGRAMS_OPTION_VALUE = "kilograms";
    public static final String HEIGHT_FEET_DROPDOWN_4_OPTION_VALUE = "4";
    public static final String HEIGHT_INCHES_DROPDOWN_11_OPTION_VALUE = "11";
    public static final String VALUE_ATTRIBUTE = "value";
    public static final String READONLY_ATTRIBUTE = "readonly";
    public static final String TRUE_VALUE = "true";
    public static final String WEIGHT_GREATER_THAN_10KG_MESSAGE = "Weight should be greater than 10kgs";
    public static final String ENTER_VALUE_FOR_WEIGHT = "Enter the value for weight";
    public static final String HEIGHT_TALLER_THAN_33_CMS_MESSAGE = "Height should be taller than 33cms";
    public static final String NAN_VALUE = "NaN";
    public static final String NORMAL_CATEGORY_MESSAGE = "Your category is Normal";
    public static final String OBESE_CATEGORY_MESSAGE = "Your category is Obese";
    public static final String OVERWEIGHT_CATEGORY_MESSAGE = "Your category is Overweight";
    public static final String STARVATION_CATEGORY_MESSAGE = "Your category is Starvation";
    public static final String UNDERWEIGHT_CATEGORY_MESSAGE = "Your category is Underweight";
}
