package users;

import java.sql.*;

public class DeleteFriend {
	private String username;
	private Connection con = null;
	private PreparedStatement stmt = null;
	private ResultSet result = null;
	private String friendUser;

	public DeleteFriend(String username, String friendUser) throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		con = DriverManager.getConnection("jdbc:mysql://35.186.149.50:3306/projectdb?useSSL=false", "root", "mysqlpassword");
		this.username = username;
		this.friendUser = friendUser;
	}

	public boolean check() throws SQLException {
		stmt = con.prepareStatement(
				"SELECT `username`, `friendsUser` FROM `FriendsList` WHERE `username` = ? AND `friendsUser` = ?");
		stmt.setString(1, username);
		stmt.setString(2, friendUser);
		result = stmt.executeQuery();
		if (result.next()) {
			return true;
		}
		return false;
	}

	public boolean exist() throws SQLException {
		stmt = con.prepareStatement("SELECT `username` FROM `Accounts` WHERE `username` = ?");
		stmt.setString(1, friendUser);
		result = stmt.executeQuery();
		if (result.next()) {
			return true;
		}
		return false;
	}

	public void add() throws SQLException {
		stmt = con.prepareStatement("DELETE FROM `FriendsList` WHERE `username` = ? AND `friendsUser` = ?");
		stmt.setString(1, username);
		stmt.setString(2, friendUser);
		stmt.executeUpdate();
		stmt.setString(1, friendUser);
		stmt.setString(2, username);
		stmt.executeUpdate();
	}
}
