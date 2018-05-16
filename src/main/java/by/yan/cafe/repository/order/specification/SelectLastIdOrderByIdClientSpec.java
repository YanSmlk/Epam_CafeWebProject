package by.yan.cafe.repository.order.specification;

import by.yan.cafe.connection.ProxyConnection;
import by.yan.cafe.repository.SqlSpecification;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectLastIdOrderByIdClientSpec implements SqlSpecification
{
    private static final String SELECT_ID_ORDER_BY_ID_USER_QUERY="SELECT idOrder FROM cafedb.order " +
            "WHERE idClient=? ORDER BY idOrder DESC LIMIT 1";

    private int idClient;

    public SelectLastIdOrderByIdClientSpec(int idClient)
    {
        this.idClient=idClient;
    }

    @Override
    public PreparedStatement setPreparedStatement (ProxyConnection proxyConnection) throws SQLException
    {
        PreparedStatement preparedStatement=proxyConnection.prepareStatement(SELECT_ID_ORDER_BY_ID_USER_QUERY);
        preparedStatement.setInt(1,idClient);
        return preparedStatement;
    }

}
