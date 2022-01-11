import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchServlet
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
  
  public void doPost(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException
  {
    try
    {
      res.setContentType("text/html");
      PrintWriter pw = res.getWriter();
      String sstring = req.getParameter("sstring");
      String cstring = req.getParameter("category");
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
      pw.println("  <TR>    <TD colSpan=12 rowSpan=3><IMG height=49 alt=''       src='images/name-copy_16.gif' width=359></TD>");
      pw.println("    <TD><IMG height=18 alt='' src='images/spacer.gif' width=1></TD></TR>");
      pw.println("  <TR>    <TD width=60><IMG height=16 alt='' src='images/name-copy_17.gif'       width=60></TD>");
      pw.println("    <TD width=21 rowSpan=2><IMG height=31 alt=''       src='images/name-copy_18.gif' width=21></TD>");
      pw.println("    <TD><IMG height=16 alt='' src='images/spacer.gif' width=1></TD></TR>  <TR>");
      pw.println("    <TD><IMG height=15 alt='' src='images/name-copy_19.gif' width=60></TD>");
      pw.println("    <TD><IMG height=15 alt='' src='images/spacer.gif'   width=1></TD></TR></TBODY></TABLE><br><br>");
      Vector results = new Vector();
      int val = 0;
      if ((sstring.length() == 0) && (!cstring.equals("ChooseCategory")))
      {
        System.out.println("in second");
        results = searchCatInfo(cstring);
        val = 2;
      }
      if ((sstring.length() == 0) && (cstring.equals("ChooseCategory")))
      {
        System.out.println("in third");
        pw.println("<b><center>give a search string or category<b></center>");
        val = 3;
      }
      if ((sstring.length() != 0) && (!cstring.equals("ChooseCategory")))
      {
        System.out.println("in fourth");
        results = searchCatItemInfo(sstring, cstring);
        val = 4;
      }
      if ((sstring.length() != 0) && (cstring.equals("ChooseCategory")))
      {
        System.out.println("in first");
        results = searchItemInfo(sstring);
        val = 1;
      }
      if (val != 3)
      {
        int size = results.size();
        System.out.println(size);
        pw.println("<br><center><b> Your  Search String has found " + size);
        pw.println("records</b></center><br>");
        if (size > 0)
        {
          pw.println("<table border=2>");
          pw.println("<tr><td><b>Item Code</b></td><td><b>Category Id</b></td><td><b>Item Name</b></td>");
          pw.println("<td><b>Description</b></td><td><b>Summary</b></td><td><b>Start Price</b></td>");
          pw.println("<td><b>Increament Amount</b></td><td><b>Start Date</b></td><td><b>End Date</b></td>");
          pw.println("<td><b>Seller Id</b></td><td><b>Bid Count</b></td></tr>");
          for (int i = 0; i < size; i++)
          {
            StringTokenizer st = new StringTokenizer((String)results.elementAt(i), "~");
            pw.println("<tr>");
            String iid = st.nextToken();
            pw.println("<td><a href='ItemDetails?id=" + iid + "' >" + iid + "</a></td>");
            for (; st.hasMoreTokens(); pw.println("</td>")) {
              pw.println("<td>" + st.nextToken());
            }
            pw.println("</tr>");
          }
        }
      }
      pw.println("</table></center><br><br><hr><CENTER>");
      pw.println("<A href=NewToday>New Items</A><STRONG>|</STRONG>");
      pw.println("<A href=EndToday>Closing Items</A>");
      pw.println("<STRONG>|</STRONG><A href=sell.htm>Sell Items</A>");
      pw.println("<STRONG>|</STRONG><A href=home.htm>Home</A>");
      pw.println("<br><br>");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public Vector searchItemInfo(String s)
    throws RemoteException
  {
    Vector v = new Vector();
    try
    {
      PreparedStatement ps = con.prepareStatement("select * from item_master where summary like ?");
      ps.setString(1, "%" + s + "%");
      String st;
      for (ResultSet rs = ps.executeQuery(); rs.next(); v.addElement(st)) {
        st = rs.getString(1) + "~" + rs.getString(2) + "~" + rs.getString(3) + "~" + rs.getString(4) + "~" + rs.getString(5) + "~" + rs.getString(6) + "~" + rs.getString(7) + "~" + rs.getString(8) + "~" + rs.getString(9) + "~" + rs.getString(10) + "~" + rs.getString(11);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return v;
  }
  
  public Vector searchCatInfo(String s)
    throws RemoteException
  {
    Vector data = new Vector();
    String st = "";
    try
    {
      PreparedStatement ps = con.prepareStatement("select catid from category_master where cat_name=?");
      ps.setString(1, s);
      ResultSet rs = ps.executeQuery();
      rs.next();
      String id = rs.getString(1);
      rs.close();
      ps.close();
      if (id != null)
      {
        PreparedStatement psmt = con.prepareStatement("select * from item_master where catid =?");
        psmt.setString(1, id);
        for (ResultSet recs = psmt.executeQuery(); recs.next(); data.addElement(st)) {
          st = recs.getString(1) + "~" + recs.getString(2) + "~" + recs.getString(3) + "~" + recs.getString(4) + "~" + recs.getString(5) + "~" + recs.getString(6) + "~" + recs.getString(7) + "~" + recs.getString(8) + "~" + recs.getString(9) + "~" + recs.getString(10) + "~" + recs.getString(11);
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return data;
  }
  
  public Vector searchCatItemInfo(String sString, String cName)
    throws RemoteException
  {
    Vector data = new Vector();
    try
    {
      PreparedStatement ps = con.prepareStatement("select catid from category_master where cat_name=?");
      ps.setString(1, cName);
      ResultSet rs = ps.executeQuery();
      rs.next();
      String id = rs.getString(1);
      System.out.println("cat id : " + id);
      rs.close();
      ps.close();
      if (id != null)
      {
        PreparedStatement psmt = con.prepareStatement("select * from item_master where catid =? and summary like ?");
        psmt.setString(1, id);
        psmt.setString(2, "%" + sString + "%");
        ResultSet recs = psmt.executeQuery();
        System.out.println("after result set: " + sString);
        for (; recs.next(); System.out.println("3"))
        {
          System.out.println("1");
          String st = recs.getString(1) + "~" + recs.getString(2) + "~" + recs.getString(3) + "~" + recs.getString(4) + "~" + recs.getString(5) + "~" + recs.getString(6) + "~" + recs.getString(7) + "~" + recs.getString(8) + "~" + recs.getString(9) + "~" + recs.getString(10) + "~" + recs.getString(11);
          System.out.println("2");
          data.addElement(st);
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return data;
  }
}