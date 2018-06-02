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
public class RespondProfessor extends HttpServlet {

    PreparedStatement ps; Connection con; ResultSet rs;
    
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
        HttpSession session=request.getSession();
        
        try
        {
            String q="select profid from permittedprofessors where profid=?;";
            ps=con.prepareStatement(q);
            ps.setString(1,request.getParameter("pid"));
            rs=ps.executeQuery();
            
            if(rs.next()==false)
            {
                String query="select * from sentresume where userid=?;";
            ps=con.prepareStatement(query);
            ps.setString(1,(String)session.getAttribute("acc_prof_userid"));
            rs=ps.executeQuery();
            boolean b=rs.next();
            
            String s1=request.getParameter("pid");
            String s2=request.getParameter("pwd");
            
            String s3=rs.getString(3);
            String s4=rs.getString(4);
            String s5=rs.getString(5);
            String s6=rs.getString(6);
            String s7=rs.getString(7);
            
            String qr="insert into permittedprofessors values(?,?,?,?,?,?,?,?,?);";
            ps=con.prepareStatement(qr);
            ps.setString(1,(String)session.getAttribute("acc_prof_userid"));
            ps.setString(2,s1);
            ps.setString(3,s2);
            ps.setString(4,(String)session.getAttribute("acc_prof_name"));
            ps.setString(5,s3);
            ps.setString(6,s4);
            ps.setString(7,s5);
            ps.setString(8,s6);
            ps.setString(9,s7);
            
            int n=ps.executeUpdate();
            
            
            qr="delete from sentresume where userid=?;";
            ps=con.prepareStatement(qr);
            ps.setString(1,(String)session.getAttribute("acc_prof_userid"));
            n=ps.executeUpdate();
            
            out.println("<h2>Authentication Details are sent</h2>");
            out.println("<h3><a href=\"DisplayRequest\">See other requests</a></h3>");
            out.println("<h3><a href=AdminHome.jsp>Home</a></h3>");
            //response.sendRedirect("DisplayRequest");
            }
            else
            {
                out.println("<h2>This Professor ID already exists</h2>");
                out.println("<h3><a href=\"AssignAuthentication.jsp\">Try using a different one</a></h3>");
                out.println("<h3><a href=\"AdminHome.jsp\">Home</a></h3>");
            }
            
            
        }
        catch(Exception e)
        {
            out.println(e);
        }
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
