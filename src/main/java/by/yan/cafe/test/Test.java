package by.yan.cafe.test;

import by.yan.cafe.command.CommandType;
import by.yan.cafe.constant.OrderStatus;
import by.yan.cafe.constant.RoleType;
import by.yan.cafe.entity.food.impl.Drink;
import by.yan.cafe.exception.ActionException;
import by.yan.cafe.mail.Mail;
import by.yan.cafe.repository.SqlSpecification;
import by.yan.cafe.repository.food.specification.SelectDessertsSpec;
import by.yan.cafe.repository.food.specification.SelectDrinksSpec;
import by.yan.cafe.repository.food.specification.SelectMealByIdSpec;
import by.yan.cafe.repository.food.specification.SelectMealsSpec;
import by.yan.cafe.validation.LoginDataValidator;
import by.yan.cafe.validation.RegistrationDataValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class Test
{
    private static final Logger LOGGER = LogManager.getLogger();
    private static Boolean bb;

    public static void main(String[] args)
    {
        //LOGGER.log(Level.INFO, "POOL CREATION");
       // System.out.println(RegistrationDataValidator.emailCheck("velo_98@mail.ru"));
       // System.out.println(passHash("1234esfd3"));
        //System.out.println(RoleType.CLIENT.toString());


       boolean bb1;
        int hh='1';

//System.out.println(hh);

        String st1r="12";
        byte bb=Byte.valueOf(st1r);


    }

    public static String passHash(String pass)
    {


        String generatedPassword = null;
        String saltStr= "apple";
        byte[] salt=saltStr.getBytes();
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(salt);
            byte[] bytes = md.digest(pass.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }

}
