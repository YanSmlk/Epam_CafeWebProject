package by.yan.cafe.repository.order.specification;

import by.yan.cafe.connection.ProxyConnection;
import by.yan.cafe.repository.SqlSpecification;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertFoodInOrderSpec implements SqlSpecification
{
    private static final String INSERT_FOOD_IN_ORDER_QUERY="INSERT INTO `cafedb`.`foodinorder` " +
            "( `idOrder`, `idDrink`, `DrinkAmount`, `idDessert`, `DessertAmount`, `idMeal`, `MealAmount`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private int idOrder;
    private Integer idDrink;
    private Integer DrinkAmount;
    private Integer idDessert;
    private Integer DessertAmount;
    private Integer idMeal;
    private Integer MealAmount;

    public InsertFoodInOrderSpec(int idOrder, Integer idDrink, Integer DrinkAmount, Integer idDessert, Integer DessertAmount,
                                 Integer idMeal, Integer MealAmount)
    {
        this.idOrder=idOrder;
        this.idDrink=idDrink;
        this.DrinkAmount=DrinkAmount;
        this.idDessert=idDessert;
        this.DessertAmount=DessertAmount;
        this.idMeal=idMeal;
        this.MealAmount=MealAmount;
    }

    @Override
    public PreparedStatement setPreparedStatement (ProxyConnection proxyConnection) throws SQLException
    {
        PreparedStatement preparedStatement=proxyConnection.prepareStatement(INSERT_FOOD_IN_ORDER_QUERY);
        preparedStatement.setInt(1,idOrder);
        if(idDrink!=null)
        {
            preparedStatement.setInt(2,idDrink);
            preparedStatement.setInt(3,DrinkAmount);
        }
        else
        {
            preparedStatement.setNull(2,java.sql.Types.INTEGER);
            preparedStatement.setNull(3,java.sql.Types.INTEGER);
        }
        if(idDessert!=null)
        {
            preparedStatement.setInt(4,idDessert);
            preparedStatement.setInt(5,DessertAmount);
        }
        else
        {
            preparedStatement.setNull(4,java.sql.Types.INTEGER);
            preparedStatement.setNull(5,java.sql.Types.INTEGER);
        }
        if(idMeal!=null)
        {
            preparedStatement.setInt(6,idMeal);
            preparedStatement.setInt(7,MealAmount);
        }
        else
        {
            preparedStatement.setNull(6,java.sql.Types.INTEGER);
            preparedStatement.setNull(7,java.sql.Types.INTEGER);
        }
        return preparedStatement;
    }

}
