package users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.ConnectionImpl;

public class DisplayFriends {
	private String username;
	private ConnectionImpl con = null;
	private PreparedStatement stmt = null;
	private ResultSet result = null;
	private ArrayList<String> friends = new ArrayList<String>();

	public DisplayFriends(String username) throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		con = (ConnectionImpl) DriverManager.getConnection("jdbc:mysql://35.186.149.50:3306/projectdb?useSSL=false", "root", "mysqlpassword");
		this.username = username;
	}
	
	public ArrayList<String> display() throws SQLException {
		stmt = con.prepareStatement("SELECT `username` FROM `friendsList` WHERE `username` = ?");
		stmt.setString(1, username);
		result = stmt.executeQuery();
		while(result.next()) {
			friends.add(con.getProperties().get("friendUser") + "");
		}
		return friends;
	}
	
	public static void main(String[] args) throws Exception {
		DisplayFriends d = new DisplayFriends("got");
		ArrayList<String> l = d.display();
		for(int i = 0; i < l.size(); i++) {
			System.out.println(l.get(i));
		}
	}
}
