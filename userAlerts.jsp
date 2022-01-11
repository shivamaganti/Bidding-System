		<%@page import="java.sql.*" %>
		
		<BODY bgColor=#a3d881 leftMargin=0 topMargin=0 MARGINHEIGHT=0 MARGINWIDTH=0 link=#ffffff alilnk=#ffffff vlink=#ffffff><!-- ImageReady Slices (Untitled-1) -->
		<div id=Layer1 style='Z-INDEX: 1; LEFT: 410px; WIDTH: 328px; POSITION: absolute; TOP: 65px; HEIGHT: 35px'>
		<form action='SearchServlet' method=post ><input name=sstring>
		<select  name=category> 
		<option >ChooseCategory</option><option >Electronics</option><option >Computers</option>
		<option >Mobiles</option><option >Jewellery</option><option >Collections</option>
		<option >Interiors</option><option >Travel</option><option >Miscilanious</option></select> 
		<input type=submit value=Go ></form></div><!-- End ImageReady Slices -->
		<TABLE cellSpacing=0 cellPadding=0 width=781 border=0>
		
		  <TBODY>  <TR>    <TD width=340 rowSpan=5><IMG height=100 alt='' src='images/name-copy_01.gif' width=340></TD>
		    <TD colSpan=2 rowSpan=3><IMG height=69 alt='' src='images/name-copy_02.gif' width=81></TD>
		    <TD colSpan=12><IMG height=39 alt='' src='images/name-copy_03.gif'      width=359></TD>
		    <TD width=20><IMG height=39 alt='' src='images/spacer.gif'   width=1></TD></TR>
		  <TR>    <TD width=51><A href='home.htm'><IMG height=12 alt='' src='images/name-copy_04.gif' width=51 border=0></A></TD>
		    <TD width=4><IMG height=12 alt='' src='images/name-copy_05.gif'       width=4></TD>
		    <TD width=55><A href='login.htm'><IMG height=12       alt='' src='images/name-copy_06.gif' width=55 border=0></A></TD>
		    <TD width=4><IMG height=12 alt='' src='images/name-copy_07.gif'       width=4></TD>
		    <TD width=65><A href='registration.htm'><IMG       height=12 alt='' src='images/name-copy_08.gif' width=65     border=0></A></TD>
		    <TD width=7><IMG height=12 alt='' src='images/name-copy_09.gif'       width=7></TD>
		    <TD width=40><A href='sell.htm'><IMG height=12       alt='' src='images/name-copy_10.gif' width=40 border=0></A></TD>
		    <TD width=5><IMG height=12 alt='' src='images/name-copy_11.gif'       width=5></TD>
		    <TD width=46><A href='help.htm'><IMG height=12      alt='' src='images/name-copy_12.gif' width=46 border=0></A></TD>
		    <TD width=6><IMG height=12 alt='' src='images/name-copy_13.gif'       width=6></TD>
		    <TD width=64><IMG height=12 alt='' src='images/name-copy_14.gif'       width=64></TD>
		    <TD width=12><IMG height=12 alt='' src='images/name-copy_15.gif'       width=12></TD>
		    <TD><IMG height=12 alt='' src='images/spacer.gif' width=1></TD></TR>
		    <TR>  <TD colSpan=12 rowSpan=3><IMG height=49 alt=''       src='images/name-copy_16.gif' width=359></TD>
		    <TD><IMG height=18 alt='' src='images/spacer.gif' width=1></TD></TR>
		    <TR>  <TD width=60><IMG height=16 alt='' src='images/name-copy_17.gif'       width=60></TD>
		    <TD width=21 rowSpan=2><IMG height=31 alt=''       src='images/name-copy_18.gif' width=21></TD>
		    <TD><IMG height=16 alt='' src='images/spacer.gif' width=1></TD></TR>  <TR>
		
		    <TD><IMG height=15 alt='' src='images/name-copy_19.gif' width=60></TD>
		    <TD><IMG height=15 alt='' src='images/spacer.gif'   width=1></TD></TR></TBODY></TABLE><br><br>
		<%! 
		String itemcode=null;
		%>
		<%
		Connection con=null;
		Statement stmt=null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con=	DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","auction","auction");
			System.out.println("<<<>>>>>>>>>>>connected");
		String item_id="select itemid from item_master";
		stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery(item_id);
		%>
		
		<form action="./userAlertsSrv">

<table border="1" align="center">
<tr>
<th align="left">UserID</th><td><input type="text" name="userid"></td></tr>
<tr>
<th align="left">MaxAuctionAmt</th><td><input type="text" name="maxauction"></td>
</tr>
<tr><th>Select Item</th><td><select  name=category1>
 		<option >--</option>
		
		<%
		while(rs.next())
		{
		itemcode=rs.getString("ITEMID");
		
		
		
		
		 %>

		<option ><%=itemcode%></option>
		
		<%} %>
		</select>
		</td></tr>
<tr><th colspan="2"><input type="submit" value="submit" ><input type="reset" value="Clear" ></th></tr>
</table>
</form>
		<br><br><hr><CENTER><br><br> 
		<A href=NewToday>New Items</A><STRONG>|</STRONG> 
		<A href=EndToday>Closing Items</A> 
		<STRONG>|</STRONG><A href=sell.htm>Sell Items</A> 
		<STRONG>|</STRONG><A href=home.htm>Home</A> 
		<br><br> 



