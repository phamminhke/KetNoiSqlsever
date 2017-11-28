package Conn;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnectionSQLServer {
	// kết nối vào SQL Server
	// (Sử dụng thư viện SQLJDBC)
	// Driver and URL
	private static final String DRIVER_JDBC = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String URL_DB = "jdbc:sqlserver://localhost:1433; instance=MSSQLSERVER; databaseName=GuestBook; user=sa; password=123;";

	static Statement statement = null;

	public static Connection getDBConnectionSQLServer() {
		Connection conn = null;
		try {
			Class.forName(DRIVER_JDBC);
			conn = DriverManager.getConnection(URL_DB); // Kết nối SQLServer
			System.out.println("Connected successfully ...");
			return conn;
		} catch (Exception e) {
			System.out.println("Error connection " + e);
		}
		
		return conn;
	}

	// hàm trả danh sách lưu trong ResultSet thông qua câu truy vấn str
	public static ResultSet ExecuteQueryResultSet(Connection conn, String str) {
		ResultSet rs = null;
		try {
			statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = statement.executeQuery(str);
		} catch (SQLException ex) {
			Logger.getLogger(DBConnectionSQLServer.class.getName()).log(Level.SEVERE, null, ex);
		} 
		return rs;
	}

	// Hàm thực hiện lệnh trả về true hoặc false
	public static boolean MyExcuteUpdateNonQuery(Connection conn, CallableStatement stmt) {
		boolean f = false;
		try {
			stmt.executeUpdate();
			f = true;
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (conn != null || stmt != null) {
					conn.close();
				}
			} catch (SQLException se) {
			} // do nothing
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return f;
	}

}