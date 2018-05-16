package by.yan.cafe.repository.order.specification;

import by.yan.cafe.connection.ProxyConnection;
import by.yan.cafe.repository.SqlSpecification;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectFoodOfOrderByOrderIdSpec implements SqlSpecification
{
    private static final String SELECT_FOOD_OF_ORDER_BY_ORDER_ID_QUERY="SELECT drink.DrinkName,drink.DrinkPrice," +
            " foodinorder.DrinkAmount, dessert.DessertName,dessert.DessertPrice,foodinorder.DessertAmount," +
            " meal.MealName,meal.MealPrice,foodinorder.MealAmount  FROM foodinorder " +
            "LEFT JOIN drink ON drink.idDrink= foodinorder.idDrink " +
            "LEFT JOIN meal ON meal.idMeal= foodinorder.idMeal " +
            "LEFT JOIN dessert ON dessert.idDessert= foodinorder.idDessert " +
            "WHERE foodinorder.idOrder=?";
    private int id;

    public SelectFoodOfOrderByOrderIdSpec(int id)
    {
        this.id=id;
    }

    @Override
    public PreparedStatement setPreparedStatement (ProxyConnection proxyConnection) throws SQLException
    {
        PreparedStatement preparedStatement=proxyConnection.prepareStatement(SELECT_FOOD_OF_ORDER_BY_ORDER_ID_QUERY);
        preparedStatement.setInt(1,id);
        return preparedStatement;
    }

}
