<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head><center><font color="red" size=4> Item&Seller Reports</font></center>
    <base href="<%=basePath%>">
    
    <title>Item reports</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <body>
  <body bgcolor=#a3d881>
  
  <%! 
  String itemid,catid, itemname,desc,summary,stprice,endprice,incrprice,stdate,enddate,sellerid,bidcnt;
  
   %>
  <%
  Class.forName("oracle.jdbc.driver.OracleDriver");
  Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","auction","auction");
  Statement stm=con.createStatement();
  ResultSet rs=stm.executeQuery("select *from item_master");
  %>
   <table  border=2 bgcolor="">
   <tr><th>ItemID</th><th>CatID</th><th>ItemName </th><th> Description</th><th>Summary</th><th>StartPrice</th><th> IncrPrice</th><th>StDate</th><th>EndDate</th><th>SellerID </th><th> BidCount</th></tr>
 <%
  while(rs.next())
  {
  itemid=rs.getString(1);
  catid=rs.getString(2);
  itemname=rs.getString(3);
  desc=rs.getString(4);
  summary=rs.getString(5);
  stprice=rs.getString(6);
  incrprice=rs.getString(7);
  stdate=rs.getString(8);
  enddate=rs.getString(9);
  sellerid=rs.getString(10);
  bidcnt=rs.getString(11);
%>
 
<tr><td><%=itemid%></td><td><%=catid %></td><td><%=itemname %></td><td><%=desc %></td><td><%=summary%></td><td><%=stprice %></td><td><%=incrprice %></td><td><%=stdate %></td><td><%=enddate%></td><td><%=sellerid %></td><td><%=bidcnt %></td></tr>

<%
  }
 %>

  </table>
 
  <center><a href="Reports.html">Back</a></center>
  </body></html>

