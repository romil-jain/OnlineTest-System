

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class RankCalculator extends HttpServlet {

   
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
            //HttpSession session=request.getSession();
            String qcode=request.getParameter("qcode");
            String qr="select * from squizdetails where quizcode=? order by marks;";
            
            PreparedStatement ps=con.prepareStatement(qr,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
           //  out.println(qcode);
            
            //PreparedStatement ps=con.prepareStatement(qr);
            ps.setString(1,qcode);
            ResultSet rs=ps.executeQuery();
            int rank=1;
            int temp=0;
            
           
            
            boolean b=rs.next();
          //  out.println(b);
            
           
            
            if(b==false)
            {
                out.println("No student has attempted this test yet");
                out.println("<p><a href=\"ProfessorHome.jsp\">Home</a></p>");
            }
            else
            {
            temp=rs.getInt(3);
            rs.beforeFirst();
            
           while(rs.next())
           {
                
              
               
                   if(rs.getInt(3)==temp)
                   {
                   qr="update squizdetails set rank=? where qcode=? and sid=?;";
                   ps=con.prepareStatement(qr);
                   ps.setInt(1,rank);
                   ps.setString(2,qcode);
                   ps.setString(3,rs.getString(1));
                   int n=ps.executeUpdate();
                   temp=rs.getInt(3);
                   }
                   else
                   {
                       rank=rank+1;
                   qr="update squizdetails set rank=? where qcode=? and sid=?;";
                   ps=con.prepareStatement(qr);
                   ps.setInt(1,rank);
                   ps.setString(2,qcode);
                   ps.setString(3,rs.getString(1));
                   int n=ps.executeUpdate();
                   temp=rs.getInt(3);
                   }
               
           }
            qr="update table quizinfo set completed=\"yes\" where qcode=?;";
            ps=con.prepareStatement(qr);
            ps.setString(1,qcode);
            int n=ps.executeUpdate();
            
            out.println("<h2>Results have been uploaded on the portal</h2>");
            out.println("<h3><a href='ResultUploader'>Upload other quiz results</a></h3>");
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
