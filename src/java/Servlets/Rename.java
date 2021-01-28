/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Helpers.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author shreyas
 */
public class Rename extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nname = request.getParameter("nname");
        String file = request.getParameter("file");
        
        HttpSession session = request.getSession(false);

        if (session != null) {
            if (session.getAttribute("user") != null) {
                
                User u = (User) session.getAttribute("user");
                
                u.rename(file, nname);
                response.sendRedirect("/Home");
            }
        } else {
            response.sendRedirect("/Register");
        }
    }

}
