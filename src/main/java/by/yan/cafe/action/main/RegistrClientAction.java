package by.yan.cafe.action.main;

import by.yan.cafe.exception.ActionException;
import by.yan.cafe.exception.RepositoryException;
import by.yan.cafe.mail.Mail;
import by.yan.cafe.repository.SqlSpecification;
import by.yan.cafe.repository.user.UserRepository;
import by.yan.cafe.repository.user.specification.InsertNewClientSpec;
import by.yan.cafe.repository.user.specification.SelectLoginByLoginSpec;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class RegistrClientAction
{
    private static final int MIN_CODE_VAL=0;
    private static final int MAX_CODE_VAL=10000;

    public boolean checkLoginAvailable(String login) throws RepositoryException
    {
        SqlSpecification selectLoginByLoginSpec= new SelectLoginByLoginSpec(login);
        UserRepository userRepository =new UserRepository();
        List<String> LoginAvailable=userRepository.stringQuery(selectLoginByLoginSpec);
        return (LoginAvailable.isEmpty());
    }

    public void tryRegistr(String login,String password, String email) throws ActionException, RepositoryException
    {
        try
        {
            password = PasswordEncoder.passHash(password);
        }
        catch (NoSuchAlgorithmException ex)
        {
            throw new ActionException("Failed to encrypt the password "+ex);
        }
        SqlSpecification sqlSpecification=new InsertNewClientSpec(login,password,email);
        UserRepository userRepository =new UserRepository();
        userRepository.insert(sqlSpecification);
    }

    public String codeSend(String mailTo) throws ActionException
    {
        String mailCode = String.valueOf(MIN_CODE_VAL + (int) (Math.random() * MAX_CODE_VAL));
        Mail mail = new Mail();
        mail.sendMail(mailTo,mailCode);
        return mailCode;
    }

}
