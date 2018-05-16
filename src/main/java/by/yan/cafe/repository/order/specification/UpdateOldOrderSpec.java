package by.yan.cafe.repository.order.specification;

import by.yan.cafe.connection.ProxyConnection;
import by.yan.cafe.repository.SqlSpecification;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateOldOrderSpec implements SqlSpecification
{

    private static final String UPDATE_OLD_ORDERS_QUERY="UPDATE cafedb.order SET Status ='Canceled' WHERE Status='Current'";

    @Override
    public PreparedStatement setPreparedStatement (ProxyConnection proxyConnection) throws SQLException
    {
        PreparedStatement preparedStatement=proxyConnection.prepareStatement(UPDATE_OLD_ORDERS_QUERY);
        return preparedStatement;
    }

}
