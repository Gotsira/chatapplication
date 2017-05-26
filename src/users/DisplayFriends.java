package users;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

public class DisplayFriends {
	private String username;
	private ConnectionSource con = null;
	private ArrayList<String> friends = new ArrayList<String>();
	private Dao<FriendsList, String> friendDao = null;

	public DisplayFriends(String username) throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		con = new JdbcConnectionSource("jdbc:mysql://35.186.149.50:3306/projectdb?useSSL=false", "root",
				"mysqlpassword");
		friendDao = DaoManager.createDao(con, FriendsList.class);
		this.username = username;
	}

	public ArrayList<String> display() throws SQLException {
		List<FriendsList> list = friendDao.queryForAll();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName().equals(username)) {
				friends.add(list.get(i).getFriends());
			}
		}
		return friends;
	}

	// public static void main(String[] args) throws Exception {
	// DisplayFriends d = new DisplayFriends("got");
	// ArrayList<String> l = d.display();
	// for(int i = 0; i < l.size(); i++) {
	// System.out.println(l.get(i));
	// }
	// }
}
