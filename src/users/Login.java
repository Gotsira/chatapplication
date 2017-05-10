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
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "");
		this.username = username;
		this.password = password;
	}
	
	public boolean matches() throws Exception {
		stmt = con.prepareStatement("SELECT `username`, `password` FROM `passes` WHERE `username` = ? AND`password` = ?");
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
	
	public void add() throws SQLException {
		stmt =  con.prepareStatement("INSERT into passes VALUES(?, ?)");
		stmt.setString(1, username);
		stmt.setString(2, password);
		stmt.executeUpdate();
	}
	
//	public static void main(String[] args) throws Exception {
//		Login l = new Login("ad", "asda" );
//		l.add();
//				
//	}
	
}
