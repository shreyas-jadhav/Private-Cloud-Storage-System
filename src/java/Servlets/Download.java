/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Helpers.User;
import java.io.FileInputStream;
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
public class Download extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String file = request.getParameter("file");

        HttpSession session = request.getSession(false);
        System.out.println("succes");
        if (session != null) {
            if (session.getAttribute("user") != null) {
                User u = (User) session.getAttribute("user");
                System.out.println(u.getFileLocation(file));
                response.setContentType("APPLICATION/OCTET-STREAM");
                response.setHeader("Content-Disposition", "attachment; filename=" + file);

                FileInputStream fileInputStream = new FileInputStream(u.getFileLocation(file));

                int i;
                while ((i = fileInputStream.read()) != -1) {
                    out.write(i);
                }
                fileInputStream.close();
                out.close();

                response.sendRedirect("/Home");
            }
        } else {
            response.sendRedirect("/Register");
        }
    }
}
