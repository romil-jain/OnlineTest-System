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
public class DisplayRequest extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Connection con; Statement stmt;  ResultSet rs;  ResultSetMetaData rsmd;  PreparedStatement ps;
    
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
       // String userid=request.getParameter("uid");
        
        // HttpSession session=request.getSession();
         //session.setAttribute("userid",userid);
        
        /*if(userid!=null)
        {
              try
              {
                String query="delete from sentresume where userid=?;";
            
                
                ps=con.prepareStatement(query);
                ps.setString(1,userid);
                int n=ps.executeUpdate();
                
              }
              catch(Exception e){out.println(e);}
        }*/
        
        try
        {
        String qr="select * from sentresume;";
        stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        rs=stmt.executeQuery(qr);
        rsmd=rs.getMetaData();
        int n=rsmd.getColumnCount();
        
           if(rs.next())
           {
               rs.beforeFirst();
               
               String s1,s2,s3,s4,s5,s6,s7;
                     
                out.println("<table border=2>");
                out.println("<tr>");
                out.println("<td>EmailId</td>");
                out.println("<td>Name</td>");
                out.println("<td>Qualifications</td>");
                out.println("<td>Specializations</td>");
                out.println("<td>Experience</td>");
                out.println("<td>Contact Number</td>");
                out.println("<td>Date</td>");
               out.println("<td></td>");
               out.println("<td></td>");
                out.println("</tr>");
                
                while(rs.next()){
                     s1=rs.getString(1);
                     s2=rs.getString(2);
                     s3=rs.getString(3);
                     s4=rs.getString(4);
                     s5=rs.getString(5);
                     s6=rs.getString(6);
                     s7=rs.getString(7);
                    
                    //sum=sum+Integer.parseInt(s5);
                    out.println("<tr>");
                    out.println("<td>"+s1+"</td>");
                    out.println("<td>"+s2+"</td>");
                    out.println("<td>"+s3+"</td>");
                    out.println("<td>"+s4+"</td>");
                    out.println("<td>"+s5+"</td>");
                    out.println("<td>"+s6+"</td>");
                    out.println("<td>"+s7+"</td>");
                    
                    
                    out.println("<td><a href=DeclineRequest?uid="+s1+">Remove Request</a></td>");
                    out.println("<td><a href=AcceptRequest?uid="+s1+"&uname="+s2+">Accept</a></td>");
                    //out.println("<td><a href=RemoveItems?c1="+s1+">[X]</a></td>");
                    //out.println("<td align=center><input type=checkbox name=c1 value="+s1+"></td>");
                    out.println("</tr>");
                }
                
                out.println("</table>");
                /*
                if(userid!=null)
                {
                   out.println("<h3>"+"Request from "+userid+" has been removed"+"</h3>");
                }
                */
                out.println("<h4><a href=AdminHome.jsp>Home</a></h4>");
               }
                   
           
           
           else
           {
               out.println("<h2>No professor requests</h2>");
               out.println("<h3><a href=AdminHome.jsp>Home</a></h3>");
           }
        }
        catch(Exception e){}
           
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
