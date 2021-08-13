package pro.artse.dal.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pro.artse.dal.database.ConnectionPool;
import pro.artse.dal.database.ServiceUtil;
import pro.artse.dal.dto.AccountDTO;
import pro.artse.dal.dto.AccountRole;

public class Test {

	public static void main(String[] args) {
		AccountDTO account = new AccountDTO();
		account.setAddress("Banja Luka, Koste Jarica 35E");
		account.setCountry("BiH");
		account.setUsername("makica");
		account.setEmail("marija.vanja.novakovic@gmail.com");
		account.setPassword("marija");
		account.setName("Marija");
		account.setLastName("Novakovic");
		account.setRole(AccountRole.Passenger);

		System.out.println(account.getRole());
		System.out.println(insert(account));
		ArrayList<AccountDTO> accounts = selectAll();
		for (AccountDTO acc : accounts)
			System.out.println(acc.getUsername()+acc.getRole());
	}

	private static ConnectionPool connectionPool = ConnectionPool.getInstance();
	private static final String SQL_SELECT_ALL = "SELECT * FROM account";
	private static final String SQL_INSERT = "INSERT INTO account (name, lastname, username, password, email, country, address, accountrole) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	public static ArrayList<AccountDTO> selectAll() {
		ArrayList<AccountDTO> retVal = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ServiceUtil.prepareStatement(connection, SQL_SELECT_ALL, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AccountDTO account = new AccountDTO();
				account.setAccountId(rs.getInt("accountId"));
				account.setUsername(rs.getString("username"));
				account.setEmail(rs.getString("email"));
				account.setRole(AccountRole.valueOf(rs.getString("accountRole")));
				retVal.add(account);
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}

	public static boolean insert(AccountDTO account) {
		boolean result = false;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = { account.getName(), account.getLastName(), account.getUsername(),
				Security.computePasswordHash(account.getPassword()), account.getEmail(), account.getCountry(),
				account.getAddress(), account.getRole().name() };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = ServiceUtil.prepareStatement(connection, SQL_INSERT, true, values);
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			if (pstmt.getUpdateCount() > 0) {
				result = true;
			}
			if (generatedKeys.next())
				account.setAccountId(generatedKeys.getInt(1));
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
}
