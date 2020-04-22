package com.nt.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLogin extends HttpServlet{
	//public class UserRegistration extends HttpServlet {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int result =0;
		int count=0;
		private static final String QUERY="SELECT * FROM USER_LIST WHERE  USERNAME=? AND  PASSWORD=?";
		public void init() {
			try {
				
				//get access to servlet context object
				
				
				ServletContext sc=getServletContext();
				//read context param values  from web.xml

				String s1=sc.getInitParameter("driver");
				
				String s2=sc.getInitParameter("dburl");
				String s3=sc.getInitParameter("dbuser");
				String s4=sc.getInitParameter("dbpwd");
				
		//create jdbc con  object
				
					Class.forName(s1);

				
				con=DriverManager.getConnection(s2,s3,s4);
				//create jdbc prepared statement object

				//ps=con.prepareStatement(QUERY);
				 }//try
			catch(SQLException | ClassNotFoundException se) {
				se.printStackTrace();
			}
				
			
				
			}//init



		public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
		{
			try {
			//read form data from formpage
			String UserName=req.getParameter("userName");
			String Password=req.getParameter("Password");
			//get printwriter object
			PrintWriter pw=res.getWriter();
			//set Response content Type
			res.setContentType("text/html");
			ps=con.prepareStatement(QUERY);
			//set values to parametr of the query
			ps.setString(1,UserName);
			ps.setString(2,Password);
			//execute the sql querry
			//rs=ps.executeQuery(QUERY);
			
			rs =ps.executeQuery();

			if(rs.next()) {
				//count=rs.getInt(1);
				pw.println("<style>marquee{background-color:green}</style><h1><marquee>preeti web Page...cute madam</marquee></h1>");
				
				pw.println("<br><h5 style='color:red'> User First Name::</h5>"+rs.getString(1));//
				pw.println("<br> <h5 style='color:red'>User Last Name::</h5>"+rs.getString(2));
			
				
				pw.println("<br><h5 style='color:red'>User DateOfBirth::</h5>"+rs.getString(3));
				
				pw.println("<br><h5 style='color:red'>User Email-Id::</h5>"+rs.getString(4));
				pw.println("<br> <h5 style='color:red'>User UserName::</h5>"+rs.getString(5));
				
				pw.println("<br> <h5 style='color:red'>User Password::</h5>"+rs.getString(6));
				
				pw.println("<br><h5 style='color:green'>record found and displayed</h5>");
				
				
				
			}
			//if(count==0)
				//pw.println("record not found");
			else {
				//pw.println("record found and displayed");
				pw.println("record not found");
			}
			
		}//try
catch(Exception e) {
	e.printStackTrace();
}
	
}//doget
		
		public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
		{
			
			doGet(req,res);
			
		}
		public void destroy() {
			//close jdbc objects
			try {
				if(ps!=null)
					ps.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			}
		}

	//}


