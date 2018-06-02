<%-- 
    Document   : ResumePage
    Created on : 10 Sep, 2017, 4:06:08 PM
    Author     : ROMIL
--%>
<%
    String nm=(String)session.getAttribute("username");
    String uid=(String)session.getAttribute("userid");
    String mno=(String)session.getAttribute("mobileno");
    if(nm==null){
        response.sendRedirect("index.jsp");
    }
    /*
    
    
    
    */
    //long val=session.getCreationTime();
    //java.util.Date dt=new java.util.Date(val);
    //int n=session.getMaxInactiveInterval();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resume</title>
    </head>
    <body>
        <h3>Resume</h3>
        <form action="SendResume" method="get">
            <pre>
                Name                           <input type="text" name="name" value="<%=nm%>"/>
               
                Email Id                       <input type="text" name="uid" value="<%=uid%>" readonly="readonly"/>

                Qualifications                 <input type="text" name="qual"/>
                    
                Specialization(Subjects)       <input type="text" name="spec"/>
                    
                Experience                     <input type="text" name="exp" />
                
                Contact Number                 <input type="text" name="mno" value="<%=mno%>"/>
                    
                <input type="submit" value="Send Details"/>    
                    
                <a href="ProfessorHome.jsp">Home</a>
                
            <p>*All fields are mandatory</p>
            </pre>
        </form>
    </body>
</html>
