package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.util.*;
import java.sql.Connection;
import java.sql.ResultSet;

public final class QuizDetails_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

 
           
String profid=(String)session.getAttribute("pid");
        
    

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Quiz Details</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <form action=\"StoreQuizDetails\" method=\"get\">\n");
      out.write("        <table>\n");
      out.write("            <tbody>\n");
      out.write("                 \n");
      out.write("                  <tr>\n");
      out.write("                    <td>Professor Id</td>\n");
      out.write("                    <td><input type=\"text\" name=\"profid\" value=\"");
      out.print(profid);
      out.write("\" readonly=\"readonly\"/></td>\n");
      out.write("                </tr>\n");
      out.write("                 \n");
      out.write("                 \n");
      out.write("                <tr>\n");
      out.write("                    <td>Quiz code</td>\n");
      out.write("                    <td><input type=\"text\" name=\"qcode\"/></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>Quiz Name</td>\n");
      out.write("                    <td><input type=\"text\" name=\"qname\"/></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>no. of questions</td>\n");
      out.write("                    <td><input type=\"number\" name=\"noq\"/></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>Marks for correct answer</td>\n");
      out.write("                    <td><input type=\"number\" name=\"pmarks\"/></td>\n");
      out.write("                    \n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>Marks for wrong answer</td>\n");
      out.write("                    <td><input type=\"number\" name=\"nmarks\"/></td>\n");
      out.write("                    \n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>Start date</td>\n");
      out.write("                    \n");
      out.write("                       <td><input type=\"date\" name=\"sdate\"/></td>\n");
      out.write("                    \n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>End date</td>\n");
      out.write("                    \n");
      out.write("                       <td><input type=\"date\" name=\"edate\"/></td>\n");
      out.write("                    \n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>Time duration(In Hours)</td>\n");
      out.write("                    <td><input type=\"number\" name=\"dur\"/></td>\n");
      out.write("                    \n");
      out.write("                </tr>\n");
      out.write("                 \n");
      out.write("                \n");
      out.write("                <tr>\n");
      out.write("                 <td>\n");
      out.write("                <input type=\"submit\" value=\"Ok\"/>    \n");
      out.write("                </td>\n");
      out.write("                </tr>\n");
      out.write("            </tbody>\n");
      out.write("        </table>\n");
      out.write("        </form>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
