<html>
    <head>
        <title>
        SignUp    
        </title>
    </head>
    <body>
        <div align="center">
        <h3>SignUp</h3>
        <form action="SaveUser" method="post">
        <table>
            <tbody>
                <tr>
                    <td>UserName</td>
                    <td><input type="text" name="uname"/></td>
                </tr>
                <tr>
                    <td>Account Type</td>
                    <td>
                       <table> 
                           <tbody>
                             <tr><td><input type="radio" name="r1" value="Student" checked="checked"/>Student</td></tr>
                             <tr><td><input type="radio" name="r1" value="Professor"/>Professor </td></tr>
                           </tbody>
                       </table>
                    </td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td><input type="text" name="adr" /></td>
                </tr>
                <tr>
                    <td>Mobile Number</td>
                    <td><input type="text" name="mno"/></td>
                </tr>
                <tr>
                    <td>Email Id</td>
                    <td><input type="text" name="uid"/></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="pwd"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Register"/></td>
                </tr>
                
            </tbody>
        </table>
            <p>*All fields are mandatory</p>
        </form>
        <hr>
        <a href="index.jsp">Home</a>
        </div>
    </body>
</html>
