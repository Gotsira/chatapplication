package users;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * This class is a table form in the database. This table collect all user
 * detail.
 * 
 * @author Sirasath Piyapootinun
 *
 */
@DatabaseTable(tableName = "Accounts")
public class Accounts {

	@DatabaseField(id = true)
	private String username;
	@DatabaseField
	private String password;
	@DatabaseField
	private String image;

	/**
	 * No arg constructor for ORMLite.
	 */
	public Accounts() {
		// ORMLite needs a no-arg constructor
	}

	/**
	 * Method for creating a new account.
	 * 
	 * @param username
	 *            is the user name of the user.
	 * @param password
	 *            is the password of the user.
	 */
	public Accounts(String username, String password) {
		this.username = username;
		this.password = password;
		this.image = "";
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
	 * @param username
	 *            is the user name of the user to be set.
	 */
	public void setName(String username) {
		this.username = username;
	}

	/**
	 * Gets the profile image of the user in String.
	 * 
	 * @return the profile picture of the user in String.
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Sets the profile image of the user in String.
	 * 
	 * @param image
	 *            is the profile image of the user to be set in String.
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * Gets the password of the user.
	 * 
	 * @return the password of the user.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the user.
	 * 
	 * @param password
	 *            is the password of the user to be set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}