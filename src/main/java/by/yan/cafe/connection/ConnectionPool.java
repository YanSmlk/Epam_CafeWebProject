package by.yan.cafe.connection;

import org.apache.logging.log4j.Level;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import static by.yan.cafe.servlet.FrontController.LOGGER;

public class ConnectionPool
{

    private static ConnectionPool instance;
    private static BlockingQueue <ProxyConnection> proxyConnectionQueue;
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);
    private static ReentrantLock reentrantLock=new ReentrantLock();

    private static int POOL_SIZE=15;
    private static final String DB_PARAMS = "property.dbParams";
    private static final String DB_POOL_SIZE="POOL_SIZE";
    private static final String DB_URL="URL";
    private static final String DB_USERNAME="USERNAME";
    private static final String DB_PASSWORD="PASSWORD";
    private static final String DB_ENCODING="ENCODING";
    private static final String DB_USE_UNICODE="USE_UNICODE";


    public static ConnectionPool getInstance()
    {
        if (!instanceCreated.get())
        {
            reentrantLock.lock();
            try
            {
                if (!instanceCreated.get())
                {
                    instance = new ConnectionPool();
                    createPool();
                    instanceCreated.set(true);
                }
            }
            finally
            {
                reentrantLock.unlock();
            }
        }
        return instance;
    }

    public ProxyConnection getConnection()
    {
        ProxyConnection proxyConnection = null;
        try
        {
            proxyConnection = proxyConnectionQueue.take();

        }
        catch (InterruptedException ex)
        {
            LOGGER.log(Level.ERROR,"Error getting connection: "+ex);
            Thread.currentThread().interrupt();
        }
        return proxyConnection;
    }

    private static void createPool()
    {
        ResourceBundle resourceBundle;
        Properties dbProperties;
        try
        {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        }
        catch (SQLException ex)
        {
            LOGGER.log(Level.FATAL, "JDBC Driver register error: "+ex);
            throw new RuntimeException("JDBC Driver register error: "+ex);
        }
        try
        {
            resourceBundle = ResourceBundle.getBundle(DB_PARAMS);
            POOL_SIZE = Integer.parseInt(resourceBundle.getString(DB_POOL_SIZE));

            dbProperties = new Properties();
            dbProperties.put("user", resourceBundle.getString(DB_USERNAME));
            dbProperties.put("password", resourceBundle.getString(DB_PASSWORD));
            dbProperties.put("characterEncoding", resourceBundle.getString(DB_ENCODING));
            dbProperties.put("useUnicode", resourceBundle.getString(DB_USE_UNICODE));
        }
        catch (MissingResourceException ex)
        {
            LOGGER.log(Level.FATAL, "Cannot find database resource file 'dbParams': "+ex);
            throw new RuntimeException("Cannot find database resource file 'dbParams': "+ ex);
        }

        proxyConnectionQueue = new ArrayBlockingQueue<ProxyConnection>(POOL_SIZE);
        for (int i = 0; i < POOL_SIZE; i++)
        {
            try
            {
                Connection  connection = DriverManager.getConnection(resourceBundle.getString(DB_URL), dbProperties);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                proxyConnectionQueue.put(proxyConnection);
            }
            catch (SQLException ex)
            {
                LOGGER.log(Level.ERROR,"Error getting connection: "+ex);
            }
            catch (InterruptedException ex)
            {
                LOGGER.log(Level.ERROR,"Error adding connection to queue: "+ex);
            }
        }
        if(proxyConnectionQueue.isEmpty())
        {throw new RuntimeException("Failed to create connections with DB");}
    }

    void releaseConnection(ProxyConnection proxyConnection)
    {
        try
        {
            proxyConnectionQueue.put(proxyConnection);
        }
        catch (InterruptedException ex)
        {
            LOGGER.log(Level.ERROR,"Error returning connection to queue: "+ex);
        }
    }

    public void destroyConnections()
    {
        for (int i = 0; i < POOL_SIZE; i++)
        {
            ProxyConnection proxyConnection;
            try
            {
                proxyConnection = proxyConnectionQueue.take();
                proxyConnection.closeConnection();

            }
            catch (InterruptedException ex)
            {
                LOGGER.log(Level.ERROR, ex);
            }
            catch (SQLException ex)
            {
                LOGGER.log(Level.ERROR, ex);
            }
        }
        try
        {
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements())
            {
                Driver driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            }
        }
        catch (SQLException ex)
        {
            LOGGER.log(Level.ERROR, ex);
        }
    }

}
