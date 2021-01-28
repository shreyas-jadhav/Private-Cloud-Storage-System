package Servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Helpers.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author saim
 */
public class Login extends HttpServlet {

    static Connection con = null;

    public static Connection getConnection() {
        if (con != null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:33010/cloudDB?zeroDateTimeBehavior=convertToNull", "shreyas", "shreyas4");
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return con;
    }

    public void init() throws ServletException {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:33010/cloudDB?zeroDateTimeBehavior=convertToNull", "shreyas", "shreyas4");
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User u = new User();

        boolean status = false;
        try {
            status = u.validate(request.getParameter("email"), request.getParameter("password"));
        } catch (SQLException ex) {
            status = false;
        }
        HttpSession session = request.getSession();
        if (status) {
            session.setAttribute("user", u);
            response.sendRedirect("/Home");
        } else {

            session.setAttribute("status", "false");
            response.sendRedirect("/Login");

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
