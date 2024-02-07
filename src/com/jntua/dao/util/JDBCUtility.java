package com.jntua.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCUtility {
	private static Connection con;
	static
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jntuchat","root","2174");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection()
	{
		if(con==null)
		{
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jntuchat","root","2174");
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return con;
	}
	public static void closeResultSet(ResultSet rs)
	{
		try {
			
			if(rs!=null)
			{
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void closeStatement(Statement st)
	{
		try {
			
			if(st!=null)
			{
				st.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
