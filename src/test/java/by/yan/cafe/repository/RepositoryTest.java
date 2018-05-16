package by.yan.cafe.repository;

import by.yan.cafe.action.main.PasswordEncoder;
import by.yan.cafe.connection.ConnectionPool;
import by.yan.cafe.connection.ProxyConnection;
import by.yan.cafe.entity.User;
import by.yan.cafe.entity.food.BaseFood;
import by.yan.cafe.exception.RepositoryException;
import by.yan.cafe.repository.food.FoodRepository;
import by.yan.cafe.repository.food.specification.SelectDessertsSpec;
import by.yan.cafe.repository.food.specification.SelectDrinksSpec;
import by.yan.cafe.repository.food.specification.SelectMealsSpec;
import by.yan.cafe.repository.user.UserRepository;
import by.yan.cafe.repository.user.specification.InsertNewClientSpec;
import by.yan.cafe.repository.user.specification.SelectUserByLoginPasswordSpec;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import static org.testng.Assert.fail;

public class RepositoryTest
{
    private SqlSpecification specification;
    private FoodRepository foodRepository;
    private UserRepository userRepository;
    private List<BaseFood> baseFoodArr;

    @BeforeClass
    public void initParams()
    {
        userRepository=new UserRepository();
        foodRepository=new FoodRepository();
    }

    @AfterMethod
    public void clearParams()
    {
        baseFoodArr=null;
    }

    @Test
    public void loadMealTest()
    {
        specification=new SelectMealsSpec();
        try
        {
            baseFoodArr=foodRepository.query(specification);
            Assert.assertTrue(!baseFoodArr.isEmpty());
        }
        catch (RepositoryException ex)
        {
            fail();
        }
    }

    @Test
    public void loadDessertTest()
    {
        specification=new SelectDessertsSpec();
        try
        {
            baseFoodArr = foodRepository.query(specification);
            Assert.assertTrue(!baseFoodArr.isEmpty());
        }
        catch (RepositoryException ex)
        {
            fail();
        }
    }

    @Test
    public void loadDrinkTest()
    {
        specification=new SelectDrinksSpec();
        try
        {
            baseFoodArr = foodRepository.query(specification);
            Assert.assertTrue(!baseFoodArr.isEmpty());
        }
        catch (RepositoryException ex)
        {
            fail();
        }
    }

    @Test
    void userCreationTest()
    {
        final String LOGIN="TEST_USER";
        String PASSWORD="TEST_PASSWORD";
        final String EMAIl="TEST-EMAIl";

        try
        {
           PASSWORD = PasswordEncoder.passHash(PASSWORD);
        }
        catch (NoSuchAlgorithmException ex)
        {
            fail();
        }
        specification=new InsertNewClientSpec(LOGIN,PASSWORD,EMAIl);
        try
        {
            userRepository.insert(specification);
            SqlSpecification sqlSpecification= new SelectUserByLoginPasswordSpec(LOGIN, PASSWORD);
            List<User> usersArr = userRepository.query(sqlSpecification);
            Assert.assertTrue(!usersArr.isEmpty());
            clearDB(LOGIN);
        }
        catch (RepositoryException ex)
        {
            fail();
        }
    }

    private void clearDB(String LOGIN)
    {
        final String DELETE_USER_QUERY="DELETE FROM `cafedb`.`user` WHERE `Login`=?";
        ProxyConnection proxyConnection= ConnectionPool.getInstance().getConnection();
        try
        {
            PreparedStatement preparedStatement = proxyConnection.prepareStatement(DELETE_USER_QUERY);
            preparedStatement.setString(1,LOGIN);
            preparedStatement.executeUpdate();
        }
        catch (SQLException ex)
        {
            fail();
        }
        finally
        {
            proxyConnection.close();
        }
    }

}
