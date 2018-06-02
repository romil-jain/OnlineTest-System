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
public class QuizFinder extends HttpServlet {

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
        String qr="Select qcode,noq,sdate,edate,duration,pmarks,nmarks,qname from quizinfo where sdate<curdate() and edate>curdate();";
        //PreparedStatement ps=con.prepareStatement(qr);
        PreparedStatement ps=con.prepareStatement(qr,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            out.println("<p>Click on the QUIZ NAME to start attempt</p>");
            out.println("<b>NOTE</b><p>Links to unattempted quizes are provided only</p>");
        rs.beforeFirst();
        
        String qcode,qname,sdate,edate,noq,duration,pmarks,nmarks;
       
        
            out.println("<table border=1>");
            
            out.println("<tr>");
           
           out.println("<td>");
           out.println("Quiz Code");
           out.println("</td>");
           
            out.println("<td>");
           out.println("Number of Questions");
           out.println("</td>");
           
           out.println("<td>");
           out.println("Starting on");
           out.println("</td>");
           
           out.println("<td>");
           out.println("Ending On");
           out.println("</td>");
           
             out.println("<td>");
           out.println("Test Duration(In Hours)");
           out.println("</td>");
           
             out.println("<td>");
           out.println("Positive Marks");
           out.println("</td>");
           
             out.println("<td>");
           out.println("Negative Marks");
           out.println("</td>");
           
            out.println("<td>");
           out.println("Quiz Name");
           out.println("</td>");
           
           out.println("</tr>");
            
        while(rs.next())
        {
           qcode=rs.getString(1);
           noq=rs.getString(2);
           sdate=rs.getString(3);
           edate=rs.getString(4);
           duration=rs.getString(5);
           pmarks=rs.getString(6);
           nmarks=rs.getString(7);
           qname=rs.getString(8);
           
           
           out.println("<tr>");
           
           out.println("<td>");
           out.println(qcode);
           out.println("</td>");
           
            out.println("<td>");
           out.println(noq);
           out.println("</td>");
           
           out.println("<td>");
           out.println(sdate);
           out.println("</td>");
           
           out.println("<td>");
           out.println(edate);
           out.println("</td>");
           
             out.println("<td>");
           out.println(duration);
           out.println("</td>");
           
             out.println("<td>");
           out.println(pmarks);
           out.println("</td>");
           
             out.println("<td>");
           out.println(nmarks);
           out.println("</td>");
           
           
          String qr1="select * from squizdetails where sid=? and quizcode=?;";
          // PreparedStatement ps1=con.prepareStatement(qr1,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            HttpSession session=request.getSession();
            PreparedStatement ps1=con.prepareStatement(qr1);
            ps1.setString(1,(String)session.getAttribute("userid"));
            ps1.setString(2,qcode);
            ResultSet rs1=ps1.executeQuery();
          
           if(rs1.next())
           {
           out.println("<td>");
           out.println(qname);
           out.println("</td>");
           }
           else
           {
            out.println("<td>");
           out.println("<a href='AttemptQuiz?qcode="+qcode+"'>"+qname+"</a>");
           out.println("</td>");
           }
           out.println("</tr>");
          
        }
             out.println("</table>");
        }
        else
        {
            out.println("<h2>There are no ongoing quizes</h2>");
            out.println("<h3><a href=\"StudentHome.jsp\">Home</a></h3>");
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
