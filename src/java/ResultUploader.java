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
public class ResultUploader extends HttpServlet {

    
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
       
        try
        {
            HttpSession session=request.getSession();
            String pid=(String)session.getAttribute("pid");
            String qr="select qcode,qname from quizinfo where pid=? and completed is null and curdate()>edate;";
           
             
            
            PreparedStatement ps=con.prepareStatement(qr,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            //PreparedStatement ps=con.prepareStatement(qr);
            ps.setString(1,pid);
           // ps.setString(2,"yes");
            rs=ps.executeQuery();
            //rs=st.executeQuery(qr);
            
          // out.println("Hyeeeee");
            
            if(rs.next()==false)
            {
                out.println("<h2>There is no quiz whose result has to be uploaded</h2>");
                out.println("<h3><a href=\"ProfessorSection.jsp\">Home</a></h3>");
            }
            else
            {
               rs.beforeFirst();
               
            out.println("<table border=1>");
            out.println("<tr>");
            
            out.println("<td>");
            out.println("Quiz Code");
            out.println("</td>");
            
            out.println("<td>");
            out.println("Quiz Name");
            out.println("</td>");
            
            out.println("</tr>");
            
            while(rs.next())
            {
            out.println("<tr>");
                
            out.println("<td>");
            out.println(rs.getString(1));
            out.println("</td>");
            
            out.println("<td>");
             out.println("<a href=\"RankCalculator?qcode="+rs.getString(1)+"\">"+rs.getString(2)+"</a>");
            out.println("</td>");
            
            out.println("</tr>"); 
            }
            out.println("</table>");
            out.println("<h2><a href=\"ProfessorSection.jsp\">Home</a></h2>");
            }
        }
        catch(Exception e)
        {}
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
