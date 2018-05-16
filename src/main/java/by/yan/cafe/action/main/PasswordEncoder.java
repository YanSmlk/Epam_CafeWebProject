package by.yan.cafe.action.main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder
{
    public static String passHash(String pass) throws NoSuchAlgorithmException
    {
        final String SALT_WORD= "apple";
        byte[] salt=SALT_WORD.getBytes();
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(salt);
        byte[] bytes = md.digest(pass.getBytes());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
