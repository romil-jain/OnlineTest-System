
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%
  try 
  {
            ServletContext context=getServletContext();
            String driver=context.getInitParameter("driver-name");
            String url=context.getInitParameter("connection-url");
            String uid=context.getInitParameter("userid");
            String pwd=context.getInitParameter("password");
            Class.forName(driver);
            Connection con=DriverManager.getConnection(url,uid,pwd);
  }
  catch(Exception e)
  {}

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Feed Questions</title>
       
    </head>
    <body>
        <h2 align="center">Upload Questions</h2>
        <form action="FeedQuestions" method="post">
           <div><textarea rows="8" cols="100" name="q">Enter the question here</textarea></div>
           <div>
            <div><textarea rows="5" cols="50" name="op1">Option 1</textarea></div>
            <div><textarea rows="5" cols="50" name="op2">Option 2</textarea></div>
            <div><textarea rows="5" cols="50" name="op3">Option 3</textarea></div>
            <div><textarea rows="5" cols="50" name="op4">Option 4</textarea></div>
            <div>
                <div><p>Enter the correct option</p><input type="number" name="ans"/></div>
                <input type="submit" value="Add more Questions">
            </div>
            </div>
          </form>
        <a href="ProfessorSection.jsp">Finish</a>
    </body>
</html>
