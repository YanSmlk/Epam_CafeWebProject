package by.yan.cafe.repository.user.specification;

import by.yan.cafe.connection.ProxyConnection;
import by.yan.cafe.constant.RoleType;
import by.yan.cafe.repository.SqlSpecification;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertNewClientSpec implements SqlSpecification
{
    private static final String INSERT_NEW_CLIENT_QUERY="INSERT INTO user (Role,Email,Password,Login) VALUES (?,?,?,?)";
    private String login;
    private String password;
    private String email;
    private RoleType role= RoleType.CLIENT;

    public InsertNewClientSpec(String login,String password, String email)
    {
        this.login=login;
        this.password=password;
        this.email=email;
    }

    @Override
    public PreparedStatement setPreparedStatement (ProxyConnection proxyConnection) throws SQLException
    {
        PreparedStatement preparedStatement=proxyConnection.prepareStatement(INSERT_NEW_CLIENT_QUERY);
        preparedStatement.setString(1,role.toString());
        preparedStatement.setString(2,email);
        preparedStatement.setString(3,password);
        preparedStatement.setString(4,login);
        return preparedStatement;
    }

}
