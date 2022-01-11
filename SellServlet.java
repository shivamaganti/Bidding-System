import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SellServlet
  extends HttpServlet
{
  protected SessionCache sessionCache;
  protected long flushTimeout;
  protected long sessionTimeout;
  
  public SellServlet()
  {
    flushTimeout = 600000L;
    sessionTimeout = 7200000L;
  }
  
  public Connection getConnection()
    throws SQLException
  {
    return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "auction", "auction");
  }
  
  public void init(ServletConfig config)
    throws ServletException
  {
    super.init(config);
    sessionCache = new SessionCache(flushTimeout);
  }
  
  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException
  {
    Cookie thisCookie = null;
    response.setContentType("text/html");
    PrintWriter pw = response.getWriter();
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
    Session session = null;
    setNoCache(request, response);
    try
    {
      if (session == null)
      {
        String seller = request.getParameter("seller");
        String pwd = request.getParameter("password");
        String itemname = request.getParameter("itemname");
        String catname = request.getParameter("category");
        String desc = request.getParameter("description");
        String summary = request.getParameter("summary");
        System.out.println("hai1");
        int startprice = Integer.parseInt(request.getParameter("startprice"));
        int incrprice = Integer.parseInt(request.getParameter("incrprice"));
        int acDays = Integer.parseInt(request.getParameter("auctiondays"));
        Connection con = getConnection();
        Statement st = con.createStatement();
        System.out.println("hai2");
        ResultSet ress = st.executeQuery("select pwd from uinfo_master where uname='" + seller + "' ");
        boolean bol = ress.next();
        if (bol)
        {
          String password = ress.getString(1);
          System.out.println(password);
          System.out.println(pwd);
          System.out.println(password.equals(pwd));
          if (password.equals(pwd))
          {
            session = startSession(request.getParameter("seller"), request.getParameter("password"), response);
            System.out.println(catname);
            System.out.println("hai");
            PreparedStatement ps1 = con.prepareStatement("select catid from category_master where cat_name=?");
            ps1.setString(1, catname);
            ResultSet rs = ps1.executeQuery();
            rs.next();
            String catid = rs.getString(1);
            int cword = catid.charAt(0);
            System.out.println("cword: " + cword);
            PreparedStatement ps2 = con.prepareStatement("select max(itemid) from item_master where itemid like '" + (char)cword + "%' ");
            ResultSet rs2 = ps2.executeQuery();
            rs2.next();
            System.out.println("hari");
            String temp = "";
            int titemid = 0;
            temp = rs2.getString(1);
            System.out.println(temp);
            if (temp.equals("null")) {
              titemid = 0;
            } else {
              titemid = Integer.parseInt(temp.substring(1));
            }
            System.out.println("1: " + titemid);
            String itemid = "";
            titemid++;
            System.out.println("2: " + titemid);
            itemid = (char)cword + "000" + titemid;
            System.out.println("3: " + itemid);
            Connection con1 = getConnection();
            Statement st1 = con1.createStatement();
            int i = st1.executeUpdate("insert into item_master values('" + itemid + "','" + catid + "','" + itemname + "','" + desc + "','" + summary + "'," + startprice + "," + incrprice + ",to_date(sysdate),to_date(sysdate+" + acDays + "),'" + seller + "',0) ");
            pw.println("<b><center> U have Successfully have posted your item to auction online<br>");
            pw.println("<br> Your item id is: </b> " + itemid);
            con.close();
          }
          else
          {
            pw.println("<br><br>Wrong Password");
          }
        }
        else
        {
          pw.println("<br><br>User name does not exist");
        }
      }
      pw.println("</b><br><br><hr>");
      pw.println("<A href=NewToday>New Items</A><STRONG>|</STRONG>");
      pw.println("<A href=EndToday>Closing Items</A>");
      pw.println("<STRONG>|</STRONG><A href=sell.htm>Sell Items</A>");
      pw.println("<STRONG>|</STRONG><A href=home.htm>Home/Search</A>");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  protected boolean verifyPassword(String theuser, String password)
    throws SQLException
  {
    String originalPassword = null;
    Connection con = getConnection();
    Statement stmt = con.createStatement();
    stmt.executeQuery("select pwd from uinfo_master where uname='" + theuser + "'");
    ResultSet rs = stmt.getResultSet();
    if (rs.next()) {
      originalPassword = rs.getString(1);
    }
    stmt.close();
    con.close();
    return originalPassword.equals(password);
  }
  
  protected Session startSession(String theuser, String password, HttpServletResponse response)
    throws SQLException
  {
    Session session = null;
    if (verifyPassword(theuser, password))
    {
      session = new Session(theuser);
      session.setExpires(sessionTimeout + System.currentTimeMillis());
      sessionCache.put(session);
      Cookie c = new Cookie("AUCTION", String.valueOf(session.getId()));
      c.setMaxAge(-1);
      response.addCookie(c);
    }
    return session;
  }
  
  private Session validateSession(HttpServletRequest request, HttpServletResponse response)
  {
    Cookie[] c = request.getCookies();
    Session session = null;
    if (c != null) {
      for (int i = 0; (i < c.length) && (session == null); i++) {
        if (c[i].getName().equals("AUCTION"))
        {
          String key = String.valueOf(c[i].getValue());
          session = sessionCache.get(key);
        }
      }
    }
    return session;
  }
  
  protected void endSession(Session session)
  {
    synchronized (sessionCache)
    {
      sessionCache.remove(session);
    }
  }
  
  private void setNoCache(HttpServletRequest request, HttpServletResponse response)
  {
    if (request.getProtocol().compareTo("HTTP/1.0") == 0) {
      response.setHeader("Pragma", "no-cache");
    } else if (request.getProtocol().compareTo("HTTP/1.1") == 0) {
      response.setHeader("Cache-Control", "no-cache");
    }
    response.setDateHeader("Expires", 0L);
  }
  
  static
  {
    try
    {
      Class.forName("oracle.jdbc.driver.OracleDriver");
    }
    catch (Exception e)
    {
      System.out.println("new pool error" + e);
    }
  }
}