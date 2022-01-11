<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head><center><font color="red" size=4> Buyer Reports</font></center>
    <base href="<%=basePath%>">
    
    <title>Buyer reports</title>
    
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
  String bidderid;
  String bidamt;
  String itemid;
  String dt,form;
   %>
  <%
  Class.forName("oracle.jdbc.driver.OracleDriver");
  Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","auction","auction");
  Statement stm=con.createStatement();
  ResultSet rs=stm.executeQuery("select *from bidding_info");
  %>
   <table align='center' border=2 bgcolor="">
   <tr><th>BidderId</th><th>BidAmt</th><th>Itemid </th><th> Bidding Date</th></tr>
 <%
  while(rs.next())
  {
  bidderid=rs.getString(1);
  bidamt=rs.getString(2);
  itemid=rs.getString(3);
  dt=rs.getString(4);
%>
 
<tr><td><%=bidderid%></td><td><%=bidamt %></td><td><%=itemid %></td><td><%=dt %></td></tr>

<%
  }
 %>
 
  </table>
<center><a href="Reports.html">Back</a></center>
  </body></html>

