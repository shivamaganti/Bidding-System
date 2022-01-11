<html>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*"%>
<body bgcolor=green>
<%
String name=request.getParameter("seller");
String pwd=request.getParameter("password");
String iname=request.getParameter("itemname");
String cat=request.getParameter("category");
String sum=request.getParameter("v");
String n1="";
String p1="";
String catid="";
int flag=0;
try
{
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "auction", "auction");
Statement s1=con.createStatement();
ResultSet rs1=s1.executeQuery("select catid from category_master where cat_name='"+cat+"'");

if(rs1.next())
	{
		catid=rs1.getString(1);
	}
else
out.println("<center><br/><br/><h2>Invalid User Name</h2></center>");
}
catch(Exception e)
{
	e.printStackTrace();
}
		//Statement s2=con.createStatement();
		//PreparedStatement ps=s2.executeUpdate("insert into item_master values(?,?,?,?,?,?,?,?,?,?,?)");
		//ps.setString();
	

%>
</body>
</html>