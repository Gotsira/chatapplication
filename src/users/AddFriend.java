package users;

import java.sql.*;

public class AddFriend {
	private String username;
	private Connection con = null;
	private PreparedStatement stmt = null;
	private ResultSet result = null;
	private String friendUser;

	public AddFriend(String username, String friendUser) throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projectdb", "root", "mysqlpassword");
		this.username = username;
		this.friendUser = friendUser;
	}

	public boolean check() throws SQLException {
		stmt = con.prepareStatement("SELECT `user`, `friendUser` FROM `friendsList` WHERE `user` = ? AND `friendUser` = ?");
		stmt.setString(1, username);
		stmt.setString(2, friendUser);
		result = stmt.executeQuery();
		if(result.next()) {
			return true;
		}
		return false;
	}
	
	public boolean exist() throws SQLException {
		stmt = con.prepareStatement("SELECT `username` FROM `accounts` WHERE `username` = ?");
		stmt.setString(1, friendUser);
		result = stmt.executeQuery();
		if(result.next()) {
			return true;
		}
		return false;
	}

	public void add() throws SQLException {
		stmt = con.prepareStatement("INSERT into friendsList VALUES(?, ?)");
		stmt.setString(1, username);
		stmt.setString(2, friendUser);
		stmt.executeUpdate();
		stmt.setString(1, friendUser);
		stmt.setString(2, username);
		stmt.executeUpdate();
	}
}
