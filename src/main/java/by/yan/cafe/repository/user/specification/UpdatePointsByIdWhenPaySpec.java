package by.yan.cafe.repository.user.specification;

import by.yan.cafe.connection.ProxyConnection;
import by.yan.cafe.repository.SqlSpecification;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdatePointsByIdWhenPaySpec implements SqlSpecification
{
    private static final String UPDATE_POINTS_BY_ID_WHEN_PAY_QUERY="UPDATE user SET Points = Points - ? WHERE idUser =?";
    private int idUser;
    private int pointsToDec;

    public UpdatePointsByIdWhenPaySpec(int idUser, int pointsToAdd)
    {
        this.idUser=idUser;
        this.pointsToDec=pointsToDec;
    }

    @Override
    public PreparedStatement setPreparedStatement (ProxyConnection proxyConnection) throws SQLException
    {
        PreparedStatement preparedStatement=proxyConnection.prepareStatement(UPDATE_POINTS_BY_ID_WHEN_PAY_QUERY);
        preparedStatement.setInt(1,pointsToDec);
        preparedStatement.setInt(2,idUser);
        return preparedStatement;
    }

}
