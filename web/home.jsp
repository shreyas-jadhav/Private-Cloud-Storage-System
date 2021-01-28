<%-- 
    Document   : home
    Created on : 24 Jan, 2021, 9:08:50 PM
    Author     : shreyas
--%>
<%
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<%@page import="Helpers.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="style.css">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Home</title>
    </head>
    <body style="background-image: url()">

        <div class="heading">
            <ul><li>            
                    <a href="/Home" style="float: left; padding-bottom: 0px;">
                        <h1> Home</h1> </a>
                </li>
                <li>
                    <div class="" style="float: right;">
                        <a href="/Upload" class="pure-material-button-contained" style="float: right; text-decoration: none; margin: 10px;">
                            Upload File
                        </a>
                        <a href="/Logout" class="pure-material-button-contained" style="float: right; text-decoration: none; margin: 10px; ">
                            Logout
                        </a>
                    </div>
                </li>
            </ul>

            <%  if (session.getAttribute("user") != null) {
                    User u = (User) session.getAttribute("user");
                    long space = u.getSpace();
                    space = 10 - space;

            %>
            <div class="disk">
                &gt; Approximately <strong> <%=space%> GB </strong>Available
                
            </div>


        </div>



        <div class="content">    
            <%            int i = 1;
                if (u.getContent("") != null) {
                    String[] FileList = u.getContent("");
                    for (String item : FileList) {%>
            <div class="item"> 
                <div class="name"><strong><%=item%></strong></div>

                <span style="float: right"><%=u.getSize(item)%> </span>

                <div class="options">

                    <form action="DownloadFile" method="POST">
                        <input type="hidden" name="file" value="<%=item%>">
                        <input type="submit" class="but" style="text-decoration: none; margin: 10px;"
                               value="Download">      
                    </form>



                    <form action="RenameFile" method="POST">
                        <input type="hidden" name="file" value="<%=item%>">

                        <input type="submit" class="but" style="text-decoration: none; margin: 10px;"
                               value="Rename">      

                    </form>



                    <form action="Delete" method="POST">
                        <input type="hidden" name="file" value="<%=item%>">
                        <input type="submit" class="but" style="text-decoration: none; margin: 10px;"
                               value="Delete">      

                    </form>


                </div>

            </div>

            <%      i++;
                }
            } else {
            %>
            <div class="Empty"><h2>Currently there are no Files Uploaded!</h2> 

                <a href="/Upload" class="fu" style="text-decoration: none; margin: 10px; ">
                    Start Uploading
                </a>

            </div>

            <%
                    }
                } else {
                    response.sendRedirect("/Register");
                }

            %>

        </div>



    </body>
</html>
