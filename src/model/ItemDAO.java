package model;

import common.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/FifthFist";
    private static final String MYSQL_USERNAME = "fifthfist";
    private static final String MYSQL_PASSWORD = "12345678";

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
            ex.printStackTrace();
        }

    }

    public ItemBean findItem(int id) throws DAOException {
        if (conn == null)
            getConnection();


        PreparedStatement statement = null;
        ResultSet result = null;

        try{
            String sql = "SELECT * FROM items WHERE id=" + id ;

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
        String description = result.getString("description");
        Timestamp updated = result.getTimestamp("updated");
        Timestamp created = result.getTimestamp("created");
        return new ItemBean(id, price, title, players, directors, description, updated, created);
    }

    public List<ItemBean> findAll() throws DAOException{
     return this.findAll( "%" , 0);
    }

    public List<ItemBean> findAll(String keyword) throws DAOException{
        return this.findAll(keyword,0);
    }

    public List<ItemBean> findAll(String keyword, int page) throws DAOException{
        if (conn == null) {
            getConnection();
        }
        System.out.println("made it to findALL with params: "+keyword+","+page);
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<ItemBean> itemlist = new ArrayList<>();

        try{
            System.out.println("made it to try with params: "+keyword+","+page);
            String sql = "SELECT * FROM items WHERE title LIKE '%" + keyword +"%' LIMIT 10 OFFSET " + (page*10);
            System.out.println(sql);
            System.out.println("made it to conn");
            System.out.println(conn);
            statement = conn.prepareStatement(sql);
            result = statement.executeQuery();
            System.out.println("made it to result");
            System.out.println(result);
//            statement.setString(1,keyword);
//            statement.setString(2, String.valueOf(page));

            while(result.next()){
                int id = result.getInt("id");
                ItemBean itemBean = setItemBean(id, result);
                itemlist.add(itemBean);
                System.out.println("made it to result.next()");
                System.out.println(itemBean);
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
                return itemlist;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("Fail to close rescues");
            }
        }
    }


}
