package by.yan.cafe.repository.order;

import by.yan.cafe.connection.ConnectionPool;
import by.yan.cafe.connection.ProxyConnection;
import by.yan.cafe.entity.Order;
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

public class OrderRepository implements Repository
{

    private static final String SELECT__ORDERS_BY_CLIENT_LOGIN_SPEC="SelectOrdersByClientLoginSpec";
    private static final String SELECT__FOOD_OF_ORDER_BY_ORDERID_SPEC="SelectFoodOfOrderByOrderIdSpec";
    private static final String SELECT__ORDERS_BY_STATUS_SPEC="SelectOrdersByStatusSpec";

    @Override
    public void insert(SqlSpecification specification) throws RepositoryException
    {
        ProxyConnection proxyConnection=ConnectionPool.getInstance().getConnection();
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
    {
        ProxyConnection proxyConnection= ConnectionPool.getInstance().getConnection();
        try( PreparedStatement preparedStatement=specification.setPreparedStatement(proxyConnection);
             ResultSet resultSet=preparedStatement.executeQuery())
        {
            List<String> stringArr=new ArrayList<>();
            while (resultSet.next())
            {
                stringArr.add(resultSet.getString(1));
            }
            return stringArr;
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
    public List<Order> query(SqlSpecification specification) throws RepositoryException
    {
        ProxyConnection proxyConnection=ConnectionPool.getInstance().getConnection();
        try(PreparedStatement preparedStatement=specification.setPreparedStatement(proxyConnection);
            ResultSet resultSet=preparedStatement.executeQuery())
        {
            List<Order> orderArr= fillOrderArr(resultSet,specification);
            return orderArr;
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

   private List<Order> fillOrderArr(ResultSet resultSet,SqlSpecification specification) throws SQLException
   {
       List<Order> orderArr=new ArrayList<>();
       String className=specification.getClass().getSimpleName();
       switch (className)
       {
           case SELECT__ORDERS_BY_CLIENT_LOGIN_SPEC:
               while (resultSet.next())
               {
                   orderArr.add(new Order(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),
                           resultSet.getByte(4)));
               }
           break;
           case SELECT__FOOD_OF_ORDER_BY_ORDERID_SPEC:
               List<Drink> drinkArr=new ArrayList<>();
               List<Dessert> dessertArr=new ArrayList<>();
               List<Meal> mealArr=new ArrayList<>();
               while (resultSet.next())
               {
                   byte drinkAmount=resultSet.getByte(3);
                   for (int i=0;i<drinkAmount;i++)
                   {
                       drinkArr.add(new Drink(resultSet.getString(1),resultSet.getByte(2)));
                   }
                   byte dessertAmount=resultSet.getByte(6);
                   for (int i=0;i<dessertAmount;i++)
                   {
                       dessertArr.add(new Dessert(resultSet.getString(4),resultSet.getByte(5)));
                   }
                   byte mealAmount=resultSet.getByte(9);
                   for (int i=0;i<mealAmount;i++)
                   {
                       mealArr.add(new Meal(resultSet.getString(7),resultSet.getByte(8)));
                   }
               }
               orderArr.add(new Order(drinkArr,dessertArr,mealArr));
               break;
           case SELECT__ORDERS_BY_STATUS_SPEC:
               while (resultSet.next())
               {
                   orderArr.add(new Order(resultSet.getInt(1),resultSet.getString(2),
                           resultSet.getByte(3), resultSet.getString(4)));
               }
               break;
       }
       return orderArr;
   }

}
