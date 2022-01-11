import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

public class userAlertsSrv
  extends HttpServlet
{
  public void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String uname = request.getParameter("userid").trim();
    String max_amt = request.getParameter("maxauction").trim();
    String category = request.getParameter("category1").trim();
    System.out.println(uname + "<<<<<<<<<" + max_amt + ">>>>>>>>>>>>>>" + category);
    Connection con = null;
    Statement stmt = null;
    int i = 0;
    String auction_alerts = "insert into AUCTION_ALERTS values('" + uname + "','" + max_amt + "','" + category + "')";
    try
    {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "auction", "auction");
      String user = "select uname from  UINFO_MASTER where UNAME='" + uname + "'";
      stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(user);
      if (rs.next())
      {
        i = stmt.executeUpdate(auction_alerts);
        if (i == 1)
        {
          JOptionPane.showMessageDialog(null, "Your Auction Amount Added SuccessFully ..", "Warning message", 2);
          response.setHeader("refresh", "2;URL=./userAlerts.jsp");
        }
        else
        {
          out.println("Un able to auction on product with no" + category);
          response.setHeader("refresh", "2;URL=./userAlerts.jsp");
        }
      }
      else
      {
        JOptionPane.showMessageDialog(null, "This is not a valid UserID..", "Warning message", 2);
        response.setHeader("refresh", "2;URL=./userAlerts.jsp");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    out.println("<BODY bgColor=#a3d881 leftMargin=0 topMargin=0 MARGINHEIGHT=0 MARGINWIDTH=0 link=#ffffff alilnk=#ffffff vlink=#ffffff><!-- ImageReady Slices (Untitled-1) -->");
    out.println("<div id=Layer1 style='Z-INDEX: 1; LEFT: 410px; WIDTH: 328px; POSITION: absolute; TOP: 65px; HEIGHT: 35px'>");
    out.println("<form action='SearchServlet' method=post ><input name=sstring><select  name=category> ");
    out.println("<option >ChooseCategory</option><option >Electronics</option><option >Computers</option>");
    out.println("<option >Mobiles</option><option >Jewellery</option><option >Collections</option>");
    out.println("<option >Interiors</option><option >Travel</option><option >Miscilanious</option></select> ");
    out.println("<input type=submit value=Go ></form></div><!-- End ImageReady Slices -->");
    out.println("<TABLE cellSpacing=0 cellPadding=0 width=781 border=0>");
    out.println("  <TBODY><TR><TD width=340 rowSpan=5><IMG height=100 alt='' src='images/name-copy_01.gif' width=340></TD>");
    out.println("    <TD colSpan=2 rowSpan=3><IMG height=69 alt='' src='images/name-copy_02.gif' width=81></TD>");
    out.println("    <TD colSpan=12><IMG height=39 alt='' src='images/name-copy_03.gif'      width=359></TD>");
    out.println("    <TD width=20><IMG height=39 alt='' src='images/spacer.gif'   width=1></TD></TR>");
    out.println("  <TR>    <TD width=51><A href='home.htm'><IMG height=12 alt='' src='images/name-copy_04.gif' width=51 border=0></A></TD>");
    out.println("    <TD width=4><IMG height=12 alt='' src='images/name-copy_05.gif'       width=4></TD>");
    out.println("    <TD width=55><A href='login.htm'><IMG height=12       alt='' src='images/name-copy_06.gif' width=55 border=0></A></TD>");
    out.println("    <TD width=4><IMG height=12 alt='' src='images/name-copy_07.gif'       width=4></TD>");
    out.println("    <TD width=65><A href='registration.htm'><IMG       height=12 alt='' src='images/name-copy_08.gif' width=65     border=0></A></TD>");
    out.println("    <TD width=7><IMG height=12 alt='' src='images/name-copy_09.gif'       width=7></TD>");
    out.println("    <TD width=40><A href='sell.htm'><IMG height=12       alt='' src='images/name-copy_10.gif' width=40 border=0></A></TD>");
    out.println("    <TD width=5><IMG height=12 alt='' src='images/name-copy_11.gif'       width=5></TD>");
    out.println("    <TD width=46><A href='help.htm'><IMG height=12      alt='' src='images/name-copy_12.gif' width=46 border=0></A></TD>");
    out.println("    <TD width=6><IMG height=12 alt='' src='images/name-copy_13.gif'       width=6></TD>");
    out.println("    <TD width=64><IMG height=12 alt='' src='images/name-copy_14.gif'       width=64></TD>");
    out.println("    <TD width=12><IMG height=12 alt='' src='images/name-copy_15.gif'       width=12></TD>");
    out.println("    <TD><IMG height=12 alt='' src='images/spacer.gif' width=1></TD></TR>");
    out.println("    <TR>  <TD colSpan=12 rowSpan=3><IMG height=49 alt=''       src='images/name-copy_16.gif' width=359></TD>");
    out.println("    <TD><IMG height=18 alt='' src='images/spacer.gif' width=1></TD></TR>");
    out.println("    <TR>  <TD width=60><IMG height=16 alt='' src='images/name-copy_17.gif'       width=60></TD>");
    out.println("    <TD width=21 rowSpan=2><IMG height=31 alt=''       src='images/name-copy_18.gif' width=21></TD>");
    out.println("    <TD><IMG height=16 alt='' src='images/spacer.gif' width=1></TD></TR>  <TR>");
    out.println("    <TD><IMG height=15 alt='' src='images/name-copy_19.gif' width=60></TD>");
    out.println("    <TD><IMG height=15 alt='' src='images/spacer.gif'   width=1></TD></TR></TBODY></TABLE><br><br>");
    out.println("<br><br><hr><CENTER><br><br>");
    out.println("<A href=NewToday>New Items</A><STRONG>|</STRONG>");
    out.println("<A href=EndToday>Closing Items</A><STRONG>|</STRONG>");
    out.println("<A href=sell.htm>Sell Items</A><STRONG>|</STRONG>");
    out.println("<A href=home.htm>Home</A>");
    out.println("<br><br>");
  }
}