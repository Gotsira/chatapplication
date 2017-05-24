package users;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

public class EditPicture {
	private String username;
	private String image;
	private ConnectionSource con = null;
	private Dao<Accounts, String> accountDao = null;

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

	public static void main(String[] args) throws Exception {
		EditPicture e = new EditPicture("got", "");
		e.setImage();
	}
}
