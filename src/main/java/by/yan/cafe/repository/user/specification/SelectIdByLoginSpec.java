package by.yan.cafe.repository.user.specification;

import by.yan.cafe.connection.ProxyConnection;
import by.yan.cafe.repository.SqlSpecification;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectIdByLoginSpec implements SqlSpecification
{
    private static final String SELECT_ID_BY_LOGIN_QUERY="SELECT idUser FROM cafedb.user WHERE Login=?";
    private String login;

    public SelectIdByLoginSpec(String login)
    {
        this.login=login;
    }

    @Override
    public PreparedStatement setPreparedStatement (ProxyConnection proxyConnection) throws SQLException
    {
        PreparedStatement preparedStatement=proxyConnection.prepareStatement(SELECT_ID_BY_LOGIN_QUERY);
        preparedStatement.setString(1,login);
        return preparedStatement;
    }

}
