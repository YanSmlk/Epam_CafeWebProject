package by.yan.cafe.connection;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ConnectionPoolTest
{

    @Test
    public void getConnectionTest()
    {
        ProxyConnection proxyConnection= ConnectionPool.getInstance().getConnection();
        Assert.assertTrue(proxyConnection!=null);
        proxyConnection.close();
    }

}
