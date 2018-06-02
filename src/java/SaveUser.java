/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ROMIL
 */
public class SaveUser extends HttpServlet {
    Connection con;
    PreparedStatement ps;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
            //String qr="select uname from shopusers where userid=? and password=?";
            //ps=con.prepareStatement(qr);            
        }
       catch(Exception e)
        {
           
        }
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
        PrintWriter out=response.getWriter();
        String s1=request.getParameter("uname");
        String s2=request.getParameter("r1");
        String s3=request.getParameter("adr");
        String s4=request.getParameter("mno");
        String s5=request.getParameter("uid");
        String s6=request.getParameter("pwd");
        
        
       /* out.println(s1);
        out.println(s2);
        out.println(s3);
        out.println(s4);
        out.println(s5);
        out.println(s6);
        */
        
        
        if(s1!="" && s3!="" && s4!="" && s5!="" && s6!="")
        {
            if(s2.equals("Student"))
            {
             try
               {
                   String check="select * from Student where userid=?;";
                   ps=con.prepareStatement(check);
                   ps.setString(1,s5);
                   ResultSet rs=ps.executeQuery();
                   if(rs.next()==false)
                   {    
                       
                 String qr="insert into student values(?,?,?,?,?);";
                 ps=con.prepareStatement(qr);
                     
                 ps.setString(1, s5); ps.setString(2,s6);
                 ps.setString(3, s1); ps.setString(4,s3);
                 ps.setString(5, s4);
                     
                 int n=ps.executeUpdate();
                 
                    out.println("<h3>Registration Completed</h3>");
                    out.println("<h4><a href=index.jsp>Login-Now</a></h4>");
                   }
                   else
                   {
                       out.println("<h3>This email-id already eists.</h3>");
                       out.println("<h4>Try using a different email-id.</h4>");
                       out.println("<h4><a href=SignUp.jsp>Try Again</a></h4>");
                   }
                }
             catch(Exception e){
                //Class c=e.getClass();
                 //out.println(c);
                out.println(e);
             }
            }
            else
            {
               try
                {
                 String qr="insert into professor values(?,?,?,?,?);";
                 ps=con.prepareStatement(qr);
                 ps.setString(1, s5); ps.setString(2,s6);
                 ps.setString(3, s1); ps.setString(4,s3);
                 ps.setString(5, s4);
                 int n=ps.executeUpdate();
                 
                  out.println("<h3>Registration Completed</h3>");
                    out.println("<h4><a href=index.jsp>Login-Now</a></h4>");
                }
               catch(Exception e){}
            }
                   
        }
        else 
        {
           out.println("<h3>All fields are mandatory</h3>");
           out.println("<h4><a href=SignUp.jsp>Try Again</a></h4>");
        }
        
        
        /*response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
             TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SaveUser</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SaveUser at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
