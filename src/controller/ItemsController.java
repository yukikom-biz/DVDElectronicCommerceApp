package controller;

import common.DAOException;
import model.Item;
import model.ItemBean;
import model.ItemDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static model.Item.getAllItem;
import static model.Item.getList;

@WebServlet("/ItemsController")
public class ItemsController extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//
//
//    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        int page = Integer.parseInt(request.getParameter("page"));
        Item item = new Item();
        List itemList = null;

        if (keyword == null){
            try {
                itemList = getAllItem();
            } catch (DAOException e) {
                e.printStackTrace();
            }
        }else if (page == 0){
            try {
                itemList = getList(keyword);
            } catch (DAOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                itemList = getList(keyword, page);
            } catch (DAOException e) {
                e.printStackTrace();
            }
        }
        
        request.setAttribute("items" , itemList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/list.jsp");
        requestDispatcher.forward(request,response);
    }

}

