package by.yan.cafe.repository.food.specification;

import by.yan.cafe.connection.ProxyConnection;
import by.yan.cafe.repository.SqlSpecification;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectMealsSpec implements SqlSpecification
{
    private static final String SELECT_MEALS="SELECT idMeal, MealName, MealDescription, MealPrice, MealImgPath FROM cafedb.meal " +
            "WHERE MealPrice>0";

    @Override
    public PreparedStatement setPreparedStatement (ProxyConnection proxyConnection) throws SQLException
    {
        PreparedStatement preparedStatement=proxyConnection.prepareStatement(SELECT_MEALS);
        return preparedStatement;
    }
}
