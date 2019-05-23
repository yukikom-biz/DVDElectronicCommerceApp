package model;

import common.DAOException;

import java.util.List;

public class Item {

    public static List getList(String keyword) throws DAOException {
        ItemDAO itemDAO = new ItemDAO();
        return itemDAO.findAll(keyword);
    }

    public static List getList(String keyword,int page ) throws DAOException {
        ItemDAO itemDAO = new ItemDAO();
        return itemDAO.findAll(keyword,page);

    }

    public static Item getItem(){

    }
}
