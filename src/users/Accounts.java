package users;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Accounts")
public class Accounts {

	@DatabaseField(id = true)
	private String username;
	@DatabaseField
	private String password;
	@DatabaseField
	private String image;

	public Accounts() {
		// ORMLite needs a no-arg constructor
	}

	public Accounts(String username, String password) {
		this.username = username;
		this.password = password;
		this.image = null;
	}
	
	public String getName() {
		return username;
	}

	public void setName(String username) {
		this.username = username;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}