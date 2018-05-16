package by.yan.cafe.validation;

import java.util.regex.Pattern;

public class RegistrationDataValidator
{
    private static final String LOGIN_REGEX = "^[a-zA-Z0-9_]{3,15}$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{5,20}$";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)$";

    public static boolean loginCheck(String login)
    {
        return  Pattern.matches(LOGIN_REGEX, login);
    }

    public static boolean passwordCheck(String password)
    {
        return  Pattern.matches(PASSWORD_REGEX, password);
    }

    public static boolean passwordEqualityCheck(String password,String passwordConfirm)
    {
        return  password.equals(passwordConfirm);
    }

    public static boolean emailCheck(String email)
    {
        return  Pattern.matches(EMAIL_REGEX, email);
    }

}
