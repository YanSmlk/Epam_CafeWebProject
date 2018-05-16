package by.yan.cafe.repository.food.specification;

import by.yan.cafe.connection.ProxyConnection;
import by.yan.cafe.repository.SqlSpecification;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectMealByIdSpec implements SqlSpecification
{
    private static final String SELECT_MEAL_BY_ID_QUERY="SELECT MealName, MealPrice FROM cafedb.meal WHERE idMeal=?";
    private int id;

    public SelectMealByIdSpec(int id)
    {
        this.id=id;
    }

    @Override
    public PreparedStatement setPreparedStatement (ProxyConnection proxyConnection) throws SQLException
    {
        PreparedStatement preparedStatement=proxyConnection.prepareStatement(SELECT_MEAL_BY_ID_QUERY);
        preparedStatement.setInt(1,id);
        return preparedStatement;
    }

}
