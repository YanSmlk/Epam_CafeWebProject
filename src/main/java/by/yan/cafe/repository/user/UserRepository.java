package by.yan.cafe.repository.user;

import by.yan.cafe.connection.ConnectionPool;
import by.yan.cafe.connection.ProxyConnection;
import by.yan.cafe.constant.RoleType;
import by.yan.cafe.entity.User;
import by.yan.cafe.exception.RepositoryException;
import by.yan.cafe.repository.Repository;
import by.yan.cafe.repository.SqlSpecification;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository
{

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
        ProxyConnection proxyConnection=ConnectionPool.getInstance().getConnection();
        try(PreparedStatement preparedStatement=specification.setPreparedStatement(proxyConnection);
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
    public List<User> query(SqlSpecification specification) throws RepositoryException
    {
        ProxyConnection proxyConnection=ConnectionPool.getInstance().getConnection();
        try( PreparedStatement preparedStatement=specification.setPreparedStatement(proxyConnection);
             ResultSet resultSet=preparedStatement.executeQuery())
        {
            List<User> usersArr= fillUserArr(resultSet);
            return usersArr;
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

    public int[] getBalanceQuery(SqlSpecification specification) throws RepositoryException
    {
        final int BALANCE_ELEMENTS_AMOUNT=2;
        final int POINTS_ID=0;
        final int MONEY_ID=1;
        int[] balance = new int[BALANCE_ELEMENTS_AMOUNT];
        try
        {
            ProxyConnection proxyConnection=ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement=specification.setPreparedStatement(proxyConnection);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next())
            {
                balance[POINTS_ID]=resultSet.getInt(1);
                balance[MONEY_ID]=resultSet.getInt(2);
            }
            proxyConnection.close();
            return balance;
        }
        catch (SQLException ex)
        {
            throw new RepositoryException("Error data loading: "+ex);
        }
    }

    private List<User> fillUserArr(ResultSet resultSet) throws SQLException
    {
        RoleType roleType=null;
        List<User> usersArr=new ArrayList<>();

        while (resultSet.next())
        {
            switch (resultSet.getString(2))
            {
                case "CLIENT":
                    roleType=RoleType.CLIENT;
                    break;
                case "MANAGER":
                    roleType=RoleType.MANAGER;
                    break;
            }
            usersArr.add(new User
                    (resultSet.getInt(1),roleType,resultSet.getString(3),
                            resultSet.getString(4),resultSet.getString(5),
                            resultSet.getInt(6),resultSet.getInt(7)));
        }
        return usersArr;
    }

}
