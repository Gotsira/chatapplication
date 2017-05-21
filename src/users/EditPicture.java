package users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

public class EditPicture {
	private String username;
	private String image;
	private ConnectionSource con = null;
	private PreparedStatement stmt = null;
	private ResultSet result = null;
	private Dao<Accounts, String> accountDao = null;

	public EditPicture(String username, String image) throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		con = new JdbcConnectionSource("jdbc:mysql://35.186.149.50:3306/projectdb?useSSL=false", "root",
				"mysqlpassword");
		accountDao = DaoManager.createDao(con, Accounts.class);
		this.username = username;
		this.image = image;
	}

	public void setImage() throws SQLException {
		List<Accounts> accounts = accountDao.queryForAll();
//		for (int i = 0; i < accounts.size(); i++) {
//			if(accounts.get(i).getName() == username) {
//				
//			}
//		}
		for(Accounts ac: accounts){
			if(ac.getName().equals(username)){
				ac.setImage(image);
				accountDao.update(ac);
			}
		}
	}

}
