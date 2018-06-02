<%
    //session.setMaxInactiveInterval(90);
    //step-1 (read all the cookies coming along with req)
    Cookie ck[]=request.getCookies();
    //step-2 (search the desired one)
    String v1="",v2="";
   
    if(ck!=null){
    for(Cookie c:ck){
        String name=c.getName();
        if(name.equals("uid")){
            v1=c.getValue();
        }else if(name.equals("pwd")){
            v2=c.getValue();
        }
    }
       }
%>

<html>
    <body>
        <h1 align="center">Online Test Platform</h1>
        <pre>
        <div align="center">
        <form action="VerifyUser" method="post">
        <table>
            <tbody>
                <tr>
                    <td>User ID</td>
                    <td><input type="text" name="userid" value="<%=v1%>"/></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password" value="<%=v2%>"/></td>
                </tr>
                <tr>
                    <td>Usertype</td>
                    <td>
                        <select name="utype">
                            <option>Student</option>
                            <option>Professor</option>
                            <option>Admin</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Save Password</td>
                    <td><input type="checkbox" name="save"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Login"/></td>
                    <td><input type="reset"/> </td>
                </tr>
                
            </tbody>
        </table>
        </form>
         </div>
          </pre>
        <hr>
        <h2 align="center"><a href="SignUp.jsp">Sign up</a></h2>
    </body>
</html>
