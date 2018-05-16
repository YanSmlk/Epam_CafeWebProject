package by.yan.cafe.mail;

import by.yan.cafe.exception.ActionException;
import java.io.IOException;
import java.io.InputStream;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail
{
    private static final String MAIL_PROP_FILE = "property/mail/mail.properties";
    private static final String MAIL_DATA_BUNDLE_FILE = "property.mail.mailData";
    private final String USERNAME = "USERNAME";
    private final String PASSWORD = "PASSWORD";
    private final String MAIL_SUBJECT ="MAIL_SUBJECT";

    public void sendMail(String mailTo, String mailText) throws ActionException
    {
        ResourceBundle resourceBundle;
        Properties prop = new Properties();
        try
        {
            resourceBundle = ResourceBundle.getBundle(MAIL_DATA_BUNDLE_FILE);
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(MAIL_PROP_FILE);
            prop.load(inputStream);
        }
        catch (MissingResourceException ex)
        {
            throw new ActionException("Cannot find mail resource file: "+ ex);
        }
        catch (IOException ex)
        {
            throw new ActionException("Message sent failed: "+ex);
        }
        try
        {
            Session session = Session.getInstance(prop, new javax.mail.Authenticator()
            {protected PasswordAuthentication getPasswordAuthentication()
                {return new PasswordAuthentication(resourceBundle.getString(USERNAME),resourceBundle.getString(PASSWORD));}});
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("CAFE_BOT"));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(mailTo));
            message.setSubject(resourceBundle.getString(MAIL_SUBJECT));
            message.setContent(mailText, "text/html; charset=utf-8");
            Transport.send(message);
        }
        catch (MessagingException ex)
        {
            throw new ActionException("Message sent failed: "+ex);
        }
    }

}
