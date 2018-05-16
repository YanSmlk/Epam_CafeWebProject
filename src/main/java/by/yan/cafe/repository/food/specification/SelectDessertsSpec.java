package by.yan.cafe.repository.food.specification;

import by.yan.cafe.connection.ProxyConnection;
import by.yan.cafe.repository.SqlSpecification;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectDessertsSpec implements SqlSpecification
{
    private static final String SELECT_DESSERTS="SELECT idDessert, DessertName, DessertDescription, DessertPrice, DessertImgPath " +
            "FROM cafedb.dessert WHERE DessertPrice>0";

    @Override
    public PreparedStatement setPreparedStatement (ProxyConnection proxyConnection) throws SQLException
    {
        PreparedStatement preparedStatement=proxyConnection.prepareStatement(SELECT_DESSERTS);
        return preparedStatement;
    }

}
