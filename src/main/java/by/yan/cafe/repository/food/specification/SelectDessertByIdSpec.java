package by.yan.cafe.repository.food.specification;

import by.yan.cafe.connection.ProxyConnection;
import by.yan.cafe.repository.SqlSpecification;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectDessertByIdSpec implements SqlSpecification
{
    private static final String SELECT_DESSERT_BY_ID_QUERY="SELECT DessertName, DessertPrice FROM cafedb.dessert WHERE idDessert=?";
    private int id;

    public SelectDessertByIdSpec(int id)
    {
        this.id=id;
    }

    @Override
    public PreparedStatement setPreparedStatement (ProxyConnection proxyConnection) throws SQLException
    {
        PreparedStatement preparedStatement=proxyConnection.prepareStatement(SELECT_DESSERT_BY_ID_QUERY);
        preparedStatement.setInt(1,id);
        return preparedStatement;
    }

}