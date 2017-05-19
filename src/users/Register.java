package users;

import java.sql.*;

public class Register {

	private String username;
	private String password;
	private Connection con = null;
	private PreparedStatement stmt = null;
	private ResultSet result = null;

	public Register(String username, String password) throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		con = DriverManager.getConnection("jdbc:mysql://35.186.149.50:3306/projectdb?useSSL=false", "root", "mysqlpassword");
		this.username = username;
		this.password = password;
	}

	public boolean check() throws SQLException {
		stmt = con.prepareStatement("SELECT `username` FROM `accounts` WHERE `username` = ?");
		stmt.setString(1, username);
		result = stmt.executeQuery();
		if (result.next()) {
			return true;
		}
		return false;
	}

	public void add() throws SQLException {
		stmt = con.prepareStatement("INSERT into accounts VALUES(?, ?)");
		stmt.setString(1, username);
		stmt.setString(2, password);
		stmt.executeUpdate();
	}
}
