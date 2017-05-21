package users;

import java.awt.image.BufferedImage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

public class GetPicture {
	private String username;
	private ConnectionSource con = null;
	private PreparedStatement stmt = null;
	private ResultSet result = null;
	private Dao<Accounts, String> accountDao = null;

	public GetPicture(String username) throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		con = new JdbcConnectionSource("jdbc:mysql://35.186.149.50:3306/projectdb?useSSL=false", "root",
				"mysqlpassword");
		accountDao = DaoManager.createDao(con, Accounts.class);
		this.username = username;
	}
	
	public String get() throws SQLException {
		List<Accounts> accounts = accountDao.queryForAll();
		for(Accounts ac: accounts){
			if(ac.getName().equals(username)){
				return ac.getImage();
			}
		}
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		GetPicture p = new GetPicture("got");
		System.out.println(p.get());
	}
}
