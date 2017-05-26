package users;

import java.sql.*;

/**
 * This class establishes a connection with the database and adds the user name
 * and password to the Accounts table in the database.
 * 
 * @author Sirasath Piyapootinun
 *
 */
public class Register {

	private String username;
	private String password;
	private Connection con = null;
	private PreparedStatement stmt = null;
	private ResultSet result = null;

	/**
	 * Constructor for establishing a connection with the database and also
	 * initializes the class.
	 * 
	 * @param username
	 *            is the user name of the user.
	 * @param password
	 *            is the pass word of the user.
	 */
	public Register(String username, String password) {
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
	 * Checks whether the user name is already used in the database or not.
	 * 
	 * @return true if the user name is already used in the database, and false
	 *         otherwise.
	 */
	public boolean check() {
		try {
			stmt = con.prepareStatement("SELECT `username` FROM `Accounts` WHERE `username` = ?");
			stmt.setString(1, username);
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
	 * Adds the user name and password to the database.
	 * 
	 */
	public void add() {
		try {
			stmt = con.prepareStatement("INSERT into Accounts VALUES(?, ?, ?)");
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, null);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// do nothing
		}
	}
}
