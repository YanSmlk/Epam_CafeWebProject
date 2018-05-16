package by.yan.cafe.action.client;

import by.yan.cafe.exception.RepositoryException;
import by.yan.cafe.repository.SqlSpecification;
import by.yan.cafe.repository.user.UserRepository;
import by.yan.cafe.repository.user.specification.UpdateMoneyByLoginWhenAddingSpec;

public class AddMoneyAction
{
    public void updateMoney(String login) throws RepositoryException
    {
        SqlSpecification sqlSpecification =new UpdateMoneyByLoginWhenAddingSpec(login);
        UserRepository userRepository=new UserRepository();
        userRepository.insert(sqlSpecification);
    }
}
