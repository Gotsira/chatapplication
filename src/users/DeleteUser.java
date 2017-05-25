package users;

import java.sql.*;

public class DeleteUser {
	private String username;
	private Connection con = null;
	private PreparedStatement stmt = null;

	public DeleteUser(String username) throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		con = DriverManager.getConnection("jdbc:mysql://35.186.149.50:3306/projectdb?useSSL=false", "root",
				"mysqlpassword");
		this.username = username;
	}

	public void delete() throws SQLException {
		stmt = con.prepareStatement("DELETE FROM `Accounts` WHERE `username` = ?");
		stmt.setString(1, username);
		stmt.executeUpdate();
	}
}
