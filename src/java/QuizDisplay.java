

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


public class QuizDisplay extends HttpServlet {

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
           String qr="select pid,qcode,qname,completed from quizinfo;";
          // Statement st=con.createStatement();
           //rs=st.executeQuery(qr);
           
            Statement st = con.createStatement(
                       ResultSet.TYPE_SCROLL_SENSITIVE,
                       ResultSet.CONCUR_UPDATABLE);
            rs=st.executeQuery(qr);
           
           if(rs.next())
           {
               rs.beforeFirst();
               //out.println("<table>");
               
                out.println("<table border=1>");
            out.println("<tr>");
            
            
            out.println("<td>");
            out.println("Professor Id");
            out.println("</td>");
            
            out.println("<td>");
            out.println("Quiz Code");
            out.println("</td>");
            
          
            
            out.println("<td>");
            out.println("Quiz Name");
            out.println("</td>");
            
            out.println("<td>");
            out.println("Status");
            out.println("</td>");
            
            out.println("</tr>");
            
            while(rs.next())
            {
                out.println("<tr>");
            
            
            out.println("<td>");
            out.println(rs.getString(1));
            out.println("</td>");
            
            out.println("<td>");
            out.println(rs.getString(2));
            out.println("</td>");
            
          
            
            out.println("<td>");
            out.println(rs.getString(3));
            out.println("</td>");
            
            out.println("<td>");
            out.println(rs.getString(4));
            out.println("</td>");
            
            out.println("</tr>");
            }
            out.println("</table>");
            out.println("<h2><a href=\"AdminHome.jsp\">Home</a></h2>");
           }
           else
           {
               out.println("<h2>No Quizes yet</h2>");
               out.println("<h2><a href=\"AdminHome.jsp\">Home</a></h2>");
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
