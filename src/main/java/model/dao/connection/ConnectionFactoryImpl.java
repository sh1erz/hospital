package model.dao.connection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public final class ConnectionFactoryImpl implements ConnectionFactory {

    private static volatile ConnectionFactoryImpl instance;
    private Connection connection;

    private ConnectionFactoryImpl() {
        try {
            Properties props = new Properties();
            props.load(ConnectionFactoryImpl.class.getResourceAsStream("/db.properties"));
            String dbConnUrl = props.getProperty("db.conn.url");
            String dbUserName = props.getProperty("db.username");
            String dbPassword = props.getProperty("db.password");
            connection = DriverManager.getConnection(dbConnUrl, dbUserName, dbPassword);
        } catch (SQLException | IOException throwable) {
            throwable.printStackTrace();
        }
    }

    public static ConnectionFactoryImpl getInstance() {
        ConnectionFactoryImpl result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ConnectionFactoryImpl.class) {
            if (instance == null) {
                instance = new ConnectionFactoryImpl();
            }
            return instance;
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
