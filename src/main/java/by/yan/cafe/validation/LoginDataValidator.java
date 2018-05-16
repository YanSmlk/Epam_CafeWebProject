package by.yan.cafe.validation;

import java.util.regex.Pattern;

public class LoginDataValidator
{
    private static final String LOGIN_REGEX = "^[a-zA-Z0-9_]{3,15}$";

    public static boolean loginCheck(String login)
    {
        return  Pattern.matches(LOGIN_REGEX, login);
    }

    public static boolean passwordCheck(String password)
    {
        return  (!password.isEmpty() && password.length()<=20);
    }

}
