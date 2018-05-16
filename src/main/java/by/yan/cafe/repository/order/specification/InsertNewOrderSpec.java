package by.yan.cafe.repository.order.specification;

import by.yan.cafe.connection.ProxyConnection;
import by.yan.cafe.constant.OrderStatus;
import by.yan.cafe.repository.SqlSpecification;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertNewOrderSpec implements SqlSpecification
{
    private static final String INSERT_NEW_ORDER_QUERY="INSERT INTO `cafedb`.`order` " +
            "(`idClient`, `ReceivingTime`, `Status`, `TotalPrice`) VALUES (?, ?, ?, ?)";

    private int idClient;
    private String resTime;
    private OrderStatus status;
    private byte totalPrice;

    public InsertNewOrderSpec(int idClient, String resTime, OrderStatus status, byte totalPrice)
    {
        this.idClient=idClient;
        this.resTime=resTime;
        this.status=status;
        this.totalPrice=totalPrice;
    }

    @Override
    public PreparedStatement setPreparedStatement (ProxyConnection proxyConnection) throws SQLException
    {
        PreparedStatement preparedStatement=proxyConnection.prepareStatement(INSERT_NEW_ORDER_QUERY);
        preparedStatement.setInt(1,idClient);
        preparedStatement.setString(2,resTime);
        preparedStatement.setString(3,status.toString());
        preparedStatement.setByte(4,totalPrice);
        return preparedStatement;
    }

}
