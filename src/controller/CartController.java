package src.controller;

import src.model.CartBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CartController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get the action mode of the cart.
            String mode = request.getParameter("mode");
            // Put the order into the cart.
            if (mode.equals("1")) {
                HttpSession session = request.getSession(true);
                CartBean cart = (CartBean) session.getAttribute("cart");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                if (cart == null) {
                    cart = new CartBean();
                    session.setAttribute("cart", cart);
                }
                gotoPage(request, response, "/cart.jsp");
            } else if (mode.equals("2")) {
                HttpSession session = request.getSession(true);
                CartBean cart = (CartBean) session.getAttribute("cart");
                int id = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("message", "Please do it properly.");
                gotoPage(request, response, "/cart.jsp");
            } else if (mode.equals("3")) {
                HttpSession session = request.getSession(true);
                CartBean cart = (CartBean) session.getAttribute("cart");
                int id = Integer.parseInt(request.getParameter("id"));
                cart.deleteCart(id);
                gotoPage(request, response, "/cart.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException{
        RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request, response);
    }
}
