package by.yan.cafe.repository.user.specification;

import by.yan.cafe.connection.ProxyConnection;
import by.yan.cafe.repository.SqlSpecification;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateMoneyByIdWhenPaySpec implements SqlSpecification
{

    private static final String UPDATE_MONEY_BY_ID_WHEN_PAY_QUERY="UPDATE user SET MoneyAmount = MoneyAmount - ? WHERE idUser =?";
    private int idUser;
    private int moneyToDec;

    public UpdateMoneyByIdWhenPaySpec(int idUser, int moneyToDec)
    {
        this.idUser=idUser;
        this.moneyToDec=moneyToDec;
    }

    @Override
    public PreparedStatement setPreparedStatement (ProxyConnection proxyConnection) throws SQLException
    {
        PreparedStatement preparedStatement=proxyConnection.prepareStatement(UPDATE_MONEY_BY_ID_WHEN_PAY_QUERY);
        preparedStatement.setInt(1,moneyToDec);
        preparedStatement.setInt(2,idUser);
        return preparedStatement;
    }

}
