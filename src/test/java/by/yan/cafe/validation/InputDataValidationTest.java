package by.yan.cafe.validation;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class InputDataValidationTest
{
    private FoodInfoValidator foodInfoValidator;
    private RegistrationDataValidator registrationDataValidator;

    @BeforeClass
    public void initParams()
    {
        foodInfoValidator=new FoodInfoValidator();
        registrationDataValidator=new RegistrationDataValidator();
    }

    @Test
    public void loginTestPositive()
    {
        final String LOGIN="Vitaliy";
        Assert.assertTrue(registrationDataValidator.loginCheck(LOGIN));
    }

    @Test
    public void loginTestNegativeFirst()
    {
        final String LOGIN="ToooooooLooooooongLoooogin";
        Assert.assertFalse(registrationDataValidator.loginCheck(LOGIN));
    }

    @Test
    public void loginTestNegativeSecond()
    {
        final String LOGIN="ПлохойLogin";
        Assert.assertFalse(registrationDataValidator.loginCheck(LOGIN));
    }

    @Test
    public void passwordTestPositive()
    {
        final String PASSWORDS="goodPassword1214";
        Assert.assertTrue(registrationDataValidator.passwordCheck(PASSWORDS));
    }

    @Test
    public void passwordTestNegative()
    {
        final String PASSWORD="BadPassword";
        Assert.assertFalse(registrationDataValidator.passwordCheck(PASSWORD));
    }

    @Test
    public void emailTestPositive()
    {
        final String EMAIL="my_mail12@mail.com";
        Assert.assertTrue(registrationDataValidator.emailCheck(EMAIL));
    }

    @Test
    public void emailTestNegative()
    {
        final String EMAIL="my_mail12@mailcom";
        Assert.assertFalse(registrationDataValidator.emailCheck(EMAIL));
    }

    @Test
    public void foodNameTestPositive()
    {
        final String FOOD_NAME="Fresh cake!";
        Assert.assertTrue(foodInfoValidator.nameCheck(FOOD_NAME));
    }

    @Test
    public void foodNameTestNegative()
    {
        final String FOOD_NAME="Bad Name<а";
        Assert.assertFalse(foodInfoValidator.nameCheck(FOOD_NAME));
    }

    @Test
    public void foodPriceTestPositive()
    {
        final String FOOD_PRICE="4";
        Assert.assertTrue(foodInfoValidator.priceCheck(FOOD_PRICE));
    }

    @Test
    public void foodPriceTestNegative()
    {
        final String FOOD_PRICE="01";
        Assert.assertFalse(foodInfoValidator.priceCheck(FOOD_PRICE));
    }

}
