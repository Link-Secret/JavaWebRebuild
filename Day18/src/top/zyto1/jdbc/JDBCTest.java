package top.zyto1.jdbc;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Test;



public class JDBCTest {
	
	@Test
	public void testStatement() throws Exception{
		Connection conn = null;
		Statement statement = null;
		try {
			conn = getConnection();
			statement = conn.createStatement();
			String sql = "";
			statement.execute(sql);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				conn.close();
			}
	
		}
	}
	
	@Test
	public void testGetConnection2() throws Exception{
		System.out.println(getConnection2());
	}
	
	public Connection getConnection2() throws Exception{
		Properties properties = new Properties();
		InputStream in =
				getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		
		properties.load(in);
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		String jdbcUrl = properties.getProperty("jdbcUrl");
		String DriverClass = properties.getProperty("driver");
		
		Class.forName(DriverClass);
		Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
		return connection;
	}
	
	
	@Test
	public void testDriverManager() throws Exception{
		String jdbcUrl = "jdbc:mysql://localhost:3306/mldn";
		String DriverClass = "com.mysql.jdbc.Driver";
		String user = "root";
		String password = "admin";
		
		//注册driver
	    Class.forName(DriverClass);
	    
	    Connection connection = 
	    		DriverManager.getConnection(jdbcUrl, user, password);
	    System.out.println(connection);
	}

	@Test
	public void testDriver() throws SQLException {
		Driver driver = new com.mysql.jdbc.Driver();
		String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/mldn";
		Properties properties = new Properties();
		properties.put("user", "root");
		properties.put("password", "admin");
		Connection conn = 
				 driver.connect(jdbcUrl, properties);
		System.out.println(conn);
	}
	
	public Connection getConnection() throws SQLException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		String driverClass = null;
		String jdbcUrl = null;
		String user = null;
		String password = null;
		
		InputStream in =
				getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(in);
		driverClass = properties.getProperty("driver");
		jdbcUrl = properties.getProperty("jdbcUrl");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		
		Driver driver = 
		  (Driver) Class.forName(driverClass).newInstance();
		Properties info = new Properties();
		info.put("user", user);
		info.put("password", password);
		
		Connection connection = driver.connect(jdbcUrl, info);
		
		return connection;
	}
	
	@Test
	public void testGetConnection () throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException{
		System.out.println(getConnection());
	}

}


   
