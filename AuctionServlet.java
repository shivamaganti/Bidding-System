import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuctionServlet
  extends HttpServlet
{
  Connection con;
  PrintWriter pw;
  Vector v;
  Vector vamt;
  
  public AuctionServlet()
  {
    v = null;
    vamt = null;
  }
  
  public void init(ServletConfig sc)
    throws ServletException
  {
    try
    {
      super.init(sc);
      Class.forName("oracle.jdbc.driver.OracleDriver");
      con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "auction", "auction");
    }
    catch (Exception ce)
    {
      ce.printStackTrace();
    }
  }
  
  public void doGet(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException
  {
    res.setContentType("text/html");
    pw = res.getWriter();
    try
    {
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
      Statement st = con.createStatement();
      String bidder = req.getParameter("bidder");
      String pwd = req.getParameter("password");
      String amount = req.getParameter("amount");
      if (amount.length() > 0)
      {
        int amt = Integer.parseInt(amount);
        String id = req.getParameter("id");
        int hbid = Integer.parseInt(req.getParameter("hbid"));
        int min = Integer.parseInt(req.getParameter("min"));
        ResultSet rs = st.executeQuery("select pwd from uinfo_master where uname='" + bidder + "' ");
        int incprice = 0;
        if (rs.next())
        {
          String dpwd = rs.getString(1);
          if (!pwd.equals(dpwd))
          {
            pw.println("<center><b>in valid password</b></center><br<br>");
          }
          else if (amt >= hbid + min)
          {
            Statement stmt = con.createStatement();
            ResultSet rst = stmt.executeQuery("select enddate,INCR_PRICE from item_master where itemid='" + id + "' ");
            rst.next();
            Date dt = rst.getDate(1);
            incprice = rst.getInt("INCR_PRICE");
            System.out.println("<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>" + incprice);
            Statement stmt2 = con.createStatement();
            ResultSet rst1 = stmt2.executeQuery("select sysdate from dual");
            rst1.next();
            Date dt1 = rst1.getDate(1);
            StringTokenizer stok = new StringTokenizer(dt.toString(), "-");
            StringTokenizer token = new StringTokenizer(dt.toString(), "-");
            int year = Integer.parseInt(token.nextToken());
            int month = Integer.parseInt(token.nextToken());
            int day = Integer.parseInt(token.nextToken());
            StringTokenizer token1 = new StringTokenizer(dt1.toString(), "-");
            int year1 = Integer.parseInt(token1.nextToken());
            int month1 = Integer.parseInt(token1.nextToken());
            int day1 = Integer.parseInt(token1.nextToken());
            if (year >= year1)
            {
              v = new Vector();
              vamt = new Vector();
              if (month >= month1)
              {
                if ((day >= day1) || (month > month1))
                {
                  Statement stmt1 = con.createStatement();
                  Statement stmt3 = con.createStatement();
                  int i = stmt1.executeUpdate("insert into bidding_info values('" + bidder + "'," + amt + ",'" + id + "',sysdate) ");
                  Statement stm1 = con.createStatement();
                  stm1.executeUpdate("update item_master set bidcnt=bidcnt+1 where itemid='" + id + "' ");
                  String max_bid = "select MAX_AUCTIONAMT ,USERID from AUCTION_ALERTS where PRODUCT_ITEM='" + id + "' and MAX_AUCTIONAMT>='" + amt + "'";
                  float amount_max = 0.0F;
                  String aut_name = null;
                  for (ResultSet rs2 = stmt1.executeQuery(max_bid); rs2.next(); vamt.add(Float.valueOf(amount_max)))
                  {
                    amount_max = rs2.getFloat("MAX_AUCTIONAMT");
                    aut_name = rs2.getString("USERID");
                    v.add(aut_name);
                  }
                  System.out.println(v + "<<<<<<<<<<>>>>>>>>>>>>>>." + vamt);
                  int a = 0;
                  for (int k = 0; (k < v.size()) && (k < vamt.size()); k++)
                  {
                    String update_alerts = "insert into bidding_info values('" + v.get(k) + "',( select max(bamt) from bidding_info where ITEMID='" + id + "')+ (select INCR_PRICE from item_master where itemid='" + id + "'),'" + id + "',sysdate)";
                    System.out.println("<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>" + update_alerts);
                    String item_master = "update item_master set bidcnt=bidcnt+1 where itemid='" + id + "'";
                    System.out.println("<<<<<<<<<<>>>>>>" + item_master);
                    stmt1.executeUpdate(update_alerts);
                    stmt3.executeUpdate(item_master);
                    a++;
                    System.out.println("<<<<>>>>>>" + a);
                  }
                  pw.println("<center><b>congratulations u have sucessfully bid for the item</b><br<br></center>");
                }
                else
                {
                  pw.println("<center><b>auction is closed a " + (day1 - day) + " day before</b><br<br></center>");
                }
              }
              else {
                pw.println("<center><b>auction closed a " + (month1 - month) + " month before</b><br<br></center>");
              }
            }
            else
            {
              pw.println("<center><b>auction closed a " + (year1 - year) + " year before</b><br<br></center>");
            }
          }
          else
          {
            pw.println("<center><b>bid more than current value + mininmum increament</b><br<br></center>");
          }
        }
        else
        {
          pw.println("<center><b>User Name doesnot exist</b></center><br<br>");
        }
      }
      else
      {
        pw.println("<br><center>specify the bid amount</center>");
      }
    }
    catch (NumberFormatException ne)
    {
      pw.println("<br><b><center>enter a numeric value</center></b><br><br>");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    pw.println("<br><br><hr><CENTER><STRONG></STRONG>");
    pw.println("<A href=NewToday>New Items</A><STRONG></STRONG>");
    pw.println("<A href=EndToday>Closing Items</A>");
    pw.println("<STRONG></STRONG><A href=sell.htm>Sell Items</A>");
    pw.println("<STRONG></STRONG><A href=home.htm>Home</A>");
  }
}