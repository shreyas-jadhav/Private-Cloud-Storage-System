<%-- 
    Document   : fileUpload
    Created on : 23 Jan, 2021, 9:03:25 PM
    Author     : shreyas
--%>
<%@page import="Helpers.User"%>
<%
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">

        <title>Rename</title>
    </head>
    <body>
        <div class="container">
            <div class="box">
                <h2>Select New Name:</h2> 



                <form action = "R" method = "post" >

                    <div class="input-box">
                        <input type = "text" name="nname" />
                        <label> New Name </label>
                    </div>
                    <input type="hidden" name="file" value="
                           <%
                               if (request.getParameter("file") != null && session.getAttribute("user") != null) {
                                   out.print(request.getParameter("file"));
                               } else {
                                   response.sendRedirect("/Register");
                               }

                           %>
                           ">
                    <p style="color: white">For file named: <strong>
                            <% out.println(request.getParameter("file"));

                            %>
                        </strong>
                        <br><br>

                        <input type = "submit" class="pure-material-button-contained" value = "Rename" />
                </form>
            </div>
        </div>
    </body>
</html>
