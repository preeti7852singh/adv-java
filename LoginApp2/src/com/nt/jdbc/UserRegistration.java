package com.nt.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserRegistration extends HttpServlet {
Connection con=null;
PreparedStatement ps=null;
int result =0;
private static final String QUERY="INSERT INTO USER_LIST VALUES(?,?,?,?,?,?)";
public void init() {
	try {
		
		//get access to servlet context object
		
		
		ServletContext sc= getServletContext();
		//read context param values  from web.xml

		String s1=sc.getInitParameter("driver");
		
		String s2=sc.getInitParameter("dburl");
		String s3=sc.getInitParameter("dbuser");
		String s4=sc.getInitParameter("dbpwd");
		
//create jdbc con  object
		
			Class.forName(s1);

		
		con=DriverManager.getConnection(s2,s3,s4);
		//create jdbc prepared statement object

		ps=con.prepareStatement(QUERY);
		 }//try
	catch(SQLException | ClassNotFoundException se) {
		se.printStackTrace();
	}
		
	
		
	}//init



public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{
	try
	
	{
		// SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
       //  Date date = formatter.parse(req.getParameter("origionalDate"));
		//read form data from form page
		String FirstName=req.getParameter("FirstName");
		String LastName=req.getParameter("LastName");
		String DateOfBirth=req.getParameter("(dob");
		String Email=req.getParameter("eid");
		String UserName=req.getParameter("userName");
		String Password=req.getParameter("Password");
	
	//get PrintWriter  object
	PrintWriter pw=res.getWriter();
	//set Content Type
	res.setContentType("text/html");
	//write jdbc code
//set value to parameter of the query
	ps.setString(1,FirstName);
	ps.setString(2,LastName);
	ps.setString(3,DateOfBirth);
	ps.setString(4,Email);
	ps.setString(5,UserName);
	ps.setString(6,Password);
result=ps.executeUpdate();
pw.println(result);
if(result==0) {
	pw.println("<h1 style='color:blue'>user not registered successfully</h1>");
	System.out.println("user not registered successfully");
}
else {
	System.out.println(" user registered successfully");
	pw.println("<h1 style='color:blue'>user  registered successfully</h1>");

}
	
	//close  stream object
	pw.close();
	


}//try
	catch(Exception e)
	{
		e.printStackTrace();
		
	}
	
}//doGet(-,-)
public void doPost(HttpServletRequest req,HttpServletResponse res )throws ServletException,IOException
{
	doGet(req,res);
	
}
public void destroy()
{
	//close jdbc  objects
	try {
		if(ps!=null)
			ps.close();
	}
	catch(SQLException se)
	
	{
	se.printStackTrace();	
	}
	try
	{
		if(con!=null)
			con.close();
	}
	catch(SQLException  se)
	{
		se.printStackTrace();
		
	}
	
	

}
	
	}