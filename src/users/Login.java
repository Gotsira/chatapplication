package users;

import java.sql.*;

/**
 * This class establishes a connection with the database and checks whether the
 * user name and the password of the user matches or not.
 * 
 * @author Sirasath Piyapootinun
 *
 */
public class Login {
	private String username;
	private String password;
	private Connection con = null;
	private PreparedStatement stmt = null;
	private ResultSet result = null;

	/**
	 * Constructor for establishing a connection with the database and also
	 * initializes the class
	 * 
	 * @param username
	 *            is the user name of the user.
	 * @param image
	 *            is the password of the user.
	 */
	public Login(String username, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://35.186.149.50:3306/projectdb?useSSL=false", "root",
					"mysqlpassword");
		} catch (Exception e) {
			// do nothing
		}
		this.username = username;
		this.password = password;
	}

	/**
	 * Checks whether the user name and password of the user matches or not.
	 * 
	 * @return true if the user name and password of the user matches, and false
	 *         otherwise.
	 */
	public boolean matches() {
		try {
			stmt = con.prepareStatement(
					"SELECT `username`, `password` FROM `Accounts` WHERE `username` = ? AND`password` = ?");
			stmt.setString(1, username);
			stmt.setString(2, password);
			result = stmt.executeQuery();
			if (result.next()) {
				return true;
			}
		} catch (Exception e) {
			// do nothing
		}
		return false;
	}
}
