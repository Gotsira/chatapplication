package users;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

/**
 * This class establishes a connection with the database and edits the picture
 * of the user in String.
 * 
 * @author Sirasath Piyapootinun
 *
 */
public class EditPicture {
	private String username;
	private String image;
	private ConnectionSource con = null;
	private Dao<Accounts, String> accountDao = null;

	/**
	 * Constructor for establishing a connection with the database and also
	 * initializes the class
	 * 
	 * @param username
	 *            is the user name of the user.
	 * @param image
	 *            is the image of the user to be edited.
	 */
	public EditPicture(String username, String image) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = new JdbcConnectionSource("jdbc:mysql://35.186.149.50:3306/projectdb?useSSL=false", "root",
					"mysqlpassword");
			accountDao = DaoManager.createDao(con, Accounts.class);
		} catch (Exception e) {
			// do nothing
		}
		this.username = username;
		this.image = image;
	}

	/**
	 * Sets the image of the user in String on the database.
	 */
	public void setImage() {
		List<Accounts> accounts;
		try {
			accounts = accountDao.queryForAll();
			for (Accounts ac : accounts) {
				if (ac.getName().equals(username)) {
					ac.setImage(image);
					accountDao.update(ac);
				}
			}
		} catch (SQLException e) {
			// do nothing
		}
	}
}
