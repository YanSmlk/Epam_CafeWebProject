package by.yan.cafe.repository.food.specification;

import by.yan.cafe.connection.ProxyConnection;
import by.yan.cafe.repository.SqlSpecification;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateFoodPriceToNegativeSpec implements SqlSpecification
{
    private static final String UPDATE_MEAL_PRICE_TO_NEGATIVE_QUERY="UPDATE meal SET MealPrice=-MealPrice WHERE idMeal =?";
    private static final String UPDATE_DESSERT_PRICE_TO_NEGATIVE_QUERY="UPDATE dessert SET DessertPrice=-DessertPrice WHERE idDessert =?";
    private static final String UPDATE_DRINK_PRICE_TO_NEGATIVE_QUERY="UPDATE drink SET DrinkPrice=-DrinkPrice WHERE idDrink =?";
    private static final String MEAL_TYPE="meal";
    private static final String DESSERT_TYPE="dessert";
    private static final String DRINK_TYPE="drink";
    private int foodId;
    private String foodType;

    public UpdateFoodPriceToNegativeSpec(int foodId,String foodType)
    {
        this.foodId=foodId;
        this.foodType=foodType;
    }

    @Override
    public PreparedStatement setPreparedStatement (ProxyConnection proxyConnection) throws SQLException
    {
        PreparedStatement preparedStatement=null;
        switch (foodType)
        {
            case MEAL_TYPE:
                preparedStatement=proxyConnection.prepareStatement(UPDATE_MEAL_PRICE_TO_NEGATIVE_QUERY);
                break;
            case DESSERT_TYPE:
                preparedStatement=proxyConnection.prepareStatement(UPDATE_DESSERT_PRICE_TO_NEGATIVE_QUERY);
                break;
            case DRINK_TYPE:
                preparedStatement=proxyConnection.prepareStatement(UPDATE_DRINK_PRICE_TO_NEGATIVE_QUERY);
                break;
        }
        preparedStatement.setInt(1,foodId);
        return preparedStatement;
    }

}
