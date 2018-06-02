<%-- 
    Document   : ProfessorSection
    Created on : 13 Sep, 2017, 8:32:55 AM
    Author     : ROMIL
--%>

<%
    String nm=(String)session.getAttribute("username");
    if(nm==null){
        response.sendRedirect("index.jsp");
    }

%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Professor Section</title>
    </head>
    <body>
        <h1 align="center">WELCOME <%=nm%></h1>
        <h2 align="right"><a href="KillSession">LogOut</a></h2>
        <hr>
        <pre>
        <h2><a href="QuizDetails.jsp">Upload quiz</a></h2>
       
        <h2><a href="ResultUploader">Upload Result</a></h2>
        
        <h2><a href="FeedbackViewer">View Feedback</a></h2>
        
        
       </pre>
        <hr>
    </body>
</html>
