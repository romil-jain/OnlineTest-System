<%-- 
    Document   : StudentHome
    Created on : 10 Sep, 2017, 3:03:48 PM
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
        <title>Student Home</title>
    </head>
    <body>
        <h2 align="center">WELCOME <%=nm%></h2>
        <h3 align="right"><a href="KillSession">LogOut</a></h3>
        <hr>
        <h5>Your Are With Us Since <%=dt%></h5>
        <h5>If you remain idle for <%=n%> sec , your session will expire</h5>
        <hr>
        
        <h2><a href="QuizFinder">Search Quizes</a></h2>
        <h2><a href="ResultViewer">View Results</a></h2>
        
    </body>
</html>
