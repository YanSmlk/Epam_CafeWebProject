package by.yan.cafe.repository;

import by.yan.cafe.connection.ProxyConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlSpecification
{
    PreparedStatement setPreparedStatement ( ProxyConnection proxyConnection) throws SQLException;
}
