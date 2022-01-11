<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head><center><font color="red" size=4> User Reports</font></center>
    <base href="<%=basePath%>">
    
    <title>User reports</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <%! 
  String uname,pwd,fname,lname,email,phno,address,city,state,pin,country,ccardno;
   %>
  <%
  Class.forName("oracle.jdbc.driver.OracleDriver");
  Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","auction","auction");
  Statement stm=con.createStatement();
  ResultSet rs=stm.executeQuery("select *from uinfo_master");%>
  <table align="center" border=1>
   <tr><th>UserName</th><th>PassWord</th><th>First Name </th><th> Last Name</th><th>Email</th><th>Phone No</th><th>Address </th><th> City</th><th>State</th><th>PinCode</th><th>Country </th><th>Credit Card NO</th></tr>
  <%
  while(rs.next())
  {
  uname=rs.getString(1);
  pwd=rs.getString(2);fname=rs.getString(3);lname=rs.getString(4);email=rs.getString(5);
  phno=rs.getString(6);address=rs.getString(7);city=rs.getString(8);
  state=rs.getString(9);pin=rs.getString(10);country=rs.getString(11);ccardno=rs.getString(12);
 %>
 
<tr><td><%=uname %></td><td><%=pwd %></td><td><%=fname %></td><td><%=lname %></td><td><%=email %></td><td><%=phno%></td><td><%=address %></td><td><%=city %></td><td><%=state %></td><td><%=pin %></td><td><%=country %></td><td><%=ccardno %></td></tr>
  <%
  }
 %>  
  </table>
  <body bgcolor="#a3d881">
   <center><a href="Reports.html">Back</a></center>
  </body></html>

