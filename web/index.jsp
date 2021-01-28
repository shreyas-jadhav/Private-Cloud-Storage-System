<%-- 
    Document   : index
    Created on : 25 Jan, 2021, 10:05:21 AM
    Author     : shreyas
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <title>Login Page</title>
    </head>
    <body>
        <div class="container">
            <div class="box">
                <h2>Login Account</h2>
                <form action="validation" method="post">
                    <div class="input-box">
                        <input type="text" name="email"  autocomplete="off" required>
                        <label> E-Mail </label>
                    </div>
                    <div class="input-box">
                        <input type="password" name="password" class="effect-17" required>
                        <label> Password </label>
                    </div>
                    
                    <div class="er">
                    <%

                        if (session.getAttribute("status") != null) {
                            if (session.getAttribute("status").toString().equals("false")) {
                                out.println("Given E-mail and Password didn't matched any records! Please Enter correct credentials or Register for a new Account.");
                                session.removeAttribute("status");
                            }
                        }

                    %>
                    
                    </div>
                    <input type="submit" style="background-color: #4CAF50" value="Login" class="pure-material-button-contained">


                    <a href="/Register" class="pure-material-button-contained" style="text-decoration: none;">Create New Account</a>
                </form>
            </div>
        </div>

    </body>
</html>

