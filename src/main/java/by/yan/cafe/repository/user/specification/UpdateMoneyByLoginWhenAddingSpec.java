package by.yan.cafe.repository.user.specification;

import by.yan.cafe.connection.ProxyConnection;
import by.yan.cafe.repository.SqlSpecification;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateMoneyByLoginWhenAddingSpec implements SqlSpecification
{
    private static final String UPDATE_MONEY_BY_LOGIN_QUERY="UPDATE user SET MoneyAmount= MoneyAmount+5 WHERE user.Login=?";
    private String login;

    public UpdateMoneyByLoginWhenAddingSpec(String login)
    {
        this.login=login;
    }

    @Override
    public PreparedStatement setPreparedStatement (ProxyConnection proxyConnection) throws SQLException
    {
        PreparedStatement preparedStatement=proxyConnection.prepareStatement(UPDATE_MONEY_BY_LOGIN_QUERY);
        preparedStatement.setString(1,login);
        return preparedStatement;
    }

}
