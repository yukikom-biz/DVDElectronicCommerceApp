package utils;

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
                response.setContentType("text/html");
                if (auth(name, password)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("uuid", name);
                    //Next Action
                } else {
                    out.print("ユーザー名またはパスワードが一致しません。");
                    //Next Action
                }
                out.close();
            }else if(request.getParameter("action").equals("logout")){
                if(isLogin(request)) {
                    PrintWriter out = response.getWriter();
                    HttpSession session = request.getSession();
                    session.invalidate();
                    out.print("正常にログアウトしました");
                    out.close();
                    //Next Action
                }else{
                    PrintWriter out = response.getWriter();
                    out.print("ログアウトエラーです。Are you sure you have login????");
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
            User user = userDAO.checkLogin(uuid,User.pwHash(password));
            if(user==null){
                return false;
            }else{
                return true;
            }
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return false;
        }
    }
}
