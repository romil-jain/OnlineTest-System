<%-- 
    Document   : ProfessorHome
    Created on : 10 Sep, 2017, 3:04:13 PM
    Author     : ROMIL
--%>

<%
    String nm=(String)session.getAttribute("username");
    if(nm==null){
        response.sendRedirect("index.jsp");
    }

    long val=session.getCreationTime();
    java.util.Date dt=new java.util.Date(val);
    int n=session.getMaxInactiveInterval();
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Professor Home</title>
    </head>
    <body>
        <h1 align="center">WELCOME <%=nm%></h1>
        <h4>Your Are With Us Since <%=dt%></h4>
        <h4>If you remain idle for <%=n%> sec , your session will expire</h4>
        <h2 align="right"><a href="KillSession">LogOut</a></h2>
        <hr>
        <h2><a href="ResumePage.jsp">Send Resume</a></h2>
        <h2><a href="CheckAdminResponse">Check Response</a></h2>
        
        <hr>

        
        <form action="VerifyProfessor" method="post">
            <h3>Authentication for uploading quiz and results</h3>
        <table>
            <tbody>
                <tr>
                    <td>Professor ID</td>
                    <td><input type="text" name="pid"/></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="pwd"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </tbody>
        </table>
        </form>
        
    </body>
</html>
