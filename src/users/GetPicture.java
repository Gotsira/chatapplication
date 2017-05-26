package users;

import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

public class GetPicture {
	private String username;
	private ConnectionSource con = null;
	private Dao<Accounts, String> accountDao = null;

	public GetPicture(String username) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = new JdbcConnectionSource("jdbc:mysql://35.186.149.50:3306/projectdb?useSSL=false", "root",
					"mysqlpassword");
			accountDao = DaoManager.createDao(con, Accounts.class);
		} catch (Exception e) {
			// do nothing
		}
		this.username = username;
	}

	public String get() {
		List<Accounts> accounts;
		try {
			accounts = accountDao.queryForAll();
			for (Accounts ac : accounts) {
				if (ac.getName().equals(username)) {
					return ac.getImage();
				}
			}
		} catch (Exception e) {
			// do nothing
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		GetPicture p = new GetPicture("got");
		System.out.println(p.get());
	}
}
