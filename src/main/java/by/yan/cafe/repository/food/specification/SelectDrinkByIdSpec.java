package by.yan.cafe.repository.food.specification;

import by.yan.cafe.connection.ProxyConnection;
import by.yan.cafe.repository.SqlSpecification;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectDrinkByIdSpec implements SqlSpecification
{
    private static final String SELECT_DRINK_BY_ID_QUERY="SELECT DrinkName, DrinkPrice FROM cafedb.drink WHERE idDrink=?";
    private int id;

    public SelectDrinkByIdSpec(int id)
    {
        this.id=id;
    }

    @Override
    public PreparedStatement setPreparedStatement (ProxyConnection proxyConnection) throws SQLException
    {
        PreparedStatement preparedStatement=proxyConnection.prepareStatement(SELECT_DRINK_BY_ID_QUERY);
        preparedStatement.setInt(1,id);
        return preparedStatement;
    }

}