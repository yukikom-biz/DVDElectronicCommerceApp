package utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.SQLException;

import model.User;
import model.UserDAO;

@WebServlet("/login")
public class login extends HttpServlet {
    public static boolean isLogin(HttpServletRequest request){
        HttpSession session=request.getSession(false);
        if(session!=null){
            return true;
        }else{
            return false;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        try {
            PrintWriter out = response.getWriter();
            out.println("You should send it via POST");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        try {
            if(request.getParameter("action").equals("login")) {
                PrintWriter out = response.getWriter();
                String name = request.getParameter("name");
                String password = request.getParameter("password");
                response.setContentType("text/html;charset=UTF-8");
                if (auth(name, password)) {
                    System.out.println("Auth successfully");
                    response.setContentType("text/html;charset=UTF-8");
                    HttpSession session = request.getSession();
                    session.setAttribute("uuid", name);
                    RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                    rd.forward(request,response);
                    out.close();
                    //Next Action
                } else {
                    out.print("Wrong Password");
                    out.close();
                    //Next Action
                }
            }else if(request.getParameter("action").equals("logout")){
                response.setContentType("text/html;charset=UTF-8");
                if(isLogin(request)) {
                    PrintWriter out = response.getWriter();
                    HttpSession session = request.getSession();
                    session.invalidate();
                    out.print("Logout successfully!");
                    out.close();
                    //Next Action
                }else{
                    PrintWriter out = response.getWriter();
                    out.print("Are you sure you have login????");
                    //Next Action
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }



    private boolean auth(String uuid, String password){
        try{
            UserDAO userDAO = new UserDAO();
            User user = userDAO.checkLogin(uuid,password);
            if(user==null){
                System.out.println("False");
                return false;
            }else{
                System.out.println("True");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
