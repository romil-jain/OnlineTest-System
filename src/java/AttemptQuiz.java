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
import java.sql.ResultSetMetaData;
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
public class AttemptQuiz extends HttpServlet {

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
         session.setAttribute("quizcode",request.getParameter("qcode"));
        
        try
        {
            String s=request.getParameter("qcode"); 
            String qr="select noq from quizinfo where qcode=?;";
            ps=con.prepareStatement(qr);
            ps.setString(1,s);
            rs=ps.executeQuery();
            rs.next();
            int n=rs.getInt(1);
            
           
            qr="select qno,question,op1,op2,op3,op4 from "+s+";";
            Statement st=con.createStatement();
           
            rs=st.executeQuery(qr);
            int count;
            out.println("<form action=\"ResultCalculator\" method=\"get\">");
            int question_count=0;
            
           
            //ResultSetMetaData rsmd=rs.getMetaData();
            //int n=rsmd.getColumnCount();
            
            out.println("Total number of questions in this Quiz:"+"<input type='number' name=\"noq\" value='"+n+"' readonly='readonly'/>");
            
            
            while(rs.next())
            {
                question_count+=1;
                String field_name="r"+question_count;
                count=1;
                out.println("<div>");
                out.println(rs.getString(1));
                 out.println("</div>");
                 
                  out.println("<div>");
                  out.println(rs.getString(2));
                   out.println("</div>");
                   
                    out.println("<div>");
                    out.println("<div>");
                    out.println("<input type='number' value='"+count+"' readonly='readonly'/>");
                    count=count+1;
                   out.println("<input type=\"radio\" name=\""+field_name+"\" VALUE=\"1\">"); 
                    out.println(rs.getString(3)); 
                    out.println("</div>");
                    
                    out.println("<div>");
                    out.println("<input type='number' value='"+count+"' readonly='readonly'/>");
                    count=count+1;
                   out.println("<input type=\"radio\" name=\""+field_name+"\" VALUE=\"2\">");
                    out.println(rs.getString(4)); 
                    out.println("</div>");

                    out.println("<div>");
                    out.println("<input type='number' value='"+count+"' readonly='readonly'/>");
                    count=count+1;
                    out.println("<input type=\"radio\" name=\""+field_name+"\" VALUE=\"3\">");
                    out.println(rs.getString(5)); 
                    out.println("</div>");
                    
                    out.println("<div>");
                    out.println("<input type='number' value='"+count+"' readonly='readonly'/>");
                    count=count+1;
                    out.println("<input type=\"radio\" name=\""+field_name+"\" VALUE=\"4\">");
                    out.println(rs.getString(6)); 
                    out.println("</div>");
                     out.println("</div>");

            }
             //qr="create table "+s2+"(qno int,Question varchar(200),op1 varchar(200),op2 varchar(200),op3 varchar(200),op4 varchar(200),answer int);";
            out.println("<div>");
            out.println("<textarea name=\"message\" rows=\"10\" cols=\"30\">\n" +"Enter your feedback here\n" +"</textarea>");
            out.println("</div>");
            out.println("<input type=\"submit\" value=\"Submit Quiz\"/>");
            out.println("</form>");
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
