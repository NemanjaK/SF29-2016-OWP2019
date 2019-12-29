package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private static final String DATABASE = "localhost:3306/bioskop";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "root";

	private static Connection connection;

	public static void open() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://" + DATABASE + "?useSSL=false", USER_NAME, PASSWORD);
			System.out.println("Connected");
		} catch (Exception ex) {
			
			System.out.println("Faild to connect");
			ex.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return connection;
	}

	public static void close() {
		try {
			connection.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
