package by.yan.cafe.repository.food.specification;

import by.yan.cafe.connection.ProxyConnection;
import by.yan.cafe.repository.SqlSpecification;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectOutOfStockDrinkSpec implements SqlSpecification
{
    private static final String SELECT_OUT_OF_STOCK_DRINK_QUERY="SELECT idDrink, DrinkName, DrinkDescription, DrinkPrice," +
            " DrinkImgPath FROM cafedb.drink WHERE DrinkPrice<0";

    @Override
    public PreparedStatement setPreparedStatement (ProxyConnection proxyConnection) throws SQLException
    {
        PreparedStatement preparedStatement=proxyConnection.prepareStatement(SELECT_OUT_OF_STOCK_DRINK_QUERY);
        return preparedStatement;
    }
}

