package by.yan.cafe.repository.user.specification;

import by.yan.cafe.connection.ProxyConnection;
import by.yan.cafe.repository.SqlSpecification;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectLoginByLoginSpec implements SqlSpecification
{
    private static final String SELECT_LOGIN_BY_LOGIN_QUERY="SELECT Login FROM user WHERE Login=?";
    private String login;

    public SelectLoginByLoginSpec(String login)
    {
        this.login=login;
    }

    @Override
    public PreparedStatement setPreparedStatement (ProxyConnection proxyConnection) throws SQLException
    {
        PreparedStatement preparedStatement=proxyConnection.prepareStatement(SELECT_LOGIN_BY_LOGIN_QUERY);
        preparedStatement.setString(1,login);
        return preparedStatement;
    }

}
