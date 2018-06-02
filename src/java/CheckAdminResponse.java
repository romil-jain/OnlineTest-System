/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ROMIL
 */
public class CheckAdminResponse extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Connection con; Statement stmt;  ResultSetMetaData rsmd;  PreparedStatement ps;
    
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
                 
                 try
                 {
                      HttpSession session=request.getSession();
                     String uid=(String)session.getAttribute("userid");
                     String uname=(String)session.getAttribute("username");
                     
                     String qr1="select * from permittedprofessors where userid=?;";
                     PreparedStatement ps1=con.prepareStatement(qr1);
                     ps1.setString(1,uid);
                     ResultSet rs1=ps1.executeQuery();
                     
                     
                     
                     if(rs1.next())
                     {
                         String s1=rs1.getString(1);
                         String s2=rs1.getString(2);
                         String s3=rs1.getString(3);
                         out.println("<h2>Congratulations..."+uname+"</h2>");
                         out.println("<h3>Your request has been accepted</h3>");
                         out.println("<h3>Following are your authentication details</h3>");
                         out.println("<table border=\"1\">");
                         out.println("<tr>");
                         out.println("<td>");
                         out.println("ID");
                         out.println("</td>");
                         out.println("<td>");
                         out.println(s2);
                         out.println("</td>");
                         out.println("</tr>");

                         out.println("<tr>");
                         out.println("<td>");
                         
                         out.println("Password");
                         out.println("</td>");
                         out.println("<td>");
                         out.println(s3);
                         out.println("</td>");
                         
                         out.println("</tr>");
                         out.println("</table>");
                         out.println("<h3><a href=ProfessorHome.jsp>Home</a></h3>");
                     }
                     else
                     {
                            String qr2="select * from declinedrequests where userid=?;";
                            PreparedStatement ps2=con.prepareStatement(qr2);
                            ps2.setString(1,uid);
                            ResultSet rs2=ps2.executeQuery();
                            
                            if(rs2.next())
                            {
                               out.println("<h2>Your request has been declined</h2>");
                               out.println("<h3><a href=ProfessorHome.jsp>Home</a></h3>");
                            }
                            else
                            {
                                out.println("<h2>No response yet !</h2>");
                                out.println("<h2>Your request is in the queue...</h2>");
                                out.println("<h3><a href=ProfessorHome.jsp>Home</a></h3>");
                            }
                     }
                 }
                 catch(Exception e)
                 {out.println(e);}
                 
            
        
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
