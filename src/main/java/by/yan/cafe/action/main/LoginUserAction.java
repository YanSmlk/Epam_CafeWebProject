package by.yan.cafe.action.main;

import by.yan.cafe.constant.RoleType;
import by.yan.cafe.entity.User;
import by.yan.cafe.exception.ActionException;
import by.yan.cafe.exception.RepositoryException;
import by.yan.cafe.repository.SqlSpecification;
import by.yan.cafe.repository.user.UserRepository;
import by.yan.cafe.repository.user.specification.SelectUserByLoginPasswordSpec;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class LoginUserAction
{
    public RoleType tryLogin(String login, String password) throws RepositoryException, ActionException
    {
        try
        {
            password = PasswordEncoder.passHash(password);
            SqlSpecification userSpecification = new SelectUserByLoginPasswordSpec(login, password);
            UserRepository userRepository = new UserRepository();
            List<User> usersArr = userRepository.query(userSpecification);
            if (!usersArr.isEmpty())
            {
                return usersArr.get(0).getRoleType();
            }
            else
            {
                return null;
            }
        }
        catch (NoSuchAlgorithmException ex)
        {
            throw new ActionException("Failed to encrypt the password "+ex);
        }
    }

}
