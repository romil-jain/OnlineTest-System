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


public class StoreQuizDetails extends HttpServlet {

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
        PrintWriter out=response.getWriter();
        
         String s1=request.getParameter("profid");
        String s2=request.getParameter("qcode");
        String s3=request.getParameter("noq");
        String s4=request.getParameter("sdate");
        String s5=request.getParameter("edate");
        String s6=request.getParameter("dur");
        String s7=request.getParameter("pmarks");
        String s8=request.getParameter("nmarks");
        String s9=request.getParameter("qname");
        
        try
        {
        String qr="select qcode from quizinfo where qcode=?;";            
        ps=con.prepareStatement(qr);
        ps.setString(1,s2);
        ResultSet rs=ps.executeQuery();
        
          //out.println("helllooo");
        
        if(rs.next())
        {
            out.println("This quiz code already exists....Try different one");
            out.println("<h3><a href=QuizDetails.jsp>Try again</a></h3>");
            //response.sendRedirect("QuizDetails.jsp");
        }
        else
        {
         qr="insert into quizinfo(pid,qcode,noq,sdate,edate,duration,pmarks,nmarks,qname) values(?,?,?,?,?,?,?,?,?);";
         
        ps=con.prepareStatement(qr);
        ps.setString(1,s1);
        ps.setString(2,s2);
        ps.setInt(3,Integer.parseInt(s3));
        ps.setString(4,s4);
        ps.setString(5,s5);
        ps.setInt(6,Integer.parseInt(s6));
        ps.setInt(7,Integer.parseInt(s7));
        ps.setInt(8,Integer.parseInt(s8));
        ps.setString(9,s9);
        
        
        int n=ps.executeUpdate();
        
     
        
        qr="create table "+s2+"(qno int,Question varchar(200),op1 varchar(200),op2 varchar(200),op3 varchar(200),op4 varchar(200),answer int);";
        Statement st=con.createStatement();
        n=st.executeUpdate(qr); 
        
        HttpSession session=request.getSession();
        session.setAttribute("quizcode",s2);
        int c=0;
        session.setAttribute("ques_count",0);
        //out.println("helllooo");
       response.sendRedirect("FeedQuestions.jsp");
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
