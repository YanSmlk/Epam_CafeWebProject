package by.yan.cafe.repository.user.specification;

import by.yan.cafe.connection.ProxyConnection;
import by.yan.cafe.repository.SqlSpecification;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdatePointsByLoginWhenIncSpec implements SqlSpecification
{
    private static final String UPDATE_POINTS_BY_ID_WHEN_INC_QUERY="UPDATE user SET Points = Points + ? WHERE Login =?";
    private String login;
    private int pointsToAdd;

    public UpdatePointsByLoginWhenIncSpec(String login, int pointsToAdd)
    {
        this.login=login;
        this.pointsToAdd=pointsToAdd;
    }

    @Override
    public PreparedStatement setPreparedStatement (ProxyConnection proxyConnection) throws SQLException
    {
        PreparedStatement preparedStatement=proxyConnection.prepareStatement(UPDATE_POINTS_BY_ID_WHEN_INC_QUERY);
        preparedStatement.setInt(1,pointsToAdd);
        preparedStatement.setString(2,login);
        return preparedStatement;
    }

}
