package by.yan.cafe.repository.user.specification;


import by.yan.cafe.connection.ProxyConnection;
import by.yan.cafe.repository.SqlSpecification;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectPointsMoneyFromUserByLoginSpec implements SqlSpecification
{
    private static final String SELECT_POINTS_MONEY_FROM_USER_BY_LOGIN_QUERY="SELECT user.Points, user.MoneyAmount FROM user" +
            " WHERE user.Login=?";
    private String login;

    public SelectPointsMoneyFromUserByLoginSpec(String login)
    {
        this.login=login;
    }

    @Override
    public PreparedStatement setPreparedStatement (ProxyConnection proxyConnection) throws SQLException
    {
        PreparedStatement preparedStatement=proxyConnection.prepareStatement(SELECT_POINTS_MONEY_FROM_USER_BY_LOGIN_QUERY);
        preparedStatement.setString(1,login);
        return preparedStatement;
    }

}
