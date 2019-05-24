package model;

import java.util.List;

public class OrderItem {
    public static List getList(String keyword) throws common.DAOException {
        CartDAO cartDAO = new CartDAO();
        return cartDAO.findAll(keyword);
    }

    public static List getList(String keyword,int page ) throws common.DAOException {
        CartDAO cartDAO = new CartDAO();
        return cartDAO.findAll(keyword);
    }

    public static CartBean getItem(int id) throws common.DAOException {
        CartDAO cartDAO = new CartDAO();
        return cartDAO.findItem(id);
    }
}


