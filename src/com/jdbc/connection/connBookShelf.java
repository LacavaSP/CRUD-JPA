package com.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connBookShelf {
	public static Connection connectione () throws ClassNotFoundException {
		String url = "jdbc:mysql://localhost:3306/databasejpa";
		String user = "root";
		String password = "261203";
		
		try {
	
			return DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void desconnect(Connection connection) throws SQLException {
		if(connection!=null) {
		connection.close();
		}
	}
	public  static void desconnect(Connection connection,Statement stmt) throws SQLException {
	if(stmt!=null) {
		stmt.close();
	}
	desconnect(connection);
	}
	public  static void desconnect(Connection connection,Statement stmt,ResultSet rs) throws SQLException {
		if(rs!=null) {
			rs.close();
		}
		desconnect(connection,stmt);
	}
}
