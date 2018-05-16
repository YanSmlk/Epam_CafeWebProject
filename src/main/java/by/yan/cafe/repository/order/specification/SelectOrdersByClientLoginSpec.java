package by.yan.cafe.repository.order.specification;

import by.yan.cafe.connection.ProxyConnection;
import by.yan.cafe.repository.SqlSpecification;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectOrdersByClientLoginSpec implements SqlSpecification
{

    private static final String SELECT_ORDER_BY_LOGIN_QUERY="SELECT cafedb.order.idOrder, " +
            "DATE_FORMAT ( cafedb.order.ReceivingTime, '%H:%i'), cafedb.order.Status,cafedb.order.TotalPrice" +
            " FROM cafedb.order JOIN user ON user.idUser= cafedb.order.idClient WHERE user.Login=?";


    private String login;

    public SelectOrdersByClientLoginSpec(String login)
    {
        this.login=login;
    }

    @Override
    public PreparedStatement setPreparedStatement (ProxyConnection proxyConnection) throws SQLException
    {
        PreparedStatement preparedStatement=proxyConnection.prepareStatement(SELECT_ORDER_BY_LOGIN_QUERY);
        preparedStatement.setString(1,login);
        return preparedStatement;
    }

}
