package model;

import java.sql.*;

public class UserDAO {

    String dbURL = "jdbc:mysql://localhost:3306/FifthFist";
    String dbUser = "root";
    String dbPass = "password";

    public User checkLogin(String user, String password) throws SQLException,
            ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        //To be amend
        String sql = "SELECT * FROM login WHERE username= ? and password= ?";
        Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPass);
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user);
        statement.setString(2, password);
        System.out.println(statement.toString());
        ResultSet result = statement.executeQuery();
        User cUser = null;
        if (result.next()) {
            cUser = new User(result.getString("id"),result.getString("username"),result.getString("password"));
            System.out.println(cUser.toString());
        }
        connection.close();
        return cUser;
    }

    public User getUserByID(String uuid) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        //To be amend
        String sql = "SELECT * FROM users WHERE username = ? and password = ?";

        Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPass);

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, uuid);
        ResultSet result = statement.executeQuery();

        User cUser = null;
        if (result.next()) {
            cUser = new User(result.getString("id"),result.getString("name"),"");
        }
        connection.close();
        return cUser;
    }
}
