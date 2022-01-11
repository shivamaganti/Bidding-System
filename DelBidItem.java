import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DelBidItem
  extends HttpServlet
{
  PrintWriter pw;
  Connection con;
  
  public void init(ServletConfig servletconfig)
    throws ServletException
  {
    super.init(servletconfig);
    try
    {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "auction", "auction");
    }
    catch (Exception exception)
    {
      System.out.println(exception);
    }
  }
  
  public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
    throws ServletException, IOException
  {
    httpservletresponse.setContentType("text/html");
    System.out.println("In Service :");
    pw = httpservletresponse.getWriter();
    pw.println("<BODY bgColor=#a3d881 leftMargin=0 topMargin=0 MARGINHEIGHT=0 MARGINWIDTH=0 link=#ffffff alilnk=#ffffff vlink=#ffffff><!-- ImageReady Slices (Untitled-1) -->");
    pw.println("<div id=Layer1 style='Z-INDEX: 1; LEFT: 410px; WIDTH: 328px; POSITION: absolute; TOP: 65px; HEIGHT: 35px'>");
    pw.println("<form action='SearchServlet' method=post ><input name=sstring><select  name=category> ");
    pw.println("<option >ChooseCategory</option><option >Electronics</option><option >Computers</option>");
    pw.println("<option >Mobiles</option><option >Jewellery</option><option >Collections</option>");
    pw.println("<option >Interiors</option><option >Travel</option><option >Miscilanious</option></select> ");
    pw.println("<input type=submit value=Go ></form></div><!-- End ImageReady Slices -->");
    pw.println("<TABLE cellSpacing=0 cellPadding=0 width=781 border=0>");
    pw.println("  <TBODY>  <TR>    <TD width=340 rowSpan=5><IMG height=100 alt='' src='images/name-copy_01.gif' width=340></TD>");
    pw.println("    <TD colSpan=2 rowSpan=3><IMG height=69 alt='' src='images/name-copy_02.gif' width=81></TD>");
    pw.println("    <TD colSpan=12><IMG height=39 alt='' src='images/name-copy_03.gif'      width=359></TD>");
    pw.println("    <TD width=20><IMG height=39 alt='' src='images/spacer.gif'   width=1></TD></TR>");
    pw.println("  <TR>    <TD width=51><A href='home.htm'><IMG height=12 alt='' src='images/name-copy_04.gif' width=51 border=0></A></TD>");
    pw.println("    <TD width=4><IMG height=12 alt='' src='images/name-copy_05.gif'       width=4></TD>");
    pw.println("    <TD width=55><A href='login.htm'><IMG height=12       alt='' src='images/name-copy_06.gif' width=55 border=0></A></TD>");
    pw.println("    <TD width=4><IMG height=12 alt='' src='images/name-copy_07.gif'       width=4></TD>");
    pw.println("    <TD width=65><A href='registration.htm'><IMG       height=12 alt='' src='images/name-copy_08.gif' width=65     border=0></A></TD>");
    pw.println("    <TD width=7><IMG height=12 alt='' src='images/name-copy_09.gif'       width=7></TD>");
    pw.println("    <TD width=40><A href='sell.htm'><IMG height=12       alt='' src='images/name-copy_10.gif' width=40 border=0></A></TD>");
    pw.println("    <TD width=5><IMG height=12 alt='' src='images/name-copy_11.gif'       width=5></TD>");
    pw.println("    <TD width=46><A href='help.htm'><IMG height=12      alt='' src='images/name-copy_12.gif' width=46 border=0></A></TD>");
    pw.println("    <TD width=6><IMG height=12 alt='' src='images/name-copy_13.gif'       width=6></TD>");
    pw.println("    <TD width=64><IMG height=12 alt='' src='images/name-copy_14.gif'       width=64></TD>");
    pw.println("    <TD width=12><IMG height=12 alt='' src='images/name-copy_15.gif'       width=12></TD>");
    pw.println("    <TD><IMG height=12 alt='' src='images/spacer.gif' width=1></TD></TR>");
    pw.println("    <TR>  <TD colSpan=12 rowSpan=3><IMG height=49 alt=''       src='images/name-copy_16.gif' width=359></TD>");
    pw.println("    <TD><IMG height=18 alt='' src='images/spacer.gif' width=1></TD></TR>");
    pw.println("    <TR>  <TD width=60><IMG height=16 alt='' src='images/name-copy_17.gif'       width=60></TD>");
    pw.println("    <TD width=21 rowSpan=2><IMG height=31 alt=''       src='images/name-copy_18.gif' width=21></TD>");
    pw.println("    <TD><IMG height=16 alt='' src='images/spacer.gif' width=1></TD></TR>  <TR>");
    pw.println("    <TD><IMG height=15 alt='' src='images/name-copy_19.gif' width=60></TD>");
    pw.println("    <TD><IMG height=15 alt='' src='images/spacer.gif'   width=1></TD></TR></TBODY></TABLE><br><br>");
    try
    {
      java.util.Date date = new java.util.Date();
      int i = date.getDate();
      int j = date.getMonth();
      int k = date.getYear();
      int l = 0;
      PreparedStatement preparedstatement = con.prepareStatement("delete from item_master where enddate < ?");
      System.out.println("DATE :" + new java.sql.Date(k, j, i));
      preparedstatement.setDate(1, new java.sql.Date(k, j, i));
      l = preparedstatement.executeUpdate();
      System.out.println("<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + l);
      System.out.println("<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + l);
      System.out.println("b val :" + l);
      pw.println("<center>");
      if (l == 0)
      {
        pw.println("<h3>No Product To Delete </h3>");
        pw.println("<h3>All the  Products  are under auction </h3>");
      }
      else
      {
        pw.println("<h3> Successfully " + l + "Items  Deleted From Data Base </h3>");
      }
      preparedstatement.close();
      con.close();
    }
    catch (Exception exception)
    {
      exception.printStackTrace();
    }
    pw.println("<br><br><hr><CENTER><br><br>");
    pw.println("<A href=NewToday>New Items</A><STRONG>|</STRONG>");
    pw.println("<A href=EndToday>Closing Items</A>");
    pw.println("<STRONG>|</STRONG><A href=sell.htm>Sell Items</A>");
    pw.println("<STRONG>|</STRONG><A href=home.htm>Home</A>");
    pw.println("<br><br>");
    pw.close();
  }
}