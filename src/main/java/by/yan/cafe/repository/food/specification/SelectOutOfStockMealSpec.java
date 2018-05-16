package by.yan.cafe.repository.food.specification;

import by.yan.cafe.connection.ProxyConnection;
import by.yan.cafe.repository.SqlSpecification;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectOutOfStockMealSpec implements SqlSpecification
{
    private static final String SELECT_OUT_OF_STOCK_Meal_QUERY="SELECT idMeal, MealName, MealDescription, MealPrice, MealImgPath" +
            " FROM cafedb.meal WHERE MealPrice<0 ";

    @Override
    public PreparedStatement setPreparedStatement (ProxyConnection proxyConnection) throws SQLException
    {
        PreparedStatement preparedStatement=proxyConnection.prepareStatement(SELECT_OUT_OF_STOCK_Meal_QUERY);
        return preparedStatement;
    }

}
