<%
 String name=(String)session.getAttribute("acc_prof_name");
 String id=(String)session.getAttribute("acc_prof_userid");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Authentication</title>
    </head>
    <body>
        <pre>
        <h1 align="center">Fill Authentication details for -<%=name%></h1>
        <h2><%=id%></h2>
        <form action="RespondProfessor" method="post">
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
                    <td><input type="submit" value="Send"/></td>
                    <td><input type="reset"/> </td>
                </tr>
            </tbody>
        </table>
        </form>
        </pre>
    </body>
</html>
