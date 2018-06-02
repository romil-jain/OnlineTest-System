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
public class ResultCalculator extends HttpServlet {

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
        int correct=0,wrong=0;
        HttpSession session=request.getSession();
        
        String qcode=(String)session.getAttribute("quizcode");
        String qr="select qno,answer from "+qcode+";";
        Statement st=con.createStatement();
        rs=st.executeQuery(qr);
        
        
        
        while(rs.next())
        {
        
            //out.println("hello world");
            
            String s="r"+rs.getString(1);
           
            String ans=request.getParameter(s);
            if(ans!=null)
            {
                if(Integer.parseInt(ans)==rs.getInt(2))
                {
                  correct=correct+1;
                }
                else
                {
                    wrong=wrong+1;
                }
            }
            
           
        }
        int total_marks;
        qr="select pmarks,nmarks from quizinfo where qcode=?;";
        PreparedStatement ps=con.prepareStatement(qr);
        ps.setString(1,qcode);
        rs=ps.executeQuery();
        rs.next();
       int  p_m=rs.getInt(1);
        int n_m=rs.getInt(2);
        
        total_marks=correct*p_m+wrong*n_m;
        
        
        
        qr="insert into squizdetails(sid,quizcode,marks,feedback) values(?,?,?,?);";
        ps=con.prepareStatement(qr);
        ps.setString(1,(String)session.getAttribute("userid"));
       
       // String temp=(String)session.getAttribute("userid");
        
        ps.setString(2,qcode);
        ps.setInt(3,total_marks);
        ps.setString(4,request.getParameter("message")); 
       
        //out.println(temp);
        //out.println(qcode);
        //out.println(total_marks);
        //out.println(request.getParameter("message"));
        
        int n=ps.executeUpdate();
        
       
        
        response.sendRedirect("StudentHome.jsp");
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
