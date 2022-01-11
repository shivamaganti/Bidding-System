import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AfterLogin
  extends HttpServlet
{
  Connection con;
  
  public void init(ServletConfig sc)
    throws ServletException
  {
    super.init(sc);
    try
    {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "auction", "auction");
    }
    catch (Exception e)
    {
      System.out.println(e);
    }
  }
  
  public void doGet(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException
  {
    res.setContentType("text/html");
    PrintWriter pw = res.getWriter();
    HttpSession hs = req.getSession(true);
    String theuser = (String)hs.getValue("user");
    try
    {
      Statement st = con.createStatement();
      ResultSet rs = st.executeQuery("select * from uinfo_master where uname='" + theuser + "' ");
      String pwd = "";
      String fname = "";
      String lname = "";
      String email = "";
      String phno = "";
      String address = "";
      String city = "";
      String state = "";
      String pin = "";
      String country = "";
      String ccardno = "";
      for ( ccardno = ""; rs.next(); ccardno = rs.getString(12))
      {
        pwd = rs.getString(2);
        fname = rs.getString(3);
        lname = rs.getString(4);
        email = rs.getString(5);
        phno = rs.getString(6);
        address = rs.getString(7);
        city = rs.getString(8);
        state = rs.getString(9);
        pin = rs.getString(10);
        country = rs.getString(11);
      }
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
      pw.println("<br><br><center><h3><a href=\"./userAlerts.jsp\">Click HereForAlerts</a></h3><br>");
      pw.println("<form action=EditUserInfo method=post>");
      pw.println("<br><br><center><h3>Edit Information</h3><br>");
      pw.println("<table><tr><td>password: </td><td><input type=password name=password value='" + pwd + "'></td></tr>");
      pw.println("<tr><td>First Name: </td><td><input type=text name=fname value='" + fname + "'></td></tr>");
      pw.println("<tr><td>Last Name: </td><td><input type=text name=lname value='" + lname + "'></td></tr>");
      pw.println("<tr><td>Email: </td><td><input type=text name=email value='" + email + "'></td></tr>");
      pw.println("<tr><td>Phone Number: </td><td><input type=text name=phno value='" + phno + "'></td></tr>");
      pw.println("<tr><td>Address: </td><td><input type=text name=address value='" + address + "'></td></tr>");
      pw.println("<tr><td>City: </td><td><input type=text name=city value='" + city + "'></td></tr>");
      pw.println("<tr><td>State: </td><td><input type=text name=state value='" + state + "'></td></tr>");
      pw.println("<tr><td>Pin Code: </td><td><input type=text name=pin value='" + pin + "'></td></tr>");
      pw.println("<tr><td>Country: </td><td><input type=text name=country value='" + country + "'></td></tr>");
      pw.println("<tr><td>CreditCard No: </td><td><input type=text name=ccardno value='" + ccardno + "'></td></tr>");
      pw.println("</table><br>");
      pw.println("<center><input type=submit value='Save Changes' > </form>");
      pw.println("<br><br><hr><CENTER><br><br>");
      pw.println("<A href=NewToday>New Items</A><STRONG>|</STRONG>");
      pw.println("<A href=EndToday>Closing Items</A>");
      pw.println("<STRONG>|</STRONG><A href=sell.htm>Sell Items</A>");
      pw.println("<STRONG>|</STRONG><A href=home.htm>Home</A>");
      pw.println("<br><br>");
    }
    catch (SQLException se)
    {
      System.out.println(se);
    }
  }
}