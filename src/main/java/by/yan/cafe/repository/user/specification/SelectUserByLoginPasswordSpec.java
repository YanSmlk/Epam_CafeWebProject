package by.yan.cafe.repository.user.specification;

import by.yan.cafe.connection.ProxyConnection;
import by.yan.cafe.repository.SqlSpecification;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectUserByLoginPasswordSpec implements SqlSpecification
{
    private static final String SELECT_USER_BY_LOGIN_AND_PASSWORD_QUERY="SELECT idUser,Role,Email,Password,Login,Points,MoneyAmount FROM user WHERE Login=? AND Password=?";
    private String login;
    private String password;

    public SelectUserByLoginPasswordSpec(String login, String password)
    {
        this.login=login;
        this.password=password;
    }

    @Override
    public PreparedStatement setPreparedStatement (ProxyConnection proxyConnection) throws SQLException
    {
        PreparedStatement preparedStatement=proxyConnection.prepareStatement(SELECT_USER_BY_LOGIN_AND_PASSWORD_QUERY);
        preparedStatement.setString(1,login);
        preparedStatement.setString(2,password);
        return preparedStatement;
    }


}
