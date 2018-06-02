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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class SendResume extends HttpServlet {

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
        
        String s1=request.getParameter("name");
        String s2=request.getParameter("uid");
        String s3=request.getParameter("qual");
        String s4=request.getParameter("spec");
        String s5=request.getParameter("exp");
        String s6=request.getParameter("mno");
        
       try
       {
        String qr;
        qr="select * from permittedprofessors where userid=?;";
        ps=con.prepareStatement(qr);
        ps.setString(1,s2);
        rs=ps.executeQuery();
        
             if(rs.next()==false)
               {
                  qr="select * from sentresume where userid=?;";
       
                       
                      
                           ps=con.prepareStatement(qr);
                           ps.setString(1,s2);
                           rs=ps.executeQuery();
             
                           if(rs.next()==false)
                              {
                                   qr="select * from declinedrequests where userid=?;";
                                   ps=con.prepareStatement(qr);
                                   ps.setString(1,s2);
                                   rs=ps.executeQuery();
                                   if(rs.next())
                                     {
                                        qr="delete from declinedrequests where userid=?;";
                                        ps=con.prepareStatement(qr);
                                        ps.setString(1,s2);
                                        int no=ps.executeUpdate();
                                     }
                
                                    qr="insert into sentresume values(?,?,?,?,?,?,?);";
                                    ps=con.prepareStatement(qr);
                                    DateFormat df = new SimpleDateFormat("20yy-MM-dd");
                                    Date dateobj = new Date();
                                    String s7=df.format(dateobj);
                                         //System.out.println(df.format(dateobj));
                                         //out.println(s7);
               
                                           ps.setString(1,s2);
                                           ps.setString(2,s1);
                                           ps.setString(3,s3);
                                           ps.setString(4,s4);
                                           ps.setString(5,s5);
                                           ps.setString(6,s6);
                                           ps.setString(7,s7);
            
              
             
                                           int n=ps.executeUpdate();
             
                                              // out.println("Hyee");
             
                                             out.println("<pre>");
                                             out.println("<h2>Resume is sent</h2>");
                                             out.println("<h3><a href=ProfessorHome.jsp>Home</a></h3>");
                                             out.println("</pre>");
                               }
                               else
                                {
                                  String date=rs.getString(7);
                                  out.println("<pre>");
                                   HttpSession session=request.getSession();
                                  String nm=(String)session.getAttribute("username");
                                  out.println("<h2>"+"Hey "+nm+"..!!</h2>");
                                  out.println("<h2>Resume has been sent already on "+date+"</h2>");
                                  out.println("<h3><a href=ProfessorHome.jsp>Home</a></h3>");
                                  out.println("</pre>");
                                }
                        
                        
                           }
                        else
                           {
                              HttpSession session=request.getSession();
                              String nm=(String)session.getAttribute("username");
                              out.println("<h2>"+"Hey "+nm+" !!</h2>");
                              out.println("<h3>You have already sent your resume and your request has been accepted</h3>");
                              out.println("<h3><a href=ProfessorHome.jsp>Home</a></h3>");
                           }
             }
    
       catch(Exception ex)
       {
           out.println(ex);
       }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

     
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

