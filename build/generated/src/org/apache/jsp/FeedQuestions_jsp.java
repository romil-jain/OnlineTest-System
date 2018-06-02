package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.Connection;
import java.sql.DriverManager;

public final class FeedQuestions_jsp extends org.apache.jasper.runtime.HttpJspBase
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

  try 
  {
            ServletContext context=getServletContext();
            String driver=context.getInitParameter("driver-name");
            String url=context.getInitParameter("connection-url");
            String uid=context.getInitParameter("userid");
            String pwd=context.getInitParameter("password");
            Class.forName(driver);
            Connection con=DriverManager.getConnection(url,uid,pwd);
  }
  catch(Exception e)
  {}


      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Feed Questions</title>\n");
      out.write("       \n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <form action=\"FeedQuestions\" method=\"post\">\n");
      out.write("           <div><textarea rows=\"8\" cols=\"100\" name=\"q\">Enter the question here</textarea></div>\n");
      out.write("           <div>\n");
      out.write("            <div><textarea rows=\"5\" cols=\"50\" name=\"op1\">Option 1</textarea></div>\n");
      out.write("            <div><textarea rows=\"5\" cols=\"50\" name=\"op2\">Option 2</textarea></div>\n");
      out.write("            <div><textarea rows=\"5\" cols=\"50\" name=\"op3\">Option 3</textarea></div>\n");
      out.write("            <div><textarea rows=\"5\" cols=\"50\" name=\"op4\">Option 4</textarea></div>\n");
      out.write("            <div>\n");
      out.write("                <div><p>Enter the correct option</p><input type=\"number\" name=\"ans\"/></div>\n");
      out.write("                <input type=\"submit\" value=\"Add more Questions\">\n");
      out.write("            </div>\n");
      out.write("            </div>\n");
      out.write("          </form>\n");
      out.write("        <a href=\"ProfessorSection.jsp\">Finish</a>\n");
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
