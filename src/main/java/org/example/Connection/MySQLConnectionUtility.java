package org.example.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySQLConnectionUtility {

    private static String MYSQL_HOST = "jdbc:mysql://localhost:3306/dbstorage";
    private static String MYSQL_USER_NAME = "root";
    private static String MYSQL_PASSWORD = "Vijay123$";

    private static Connection connection;

    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLConnectionUtility.class);

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to register mysql driver", e);
        }
    }
    public static Connection getConnectionInSingleTon() {
        if(connection == null) {
            connection = createConnection(MYSQL_HOST, MYSQL_USER_NAME, MYSQL_PASSWORD);
        }
        return connection;
    }

    public static Connection getConnection() {
        if (connection == null) {
            synchronized (MySQLConnectionUtility.class) {
                if(connection == null) {
                    connection = createConnection(MYSQL_HOST, MYSQL_USER_NAME, MYSQL_PASSWORD);
                }
            }
        }
        return connection;
    }

    public static Connection getConnectionNonSingleTon() {
        Connection mySQLConnection = createConnection(MYSQL_HOST, MYSQL_USER_NAME, MYSQL_PASSWORD);
        return mySQLConnection;
    }

    private static Connection createConnection(String mysqlUrl, String mysqlUserName, String mysqlPassword) {
        try {
            LOGGER.info("Creating database connection to {} and username {}", mysqlUrl, mysqlUserName);
            return DriverManager.getConnection(mysqlUrl, mysqlUserName, mysqlPassword);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create connection", e);
        }
    }

    public static void closeConnection(Connection connection){
        try{
            connection.close();
        } catch (SQLException e) {
            LOGGER.warn("SQL Exception while closing Mysql Connection {} With User {}", MYSQL_HOST, MYSQL_USER_NAME, e);
        }
    }
}
