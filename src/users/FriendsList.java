package users;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * This class is a table form in the database. This table collects the friends
 * list of the user.
 * 
 * @author Sirasath Piyapootinun
 *
 */
@DatabaseTable(tableName = "FriendsList")
public class FriendsList {

	@DatabaseField
	private String username;
	@DatabaseField
	private String friendsUser;

	/**
	 * No arg constructor for ORMLite.
	 */
	public FriendsList() {
		// ORMLite needs a no-arg constructor
	}

	/**
	 * Method for creating a new friends list.
	 * 
	 * @param username
	 *            is the user name of the user.
	 * @param password
	 *            is the user name of the friend of the user.
	 */
	public FriendsList(String name, String friends) {
		this.username = name;
		this.friendsUser = friends;
	}

	/**
	 * Gets the user name of the user.
	 * 
	 * @return the user name of the user.
	 */
	public String getName() {
		return username;
	}

	/**
	 * Sets the user name of the user.
	 * 
	 * @param name
	 *            is the user name of the user to be set.
	 */
	public void setName(String name) {
		this.username = name;
	}

	/**
	 * Get the user name of the user's friend.
	 * 
	 * @return the user name of the user's friend.
	 */
	public String getFriends() {
		return friendsUser;
	}

	/**
	 * Sets the user name of the user's friend.
	 * 
	 * @param friends
	 *            is the user name of the user's friend.
	 */
	public void setFriends(String friends) {
		this.friendsUser = friends;
	}
}