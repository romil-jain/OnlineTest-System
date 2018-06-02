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
public class FeedQuestions extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Connection con;
    
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
        String s1=request.getParameter("q");
        String s2=request.getParameter("op1");
        String s3=request.getParameter("op2");
        String s4=request.getParameter("op3");
        String s5=request.getParameter("op4");
        String s6=request.getParameter("ans");
        
        HttpSession session=request.getSession();
        
        
        String s7=(String)session.getAttribute("quizcode");
        int n=(Integer)session.getAttribute("ques_count");
        //int n=Integer.parseInt(s8);
        n=n+1;
      
        
        session.setAttribute("ques_count",n);
        
        
      
        
        
        String qr="insert into "+s7+"(qno,question,op1,op2,op3,op4,answer) values(?,?,?,?,?,?,?);";
        PreparedStatement ps=con.prepareStatement(qr);
        ps.setInt(1,n);
        ps.setString(2,s1);
        ps.setString(3,s2);
        ps.setString(4,s3);
        ps.setString(5,s4);
        ps.setString(6,s5);
        ps.setInt(7,Integer.parseInt(s6));
        
        n=ps.executeUpdate();
        //out.println(Integer.parseInt(s2));
        //out.println(s1);
        
       
               // out.println("<form action='StoreQuestion' method='post'>");
               // out.println("<table>");
                //out.println("<tbody>");
                //out.println("<tr>");
                //out.println("<tr>");
                
                //out.println("<div><textarea rows=\"10\" cols=\"50\" value=\"Enter the question here\"></textarea></div>"); 
                //out.println("");
             
        response.sendRedirect("FeedQuestions.jsp");
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
