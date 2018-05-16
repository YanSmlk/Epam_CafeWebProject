package by.yan.cafe.repository.order.specification;

import by.yan.cafe.connection.ProxyConnection;
import by.yan.cafe.repository.SqlSpecification;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectOrdersByStatusSpec implements SqlSpecification
{
    private static final String SELECT_CURRENT_ORDERS_BY_STATUS_QUERY="SELECT cafedb.order.idOrder," +
            " DATE_FORMAT ( cafedb.order.ReceivingTime, '%H:%i'),cafedb.order.TotalPrice, cafedb.user.Login" +
                "  FROM cafedb.`order` JOIN cafedb.user on cafedb.user.idUser=cafedb.order.idClient WHERE Status=?";

    private String status;

    public SelectOrdersByStatusSpec(String status)
    {
        this.status=status;
    }

    @Override
    public PreparedStatement setPreparedStatement (ProxyConnection proxyConnection) throws SQLException
    {
        PreparedStatement preparedStatement=proxyConnection.prepareStatement(SELECT_CURRENT_ORDERS_BY_STATUS_QUERY);
        preparedStatement.setString(1,status);
        return preparedStatement;
    }

}
