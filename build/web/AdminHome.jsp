<%-- 
    Document   : AdminHome
    Created on : 10 Sep, 2017, 3:04:40 PM
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
        <title>Admin Home</title>
    </head>
    <body>
     
        <h1 align="center">Admin Home</h1>
        <h2 align="right"><a href="KillSession">LogOut</a></h2>
        <hr>
        <h2>WELCOME <%=nm%></h2>
        <h4>Your Are With Us Since <%=dt%></h4>
        <h4>If you remain idle for <%=n%> sec , your session will expire</h4>
        <hr>
        <h2><a href="DisplayRequest">See Professor Requests</a></h2>
        
        <h2><a href="QuizDisplay">See current quizes</a></h2>
        <h2><a href="ProfessorList">See the professors list</a></h2>
        
        
     
    </body>
</html>
