package com.parkit.parkingsystem.integration;

import com.parkit.parkingsystem.config.DataBaseConfig;
import junit.framework.Assert;
import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.SQLException;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class DataBaseIT {
    private static Connection connection;
    private static  DataBaseConfig dataBaseConfig;

    @BeforeAll
    static void setup() throws SQLException, ClassNotFoundException {
        dataBaseConfig= new DataBaseConfig();
        connection = dataBaseConfig.getConnection();
    }
    @BeforeEach
    public  void beforeEach() throws SQLException, ClassNotFoundException {
        if (connection.isClosed()) {
            connection = dataBaseConfig.getConnection();
        }
    }
    @AfterAll
    static void after() throws SQLException {
      connection.close();
    }

    @Test
    public void testGetConnection() throws SQLException {

        Assert.assertNotNull(connection);
        Assert.assertTrue(connection.isValid(0));

    }
    @Test
    public void testStatment() throws SQLException {
        Assert.assertNotNull(connection);
        Assert.assertTrue(connection.isValid(0));
        Assert.assertTrue(
                connection.prepareStatement("select * from ticket ").executeQuery().next());

    }

    @Test
    public void testCloseDb() throws SQLException {
       dataBaseConfig.closeConnection(connection);
        Assert.assertTrue(
                connection.isClosed());

    }


}
