package src.controller;
import java.util.List;

public class OrderItem {
    public static List getList(String keyword) throws common.DAOException {
        OrderDAO orderDAO = new OrderDAO();
        return orderDAO.findAll(keyword);
    }

    public static List getList(String keyword,int page ) throws common.DAOException {
        OrderDAO orderDAO = new OrderDAO();
        return orderDAO.findAll(keyword);
    }

    public static CartBean getItem(int id) throws common.DAOException {
        OrderDAO orderDAO = new OrderDAO();
        return orderDAO.findItem(id);
    }
}
