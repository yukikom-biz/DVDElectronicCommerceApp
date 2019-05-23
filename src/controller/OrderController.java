package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import model.OrderDAO;
import model.User;
import model.UserDAO;
import utils.login;

@WebServlet("/OrderServlet")
public class OrderController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if(!utils.login.isLogin(request)){
            gotoPage(request,response,"/login.jsp");
        }else{
            HttpSession session = request.getSession();
            CartBean cart = (CartBean) session.getAttribute("cart");
            String action = request.getParameter("action");
            if(action == null || action.length() == 0){
                gotoPage(request,response,"/cart.jsp");
            }else if(action.equals("1")){
                //Show Order Complete
                if(cart==null){
                    gotoPage(request,response,"/cart.jsp");
                }else{
                    //Set Customer Info
                    UserDAO userDAO = new UserDAO();
                    try {
                        User customer = userDAO.getUserByID((String)session.getAttribute("uuid"));
                        //Need further amendment
                        customer.setAddress(request.getParameter("address"));
                        customer.setPhone(request.getParameter("phone"));
                        customer.setPostal(request.getParameter("postal"));
                        session.setAttribute("customer",customer);
                        OrderDAO order = new OrderDAO();
                        int orderNumber = order.saveOrder(customer,cart);
                        session.removeAttribute("cart");
                        session.removeAttribute("customer");
                        request.setAttribute("orderNumber", orderNumber);
                        gotoPage(request,response,"/order.jsp");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void gotoPage(HttpServletRequest request,HttpServletResponse response, String page) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
