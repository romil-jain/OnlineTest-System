 

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


public class VerifyProfessor extends HttpServlet {

   
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
        HttpSession session=request.getSession();
        
        try
        {
            
            String qr="select profid,profpwd from permittedprofessors where userid=?;";
            ps=con.prepareStatement(qr);
            ps.setString(1,(String)session.getAttribute("userid"));
            rs=ps.executeQuery();
            
            
                if(rs.next())
                {
                    String s1=rs.getString(1);
                    String s2=rs.getString(2);
                    String pid=request.getParameter("pid");
                    String pwd=request.getParameter("pwd");
                     if(pid.equals(s1) && pwd.equals(s2))
                     {
                          session=request.getSession();
                          session.setAttribute("pid",pid);
                         response.sendRedirect("ProfessorSection.jsp");
                     }
                    else
                     {
                        out.println("<h2>Incorrect details</h2>");
                        out.println("<h3><a href=ProfessorHome.jsp>Try Again</a></h3>");
                     }
                }
                else
                {
                    out.println("<h2>You are not a permitted professor and not allowed to enter the professor section now</h2>");
                    out.println("<h3><a href=ProfessorHome.jsp>Try Again</a></h3>");
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
