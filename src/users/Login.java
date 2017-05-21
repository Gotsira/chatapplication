package users;

import java.sql.*;

public class Login {
	private String username;
	private String password;
	private Connection con = null;
	private PreparedStatement stmt = null;
	private ResultSet result = null;
		
	public Login(String username, String password) throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		con = DriverManager.getConnection("jdbc:mysql://35.186.149.50:3306/projectdb?useSSL=false", "root", "mysqlpassword");
		this.username = username;
		this.password = password;
	}
	
	public boolean matches() throws Exception {
		stmt = con.prepareStatement("SELECT `username`, `password` FROM `Accounts` WHERE `username` = ? AND`password` = ?");
		stmt.setString(1, username);
		stmt.setString(2, password);
		result = stmt.executeQuery();
		if(result.next()) {
			return true;
		}
		else {
			return false;
		}
	}
	
//	public static void main(String[] args) throws Exception {
//		Login l = new Login("got", "12345" );
//		System.out.println(l.matches());
//				
//	}
	
}
