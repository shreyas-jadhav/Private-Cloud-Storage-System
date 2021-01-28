<%-- 
    Document   : fileUpload
    Created on : 23 Jan, 2021, 9:03:25 PM
    Author     : shreyas
--%>
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

        <title>Upload</title>
    </head>
    <body>
        <div class="container">
            <div class="box">
                <h2>Select a file to upload:</h2> 

                <%
                    if (session.getAttribute("upload-status") != null) {
                        if (session.getAttribute("upload-status").equals("success")) {
                %> 
                <p style="color:greenyellow"> Successfully Uploaded! </p> 
                <% session.removeAttribute("upload-status");
                } else {
                %>
                <div class="er">
                    <% out.println(session.getAttribute("upload-status"));
                        session.removeAttribute("upload-status");
                    %>
                </div>
                <%
                        }
                    }
                %>

                <form action = "save" method = "post" enctype = "multipart/form-data">

                    <div class="input-box">
                        <input type = "file"  style="color: wheat" name = "file" size = "50"  />

                    </div>
                    <input type = "submit" class="pure-material-button-contained" style="background-color: #4CAF50" value = "Upload File" />
                    <a href="/Home" class="pure-material-button-contained" style="float: right; text-decoration: none; margin-top: 0px;">
                        Return Home
                    </a>

                </form>

            </div>
        </div>
    </body>
</html>
