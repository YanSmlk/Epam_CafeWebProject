package by.yan.cafe.validation;

import java.io.File;
import java.util.regex.Pattern;

public class FoodInfoValidator
{
    private static final String DESCR_REGEX="^[a-zA-Zа-яА-Я0-9 !\"#$%&«»'()*+—,-./:;=?@\\^_`|}~ ]{4,180}$";
    private static final String NAME_REGEX="^[a-zA-Zа-яА-Я0-9_.!? ,]{2,35}$";
    private static final String PRICE_REGEX="^([1-9][0-9]{0,2})$";
    private static final String PIC_NAME_REGEX="(.*/)*.+\\.(png|jpg|jpeg|PNG|JPG)$";

    public static boolean nameCheck(String name)
    {
        return  Pattern.matches(NAME_REGEX, name);
    }

    public static boolean descrCheck(String descr)
    {
        return  Pattern.matches(DESCR_REGEX, descr);
    }

    public static boolean priceCheck(String price)
    {
        return Pattern.matches(PRICE_REGEX, price);
    }

    public static boolean picNameCheck(String picName,String picPath)
    {
        if (Pattern.matches(PIC_NAME_REGEX, picName))
        {
            File file = new File(picPath);
            return (file.exists());
        }
        return false;
    }

}
