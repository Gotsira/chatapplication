package users;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "FriendsList")
public class FriendsList {

	@DatabaseField
	private String username;
	@DatabaseField
	private String friendsUser;

	public FriendsList() {
		// ORMLite needs a no-arg constructor
	}

	public FriendsList(String name, String friends) {
		this.username = name;
		this.friendsUser = friends;
	}

	public String getName() {
		return username;
	}

	public void setName(String name) {
		this.username = name;
	}

	public String getFriends() {
		return friendsUser;
	}

	public void setPassword(String password) {
		this.friendsUser = password;
	}
}