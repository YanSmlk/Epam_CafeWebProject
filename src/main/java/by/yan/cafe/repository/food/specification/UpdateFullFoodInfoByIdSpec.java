package by.yan.cafe.repository.food.specification;

import by.yan.cafe.connection.ProxyConnection;
import by.yan.cafe.repository.SqlSpecification;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateFullFoodInfoByIdSpec implements SqlSpecification
{
    private static final String UPDATE_MEAL_INFO_QUERY="UPDATE meal SET MealName = ?, MealDescription=?, MealPrice=?,MealImgPath=? WHERE idMeal =?";
    private static final String UPDATE_DESSERT_INFO_QUERY="UPDATE dessert SET DessertName = ?,DessertDescription=?, DessertPrice=?, DessertImgPath=? WHERE idDessert =?";
    private static final String UPDATE_DRINK_INFO_QUERY="UPDATE drink SET DrinkName = ?, DrinkDescription=?, DrinkPrice=?, DrinkImgPath=? WHERE idDrink =?";
    private static final String MEAL_TYPE="meal";
    private static final String DESSERT_TYPE="dessert";
    private static final String DRINK_TYPE="drink";
    private String name;
    private String descr;
    private byte price;
    private String picPath;
    private String foodType;
    private int foodId;

    public UpdateFullFoodInfoByIdSpec(String name, String descr, byte price, String foodType, int foodId, String picPath)
    {
        this.name=name;
        this.descr=descr;
        this.price=price;
        this.foodType=foodType;
        this.foodId=foodId;
        this.picPath = picPath;
    }

    @Override
    public PreparedStatement setPreparedStatement (ProxyConnection proxyConnection) throws SQLException
    {
        PreparedStatement preparedStatement=null;
        switch (foodType)
        {
            case MEAL_TYPE:
                preparedStatement=proxyConnection.prepareStatement(UPDATE_MEAL_INFO_QUERY);
                break;
            case DESSERT_TYPE:
                preparedStatement=proxyConnection.prepareStatement(UPDATE_DESSERT_INFO_QUERY);
                break;
            case DRINK_TYPE:
                preparedStatement=proxyConnection.prepareStatement(UPDATE_DRINK_INFO_QUERY);
                break;
        }
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,descr);
        preparedStatement.setByte(3,price);
        preparedStatement.setString(4,picPath);
        preparedStatement.setInt(5,foodId);
        return preparedStatement;
    }

}
