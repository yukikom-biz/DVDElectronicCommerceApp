package model;

import common.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public ItemBean findItem(int id) throws DAOException {
        if (conn == null)
            getConnection();


        PreparedStatement statement = null;
        ResultSet result = null;

        try{
            String sql = "SELECT * FROM item WHERE id=" + id ;
            statement = conn.prepareStatement(sql);
            result = statement.executeQuery();
            statement.setString(1, String.valueOf(id));

            return setItemBean(id, result);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Fail to get records");

        }finally {
            try {
                if (result != null)result.close();
                if (statement != null)statement.close();
                close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("Fail to close rescues");
            }
        }

    }

    private ItemBean setItemBean(int id, ResultSet result) throws SQLException {
        int price = result.getInt("price");
        String title = result.getString("title");
        String players = result.getString("players");
        String directors = result.getString("directors");
        Timestamp updated = result.getTimestamp("updated");
        Timestamp created = result.getTimestamp("created");
        return new ItemBean(id, price, title, players, directors, updated, created);
    }

    public List<ItemBean> findAll() throws DAOException{
     return this.findAll( "%" , 0);
    }

    public List<ItemBean> findAll(String keyword) throws DAOException{
        return this.findAll(keyword,0);
    }

    public List<ItemBean> findAll(String keyword, int page) throws DAOException{
        if (conn == null)
            getConnection();


        PreparedStatement statement = null;
        ResultSet result = null;

        try{
            String sql = String.format("SELECT * FROM item WHERE title LIKE %s LIMIT 10 OFFSET %s",keyword,page*10);

            statement = conn.prepareStatement(sql);
            result = statement.executeQuery();
            statement.setString(1,keyword);
            statement.setString(2, String.valueOf(page));

            ArrayList<ItemBean> itemlist = new ArrayList<>();
            while(result.next()){
                int id = result.getInt("id");
                ItemBean itemBean = setItemBean(id, result);
                itemlist.add(itemBean);
            }
            return itemlist;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Fail to get records");

        }finally {
            try {
                if (result != null)result.close();
                if (statement != null)statement.close();
                close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("Fail to close rescues");
            }
        }

    }


}
