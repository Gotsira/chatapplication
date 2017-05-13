package users;

import java.sql.*;

public class DeleteUser {
	private String username;
	private Connection con = null;
	private PreparedStatement stmt = null;
	private ResultSet result = null;
		
	public DeleteUser(String username) throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projectdb", "root", "mysqlpassword");
		this.username = username;
	}
	
	public void delete() throws SQLException {
		stmt = con.prepareStatement("DELETE FROM `accounts` WHERE `username` = ?");
		stmt.setString(1, username);
		stmt.executeUpdate();
	}
	
//	public static void main(String[] args) throws Exception {
//		DeleteUser d = new DeleteUser("afdaf");
//		d.delete();
//	}
}
