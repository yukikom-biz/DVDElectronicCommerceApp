package model;

import common.DAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ItemDAO {

    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/";
    private static final String MYSQL_USERNAME = "user_name";
    private static final String MYSQL_PASSWORD = "password";

    private Connection conn;
    public ItemDAO() throws DAOException {
        getConnection();
    }

    public void close() throws DAOException {
        if (null != conn) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException ex) {
//                throw new DAOException("Failed to close connection of database.", ex);
            }
        }
    }

    private void getConnection() throws DAOException {

        try {

            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);

        } catch (ClassNotFoundException | SQLException ex) {
//            throw new DAOException("Failed to connect to database.", ex);
        }

    }


}
