package users;

import java.sql.*;

/**
 * This class establishes a connection with the database and adds friend for the
 * user.
 * 
 * @author Sirasath Piyapootinun
 *
 */
public class AddFriend {
	private String username;
	private Connection con = null;
	private PreparedStatement stmt = null;
	private ResultSet result = null;
	private String friendUser;

	/**
	 * Constructor for establishing a connection with the database and also
	 * initializes the class
	 * 
	 * @param username
	 *            is the user name of the user.
	 * @param friendUser
	 *            is the user name of the user to be added.
	 */
	public AddFriend(String username, String friendUser) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://35.186.149.50:3306/projectdb?useSSL=false", "root",
					"mysqlpassword");
		} catch (Exception e) {
			// do nothing
		}
		this.username = username;
		this.friendUser = friendUser;
	}

	/**
	 * Checks whether the user is already a friend with the user who is about to
	 * be added.
	 * 
	 * @return true if they are already friends, and false otherwise.
	 */
	public boolean check() throws SQLException {
		try {
			stmt = con.prepareStatement(
					"SELECT `username`, `friendsUser` FROM `FriendsList` WHERE `username` = ? AND `friendsUser` = ?");
			stmt.setString(1, username);
			stmt.setString(2, friendUser);
			result = stmt.executeQuery();
			if (result.next()) {
				return true;
			}
		} catch (Exception e) {
			// do nothing
		}
		return false;
	}

	/**
	 * Checks if the user name to be added exists or not.
	 * 
	 * @return true if the user name exist, and false otherwise.
	 */
	public boolean exist() {
		try {
			stmt = con.prepareStatement("SELECT `username` FROM `Accounts` WHERE `username` = ?");
			stmt.setString(1, friendUser);
			result = stmt.executeQuery();
			if (result.next()) {
				return true;
			}
		} catch (Exception e) {
			// do nothing
		}
		return false;
	}

	/**
	 * Make the user and the friendUser friends.
	 * 
	 */
	public void add() throws SQLException {
		try {
			stmt = con.prepareStatement("INSERT into FriendsList VALUES(?, ?)");
			stmt.setString(1, username);
			stmt.setString(2, friendUser);
			stmt.executeUpdate();
			stmt.setString(1, friendUser);
			stmt.setString(2, username);
			stmt.executeUpdate();
		} catch (Exception e) {
			// do nothing
		}
	}
}
