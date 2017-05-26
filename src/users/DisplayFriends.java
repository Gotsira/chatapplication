package users;

import java.util.ArrayList;
import java.util.List;
import com.j256.ormlite.dao.*;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

/**
 * This class establishes a connection with the database and displays the list
 * of friends the user has.
 * 
 * @author Sirasath Piyapootinun
 *
 */
public class DisplayFriends {
	private String username;
	private ConnectionSource con = null;
	private ArrayList<String> friends = new ArrayList<String>();
	private Dao<FriendsList, String> friendDao = null;

	/**
	 * Constructor for establishing a connection with the database and also
	 * initializes the class
	 * 
	 * @param username
	 *            is the user name of the user.
	 */
	public DisplayFriends(String username) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = new JdbcConnectionSource("jdbc:mysql://35.186.149.50:3306/projectdb?useSSL=false", "root",
					"mysqlpassword");
			friendDao = DaoManager.createDao(con, FriendsList.class);
		} catch (Exception e) {
			// do nothing
		}
		this.username = username;
	}

	/**
	 * Returns the list of friends the user has.
	 * 
	 * @return the list of friends the user has.
	 */
	public ArrayList<String> display() {
		List<FriendsList> list = null;
		try {
			list = friendDao.queryForAll();
		} catch (Exception e) {
			// do nothing
		}
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName().equals(username)) {
				friends.add(list.get(i).getFriends());
			}
		}
		return friends;
	}
}
