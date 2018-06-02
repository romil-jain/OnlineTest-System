import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VerifyUser extends HttpServlet {

    Connection con; PreparedStatement ps;  ResultSet rs;
    
    @Override
    public void init(){
        try{
            
            
            
            ServletContext context=getServletContext();
            String driver=context.getInitParameter("driver-name");
            String url=context.getInitParameter("connection-url");
            String uid=context.getInitParameter("userid");
            String pwd=context.getInitParameter("password");
            Class.forName(driver);
            con=DriverManager.getConnection(url,uid,pwd);
            //String qr="select uname,acctype from shopusers where userid=? and password=?";
            //ps=con.prepareStatement(qr);
           
        }catch(Exception e){}
    }
    @Override
    public void destroy(){
        try{
        con.close();
        }catch(Exception e){}
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String s1=request.getParameter("userid");
        String s2=request.getParameter("password");
        String s3=request.getParameter("utype");
        //rs=ps.executeQuery();
      if(s1!="" && s2!="")
      {
        if(s3.equals("Student"))
        {
            try
            {
                String qr="select uname,userid from student where userid=? and password=?;";
                ps=con.prepareStatement(qr);
                ps.setString(1,s1);
                ps.setString(2,s2);
               
                rs=ps.executeQuery();
                if(rs.next())
                {
                    String name=rs.getString(1);
                     String id=rs.getString(2);
                     //String mobno=rs.getString(3);
                    HttpSession session=request.getSession();
                    session.setAttribute("username", name);
                    session.setAttribute("userid",id);
                    //session.setAttribute("mobileno",mobno);
                    
                    String ch=request.getParameter("save");
                   if(ch!=null)
                   {
                    //step-1 (create cookie object(s)
                    Cookie c1=new Cookie("uid",s1);
                    Cookie c2=new Cookie("pwd",s2);
                    //step-2 (set the maximum age of the cookie)
                    c1.setMaxAge(60*60*24*7);
                    c2.setMaxAge(60*60*24*7);
                    //step-3 (add the cookie object(s) to response
                    response.addCookie(c1);
                    response.addCookie(c2);
                   }
                   else
                    {
                        Cookie ck[]=request.getCookies();
                    
   
                        if(ck!=null)
                          {
                             for(Cookie c:ck){
                             String nm=c.getName();
                             if(nm.equals("uid")) 
                                {
                                   c.setMaxAge(0);
                                   response.addCookie(c);
                                }
                             else if(nm.equals("pwd"))
                                {
                                   c.setMaxAge(0);
                                   response.addCookie(c);
                                }
                          }
                         }
                    }
                    response.sendRedirect("StudentHome.jsp");   
                }
                else
                {
                    out.println("<h3>Invalid Details</h3>");
                    out.println("<h4><a href=index.jsp>Try Again</a></h4>");
                }
            }
            catch(Exception e){}
        }
        else if(s3.equals("Professor"))
        {
            try
            {
                String qr="select uname,userid,mobileno from professor where userid=? and password=?;";
                
                ps=con.prepareStatement(qr);
                ps.setString(1,s1);
                ps.setString(2,s2);
                rs=ps.executeQuery();
                if(rs.next())
                {
                    String name=rs.getString(1);
                     String id=rs.getString(2);
                     String mobno=rs.getString(3);
                    HttpSession session=request.getSession();
                    session.setAttribute("username", name);
                    session.setAttribute("userid",id);
                    session.setAttribute("mobileno",mobno);
                    
                    String ch=request.getParameter("save");
                     if(ch!=null)
                     {
                    //step-1 (create cookie object(s)
                    Cookie c1=new Cookie("uid",s1);
                    Cookie c2=new Cookie("pwd",s2);
                    //step-2 (set the maximum age of the cookie)
                    c1.setMaxAge(60*60*24*7);
                    c2.setMaxAge(60*60*24*7);
                    //step-3 (add the cookie object(s) to response
                    response.addCookie(c1);
                    response.addCookie(c2);
                     }
                    else
                    {
                        Cookie ck[]=request.getCookies();
                    
   
                        if(ck!=null)
                          {
                             for(Cookie c:ck){
                             String nm=c.getName();
                             if(nm.equals("uid")) 
                                {
                                   c.setMaxAge(0);
                                   response.addCookie(c);
                                }
                             else if(nm.equals("pwd"))
                                {
                                   c.setMaxAge(0);
                                   response.addCookie(c);
                                }
                          }
                         }
                    }
                    
                    response.sendRedirect("ProfessorHome.jsp");   
                }
                else
                {
                    out.println("<h3>Invalid Details</h3>");
                    out.println("<h4><a href=index.jsp>Try Again</a></h4>");
                }
            }
            catch(Exception e){}
        }
        else
        {
             try
            {
                String qr="select uname,userid from admin where userid=? and password=?;";
                
                ps=con.prepareStatement(qr);
                ps.setString(1,s1);
                ps.setString(2,s2);
                rs=ps.executeQuery();
                if(rs.next())
                {
                     String name=rs.getString(1);
                     String id=rs.getString(2);
                     //String mobno=rs.getString(3);
                    HttpSession session=request.getSession();
                    session.setAttribute("username", name);
                    session.setAttribute("userid",id);
                    
                    //session.setAttribute("mobileno",mobno);
                 
                    String ch=request.getParameter("save");
                if(ch!=null){
                    //step-1 (create cookie object(s)
                    Cookie c1=new Cookie("uid",s1);
                    Cookie c2=new Cookie("pwd",s2);
                    //step-2 (set the maximum age of the cookie)
                    c1.setMaxAge(60*60*24*7);
                    c2.setMaxAge(60*60*24*7);
                    //step-3 (add the cookie object(s) to response
                    response.addCookie(c1);
                    response.addCookie(c2);
                           }
                else
                {
                    Cookie ck[]=request.getCookies();
                    
   
                   if(ck!=null){
                      for(Cookie c:ck){
                      String nm=c.getName();
                         if(nm.equals("uid")){
                            c.setMaxAge(0);
                            response.addCookie(c);
                          }
                         else if(nm.equals("pwd")){
                                   c.setMaxAge(0);
                                   response.addCookie(c);
                                               }
                                       }
                                 }
                }
                    
                    response.sendRedirect("AdminHome.jsp");   
                }
                else
                {
                    out.println("<h3>Invalid Details</h3>");
                    out.println("<h4><a href=index.jsp>Try Again</a></h4>");
                }
            }
            catch(Exception e){}
        }
      }
      else
      {
         // response.sendRedirect("index.jsp");
          out.println("<h2>User ID or Password missing</h2>");
         out.println("<h3><a href=index.jsp>Try Again</a></h3>");
      }
        
       
    }
      
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

