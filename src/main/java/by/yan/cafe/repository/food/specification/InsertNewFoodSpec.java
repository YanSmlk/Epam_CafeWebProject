package by.yan.cafe.repository.food.specification;

import by.yan.cafe.connection.ProxyConnection;
import by.yan.cafe.repository.SqlSpecification;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertNewFoodSpec implements SqlSpecification
{
    private static final String INSERT_NEW_MEAL_QUERY="INSERT INTO cafedb.meal " +
            "(MealName,MealPrice,MealDescription,MealImgPath) VALUES (?,?,?,?)";
    private static final String INSERT_NEW_DESSERT_QUERY="INSERT INTO cafedb.dessert " +
            "(DessertName,DessertPrice,DessertDescription,DessertImgPath) VALUES (?,?,?,?)";
    private static final String INSERT_NEW_DRINK_QUERY="INSERT INTO cafedb.drink " +
            "(DrinkName,DrinkPrice,DrinkDescription,DrinkImgPath) VALUES (?,?,?,?)";
    private static final String MEAL_TYPE="Meal";
    private static final String DESSERT_TYPE="Dessert";
    private static final String DRINK_TYPE="Drink";
    private String newFoodName;
    private String newFoodDescr;
    private byte newFoodPrice;
    private String newFoodPicPath;
    private String newFoodType;

    public InsertNewFoodSpec(String newFoodType, String newFoodName,String newFoodDescr,byte newFoodPrice,String newFoodPicPath)
    {
        this.newFoodDescr=newFoodDescr;
        this.newFoodName=newFoodName;
        this.newFoodPicPath=newFoodPicPath;
        this.newFoodPrice=newFoodPrice;
        this.newFoodType=newFoodType;
    }

    @Override
    public PreparedStatement setPreparedStatement (ProxyConnection proxyConnection) throws SQLException
    {
        PreparedStatement preparedStatement=null;
        switch (newFoodType)
        {
            case MEAL_TYPE:
                preparedStatement=proxyConnection.prepareStatement(INSERT_NEW_MEAL_QUERY);
                break;
            case DESSERT_TYPE:
                preparedStatement=proxyConnection.prepareStatement(INSERT_NEW_DESSERT_QUERY);
                break;
            case DRINK_TYPE:
                preparedStatement=proxyConnection.prepareStatement(INSERT_NEW_DRINK_QUERY);
                break;
        }
        preparedStatement.setString(1,newFoodName);
        preparedStatement.setByte(2,newFoodPrice);
        preparedStatement.setString(3,newFoodDescr);
        preparedStatement.setString(4,newFoodPicPath);
        return preparedStatement;
    }


}
