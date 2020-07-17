package utility;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class ConnectionManager
{
	public static Connection getConnection() throws ClassNotFoundException, SQLException, IOException
	{
		Properties prop=null;
		prop=loadPropertiesFile();
		final String url=prop.getProperty("url");
		final String driver=prop.getProperty("driver");
		Class .forName(driver);
		Connection ob=null;
		ob=DriverManager.getConnection(url);
		if(ob!=null)
			System.out.println("Established");
		return ob;
	}
	public static Properties loadPropertiesFile() throws IOException
	{
		Properties pro=new Properties();
		InputStream in=ConnectionManager.class.getClassLoader().getResourceAsStream("jdbc.properties");
		pro.load(in);
		in.close();
		return pro;
	}
}