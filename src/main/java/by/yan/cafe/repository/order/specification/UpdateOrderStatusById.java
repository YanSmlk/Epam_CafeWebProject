package by.yan.cafe.repository.order.specification;

import by.yan.cafe.connection.ProxyConnection;
import by.yan.cafe.repository.SqlSpecification;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateOrderStatusById implements SqlSpecification
{
    private static final String UPDATE_ORDER_STATUS_BY_ID_QUERY="UPDATE cafedb.order SET Status =? WHERE idOrder=?";

    private String status;
    private int id;

    public UpdateOrderStatusById(String status,int id)
    {
        this.status=status;
        this.id=id;
    }

    @Override
    public PreparedStatement setPreparedStatement (ProxyConnection proxyConnection) throws SQLException
    {
        PreparedStatement preparedStatement=proxyConnection.prepareStatement(UPDATE_ORDER_STATUS_BY_ID_QUERY);
        preparedStatement.setString(1,status);
        preparedStatement.setInt(2,id);
        return preparedStatement;
    }

}
