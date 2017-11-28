package BALayer;

import Conn.*;
import Model.*;
import Conn.DBConnectionSQLServer;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.SQLException;


public class DBGuestBook {

	CallableStatement stmt = null;

	public DBGuestBook() {
		//
	}
	
	
	public Boolean AddGuestBook(String Name, String Message) throws SQLException {
		Connection conn = DBConnectionSQLServer.getDBConnectionSQLServer();
		try {
			String call = "{call dbo.ThemGuestBook(?,?)}";
			CallableStatement stmt = conn.prepareCall(call);
			stmt.setString(1, Name);
			stmt.setString(2, Message);

			return DBConnectionSQLServer.MyExcuteUpdateNonQuery(conn, stmt);
		} catch (Exception e) {
			return false;
		}finally {
			conn.close();
		}
	}
	
	public Boolean UpdateGuestBook(int ID, String Name, String Message) throws SQLException {
		Connection conn = DBConnectionSQLServer.getDBConnectionSQLServer();
		try {
			String call = "{call dbo.CapNhatGuestBook(?,?,?)}";
			CallableStatement stmt = conn.prepareCall(call);
			stmt.setInt(1, ID);
			stmt.setString(2, Name);
			stmt.setString(3, Message);

			return DBConnectionSQLServer.MyExcuteUpdateNonQuery(conn, stmt);
		} catch (Exception e) {
			return false;
		}finally {
			conn.close();
		}
	}
	
	public Boolean DeleteGuestBook(int ID) throws SQLException {
		Connection conn = DBConnectionSQLServer.getDBConnectionSQLServer();
		try {
			String call = "{call dbo.XoaGuestBook(?)}";
			CallableStatement stmt = conn.prepareCall(call);
			stmt.setInt(1, ID);

			return DBConnectionSQLServer.MyExcuteUpdateNonQuery(conn, stmt);
		} catch (Exception e) {
			return false;
		}finally {
			conn.close();
		}
	}
}