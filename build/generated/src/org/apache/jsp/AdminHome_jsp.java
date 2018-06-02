package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class AdminHome_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write('\n');
      out.write('\n');

    String nm=(String)session.getAttribute("username");
    if(nm==null){
        response.sendRedirect("index.jsp");
    }
   
    long val=session.getCreationTime();
    java.util.Date dt=new java.util.Date(val);
    int n=session.getMaxInactiveInterval();

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Admin Home</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("     \n");
      out.write("                                   <h1>Admin Home</h1>\n");
      out.write("        <h2>WELCOME ");
      out.print(nm);
      out.write("</h2>\n");
      out.write("        <h4>Your Are With Us Since ");
      out.print(dt);
      out.write("</h4>\n");
      out.write("        <h4>If you remain idle for ");
      out.print(n);
      out.write(" sec , your session will expire</h4>\n");
      out.write("        <hr>\n");
      out.write("        <h2><a href=\"DisplayRequest\">See Professor Requests</a></h2>\n");
      out.write("        \n");
      out.write("        <h2><a href=\"QuizDisplay\">See current quizes</a></h2>\n");
      out.write("        <h2><a href=\"ProfessorList\">See the professors list</a></h2>\n");
      out.write("        <h2><a href=\"KillSession\">LogOut</a></h2>\n");
      out.write("        \n");
      out.write("     \n");
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
