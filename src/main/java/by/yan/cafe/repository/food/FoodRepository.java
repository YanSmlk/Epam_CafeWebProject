package by.yan.cafe.repository.food;

import by.yan.cafe.connection.ConnectionPool;
import by.yan.cafe.connection.ProxyConnection;
import by.yan.cafe.entity.food.BaseFood;
import by.yan.cafe.entity.food.impl.Dessert;
import by.yan.cafe.entity.food.impl.Drink;
import by.yan.cafe.entity.food.impl.Meal;
import by.yan.cafe.exception.RepositoryException;
import by.yan.cafe.repository.Repository;
import by.yan.cafe.repository.SqlSpecification;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodRepository implements Repository
{
    private static final String SELECT_OUT_OF_STOCK_DESSERT_SPEC="SelectOutOfStockDessertSpec";
    private static final String SELECT_OUT_OF_STOCK_DRINK_SPEC="SelectOutOfStockDrinkSpec";
    private static final String SELECT_OUT_OF_STOCK_MEAL_SPEC="SelectOutOfStockMealSpec";
    private static final String SELECT_MEAL_SPEC="SelectMealsSpec";
    private static final String SELECT_DESSERT_SPEC="SelectDessertsSpec";
    private static final String SELECT_DRINK_SPEC="SelectDrinksSpec";
    private static final String SELECT_MEAL_BY_ID_SPEC="SelectMealByIdSpec";
    private static final String SELECT_DESSERT_BY_ID_SPEC="SelectDessertByIdSpec";
    private static final String SELECT_DRINK_BY_ID_SPEC="SelectDrinkByIdSpec";

    @Override
    public void insert(SqlSpecification specification) throws RepositoryException
    {
        ProxyConnection proxyConnection= ConnectionPool.getInstance().getConnection();
        try(PreparedStatement preparedStatement=specification.setPreparedStatement(proxyConnection))
        {
            preparedStatement.executeUpdate();
        }
        catch (SQLException ex)
        {
            throw new RepositoryException("Error data loading: "+ex);
        }
        finally
        {
            proxyConnection.close();
        }
    }

    @Override
    public List<String> stringQuery(SqlSpecification specification) throws RepositoryException
    {return null;}

    @Override
    public List<BaseFood> query(SqlSpecification specification) throws RepositoryException
    {
        ProxyConnection proxyConnection=ConnectionPool.getInstance().getConnection();
        try( PreparedStatement preparedStatement=specification.setPreparedStatement(proxyConnection);
             ResultSet resultSet=preparedStatement.executeQuery())
        {
            return fillFoodArr(resultSet,specification);
        }
        catch (SQLException ex)
        {
            throw new RepositoryException("Error data loading: "+ex);
        }
        finally
        {
            proxyConnection.close();
        }
    }

    private List<BaseFood> fillFoodArr(ResultSet resultSet,SqlSpecification specification) throws SQLException
    {
        List<BaseFood> foodArr=new ArrayList<>();
        String className=specification.getClass().getSimpleName();
        switch (className)
        {
            case SELECT_MEAL_SPEC:
                while (resultSet.next())
                {
                    foodArr.add(new Meal(resultSet.getInt(1),resultSet.getString(2),
                            resultSet.getString(3),resultSet.getByte(4),resultSet.getString(5)));
                }
                break;
            case SELECT_DESSERT_SPEC:
                while (resultSet.next())
                {
                    foodArr.add(new Dessert(resultSet.getInt(1),resultSet.getString(2),
                            resultSet.getString(3),resultSet.getByte(4),resultSet.getString(5)));
                }
                break;
            case SELECT_DRINK_SPEC:
                while (resultSet.next())
                {
                    foodArr.add(new Drink(resultSet.getInt(1),resultSet.getString(2),
                            resultSet.getString(3),resultSet.getByte(4),resultSet.getString(5)));
                }
                break;
            case SELECT_MEAL_BY_ID_SPEC:
                while (resultSet.next())
                {
                    foodArr.add(new Meal(resultSet.getString(1),resultSet.getByte(2)));
                }
                break;
            case SELECT_DESSERT_BY_ID_SPEC:
                while (resultSet.next())
                {
                    foodArr.add(new Dessert(resultSet.getString(1),resultSet.getByte(2)));
                }
                break;
            case SELECT_DRINK_BY_ID_SPEC:
                while (resultSet.next())
                {
                    foodArr.add(new Drink(resultSet.getString(1),resultSet.getByte(2)));
                }
                break;
            case SELECT_OUT_OF_STOCK_DESSERT_SPEC:
                while (resultSet.next())
                {
                    foodArr.add(new Dessert(resultSet.getInt(1),resultSet.getString(2),
                            resultSet.getString(3),resultSet.getByte(4),resultSet.getString(5)));
                }
                break;
            case SELECT_OUT_OF_STOCK_DRINK_SPEC:
                while (resultSet.next())
                {
                    foodArr.add(new Drink(resultSet.getInt(1),resultSet.getString(2),
                            resultSet.getString(3),resultSet.getByte(4),resultSet.getString(5)));
                }
                break;
            case SELECT_OUT_OF_STOCK_MEAL_SPEC:
                while (resultSet.next())
                {
                    foodArr.add(new Meal(resultSet.getInt(1),resultSet.getString(2),
                            resultSet.getString(3),resultSet.getByte(4),resultSet.getString(5)));
                }
                break;
        }
        return foodArr;
    }

}
