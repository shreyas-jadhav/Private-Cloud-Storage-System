
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <title>Register Account</title>
    </head>
    <body>
        <div class="container">
            <div class="box">
                
                <h2>Register Account</h2>
                <h3 style="color: white">Private Cloud Storage</h3>
                <p style="color: white"> Store Upto 10 GB ! </p><br>
                <form action="validater" method="post">

                    <div class="input-box">
                        <input type="text"  name="name" class="effect-17" required>     
                        <label>Name</label>
                    </div>
                    <div class="er">
                    <%
                        if (session.getAttribute("rstatus") != null) {
                          
                                out.println(session.getAttribute("rstatus").toString());
                                session.removeAttribute("rstatus");
                            
                        }
                    %>
                    </div>
                    <div class="input-box" style="">
                        <input type="text"  name="email" class="effect-17" required>
                        <label>Email</label>
                    </div>

                    <div class="input-box">
                        <input type="password" name="password" class="effect-17" required>
                        <label>Password</label>
                    </div>


                    <input type="submit" style="background-color: #4CAF50" class="pure-material-button-contained" value="Register">
                    <a href="/Login" class="pure-material-button-contained" style="text-decoration: none;">Already have Account?</a>
                </form>
            </div>
        </div>
    </body>
</html>
