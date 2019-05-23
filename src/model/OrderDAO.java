package model;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;

public class OrderDAO {
    private String dbURL = "jdbc:mysql://localhost:3306/fifthfist";
    private String dbUSER = "fifthfist";
    private String dbPASS= "12345678";

    public int saveOrder(User user, CartBean bean){
        ResultSet rs = null;
        PreparedStatement st=null;
        try(Connection con = DriverManager.getConnection(dbURL,dbUSER,dbPASS)){
            int orderNumber = 0;
            String sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'fifthfist' AND TABLE_NAME = 'order'";
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            if(rs.next()){
                orderNumber = rs.getInt(1);
            }
            rs.close();
            st.close();
            sql = "INSERT INTO order VALUES(?,?,?,?,?,?,?)";
            st = con.prepareStatement(sql);
            st.setInt(1,orderNumber);
            st.setString(2,"");
            st.setString(3,"");
            st.setString(4,"");
            st.setString(5,"");
            java.util.Date today = new java.util.Date();
            Time obj = new java.sql.Time(today.getTime());
            st.setTime(6,obj);
            st.setTime(7,obj);
            st.executeUpdate();
            st.close();


            /*
             *
             *                     order_id SERIAL,
             *                     name VARCHAR(255) NOT NULL,
             *                     postal CHAR(8) NOT NULL,
             *                     address VARCHAR(255) NOT NULL,
             *                     phone VARCHAR(255),
             *                     updated DATETIME,
             *                     created DATETIME,
             *                     PRIMARY KEY (order_id)
             *
             */

            //Update Order
            int orderItemID = 0;
            sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'fifthfist' AND TABLE_NAME = 'order'";
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            if(rs.next()){
                orderItemID = rs.getInt(1);
            }

            Map<Integer,CartBean> items = bean.getItems();
            Collection<CartBean> list = items.values();
            for(CartBean item:list){
                sql = "INSERT INTO order_items VALUES(?,?,?,?,?,?,?)";
                st = con.prepareStatement(sql);
                st.setInt(1,orderItemID);
                st.setInt(2,orderNumber);
                //Need further amendment
                st.setInt(3,item.getId());
                st.setInt(4,item.getPrice());
                st.setInt(5,item.getQuantity());
                st.setTime(6,obj);
                st.setTime(7,obj);
                st.executeUpdate();
                st.close();
            }
            /*
            CREATE TABLE order_items (
                    order_items_id SERIAL,
                    order_id BIGINT UNSIGNED NOT NULL,
                    item_id BIGINT UNSIGNED NOT NULL,
                    price INT UNSIGNED NOT NULL,
                    quantity INT UNSIGNED NOT NULL,
                    updated DATETIME,
                    created DATETIME,
                    PRIMARY KEY (order_items_id),
                    FOREIGN KEY (item_id) REFERENCES items(item_id),
                    FOREIGN KEY (order_id) REFERENCES orders(order_id)
);
             */
            return orderNumber;
        } catch(SQLException e){
            e.printStackTrace();
        }

    }
}
