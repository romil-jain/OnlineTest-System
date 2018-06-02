

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


public class ResultViewer extends HttpServlet {

   
    
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
            String sid=(String)session.getAttribute("userid");
            String qr="select qcode,qname from quizinfo where qcode in (select quizcode from squizdetails where sid=? and rank!=null);";
           
             PreparedStatement ps=con.prepareStatement(qr,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
             
             
            //PreparedStatement ps=con.prepareStatement(qr);
            ps.setString(1,sid);
            
           
            
            ResultSet rs=ps.executeQuery();
            
             //out.println("Helllloooo");
            
           // ps=con.prepareStatement(qr);
            //ps.setString(1,sid);
            //ResultSet rs=ps.executeQuery();
            
             boolean b=rs.next();
             if(b==false)
             {
                 out.println("<h2>There are no quizes attempted yet..!!</h2>");
                 out.println("<h3><a href=\"StudentHome.jsp\">Home</a></h3>");
             }
             else
             {
                 rs.beforeFirst();
                 
                 out.println("<table>");
            out.println("<tr>");
            
            out.println("<td>");
            out.println("Quiz code");
            out.println("</td>");
            
            out.println("<td>");
            out.println("Quiz name");
            out.println("</td>");
            
            out.println("<td>");
            out.println("Rank");
            out.println("</td>");
            
             out.println("<td>");
            out.println("Marks");
            out.println("</td>");
            
            out.println("</tr>");
                 
                 while(rs.next())
                 {
                     qr="select rank,marks from squizdetails where sid=? and quizcode=?;";
                     
                     ps=con.prepareStatement(qr);
                     ps.setString(1,sid);
                     ps.setString(2,rs.getString(1));
                     ResultSet rs1=ps.executeQuery();
                      out.println("<tr>");
            
            out.println("<td>");
            out.println(rs.getString(1));
            out.println("</td>");
            
            out.println("<td>");
            out.println(rs.getString(2));
            out.println("</td>");
            
            out.println("<td>");
            out.println(rs1.getInt(1));
            out.println("</td>");
            
             out.println("<td>");
            out.println(rs1.getInt(2));
            out.println("</td>");
            
            out.println("</tr>");
                 }
                 out.println("</table>");
                 out.println("<h2><a href=\"StudentHome.jsp\">Home</a></h2>");
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
