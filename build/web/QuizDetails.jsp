
<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>

<%
 
           
String profid=(String)session.getAttribute("pid");
        
    
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz Details</title>
    </head>
    <body>
        <h3 align="center">Fill Quiz Details</h3>
        <form action="StoreQuizDetails" method="get">
       <pre>
            <table>
            <tbody>
                 
                  <tr>
                    <td>Professor Id</td>
                    <td><input type="text" name="profid" value="<%=profid%>" readonly="readonly"/></td>
                </tr>
                 
                 
                <tr>
                    <td>Quiz code</td>
                    <td><input type="text" name="qcode"/></td>
                </tr>
                <tr>
                    <td>Quiz Name</td>
                    <td><input type="text" name="qname"/></td>
                </tr>
                <tr>
                    <td>no. of questions</td>
                    <td><input type="number" name="noq"/></td>
                </tr>
                <tr>
                    <td>Marks for correct answer</td>
                    <td><input type="number" name="pmarks"/></td>
                    
                </tr>
                <tr>
                    <td>Marks for wrong answer</td>
                    <td><input type="number" name="nmarks"/></td>
                    
                </tr>
                <tr>
                    <td>Start date</td>
                    
                       <td><input type="date" name="sdate"/></td>
                    
                </tr>
                <tr>
                    <td>End date</td>
                    
                       <td><input type="date" name="edate"/></td>
                    
                </tr>
                <tr>
                    <td>Time duration(In Hours)</td>
                    <td><input type="number" name="dur"/></td>
                    
                </tr>
                 
                
                <tr>
                 <td>
                <input type="submit" value="Ok"/>    
                </td>
                </tr>
            </tbody>
        </table>
        </pre>
        </form>
    </body>
</html>
