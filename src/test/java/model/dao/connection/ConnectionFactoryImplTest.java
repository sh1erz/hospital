package model.dao.connection;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertNotNull;

public class ConnectionFactoryImplTest {

    @Test
    public void getInstance() {
        ConnectionFactoryImpl.getInstance();
    }

    @Test
    public void getConnection(){
        Connection connection = ConnectionFactoryImpl.getInstance().getConnection();
        assertNotNull(connection);
    }

}