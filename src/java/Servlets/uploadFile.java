package Servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Helpers.User;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;

/**
 *
 * @author shreyas
 */
public class uploadFile extends HttpServlet {

    private boolean isMultipart;
    static public String filePath;
    static public int maxFileSize = 1024 * 1024 * 1024;
    static public int maxMemSize = 3024 * 1024 * 1024;

    public void init() {
        // Get the file location where it would be stored.
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();

        if (!isMultipart) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>No file uploaded</p>");
            out.println("</body>");
            out.println("</html>");
            return;
        }

        HttpSession session = request.getSession();

        if (session.getAttribute("user") == null) {
            response.sendRedirect("/Login");
        } else {
            User u = (User) session.getAttribute("user");
            String result = u.handleFile(request);
            session.setAttribute("upload-status", result);
            response.sendRedirect("/Upload");
        }
    }

}
