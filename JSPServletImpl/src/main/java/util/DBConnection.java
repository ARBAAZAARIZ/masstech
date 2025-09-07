package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private static final String url="jdbc:mysql://localhost:3306/augbatch";
	private static final String username="root";
	private static final String password="";
	public static Connection getConnection()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url,username,password);
		}
		catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
	}
}
