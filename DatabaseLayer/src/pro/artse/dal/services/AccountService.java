package pro.artse.dal.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import pro.artse.dal.database.ConnectionPool;
import pro.artse.dal.database.ServiceUtil;
import pro.artse.dal.dto.AccountDTO;
import pro.artse.dal.dto.AccountRole;
import pro.artse.dal.errorhandling.DbResultMessage;
import pro.artse.dal.errorhandling.DbStatus;
import pro.artse.dal.errorhandling.ErrorHandler;
import pro.artse.dal.util.Security;
import pro.artse.dal.util.Validator;

public class AccountService implements IAccountService {
	@Override
	public DbResultMessage<Boolean> register(AccountDTO account, String password) {
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement ps = null;

		if (Validator.isInvalidAccount(account))
			return new DbResultMessage<Boolean>(DbStatus.INVALID_DATA);

		byte[] passwordHash = Security.computePasswordHash(password);

		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.checkOut();
			ps = ServiceUtil.prepareStatement(connection, AccountSqlExtension.SQL_INSERT, false,
					AccountSqlExtension.mapForInsert(account, passwordHash));
			ps.executeUpdate();
			if (!ServiceUtil.isSuccess(ps))
				return new DbResultMessage<Boolean>(false, DbStatus.UNKNOWN_ERROR, "Registration failed.");

			return new DbResultMessage<Boolean>(true);
		} catch (Exception ex) {
			return ErrorHandler.handle(ex);
		} finally {
			ServiceUtil.finish(connectionPool, connection, ps);
		}
	}

	@Override
	public DbResultMessage<AccountDTO> login(String username, String password) {
		AccountDTO dbAccount = null;
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement ps = null;

		if (Validator.areNullOrEmpty(username, password))
			return new DbResultMessage<AccountDTO>(DbStatus.INVALID_DATA, "Username or password are invalid");

		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.checkOut();
			ps = ServiceUtil.prepareStatement(connection, AccountSqlExtension.SQL_SELECT_WITH_USERNAME, username);
			ResultSet rs = ps.executeQuery();

			if (!rs.next())
				return new DbResultMessage<AccountDTO>(DbStatus.NOT_FOUND);
			if (!Arrays.equals(Security.computePasswordHash(password), rs.getBytes("password")))
				return new DbResultMessage<AccountDTO>(null, DbStatus.SUCCESS, "Passwords do not match.");

			dbAccount = AccountSqlExtension.mapFromSelect(rs);
			return new DbResultMessage<AccountDTO>(dbAccount);
		} catch (Exception ex) {
			return ErrorHandler.handle(ex);
		} finally {
			ServiceUtil.finish(connectionPool, connection, ps);
		}
	}

	private final static class AccountSqlExtension {
		public static final String SQL_SELECT_WITH_USERNAME = "SELECT * FROM accounts WHERE username=%s";
		public static final String SQL_INSERT = "INSERT INTO accounts (name, lastname, username, password, email, country, address, accountrole) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		public static Object[] mapForInsert(AccountDTO account, byte[] password) {
			return new Object[] { account.getName(), account.getLastName(), account.getUsername(), password,
					account.getEmail(), account.getCountry(), account.getAddress(), account.getRole().name() };
		}

		public static AccountDTO mapFromSelect(ResultSet rs) throws SQLException {
			return new AccountDTO(rs.getInt("accountId"), rs.getString("name"), rs.getString("lastname"),
					rs.getString("email"), rs.getString("username"), rs.getString("country"), rs.getString("address"),
					AccountRole.valueOf(rs.getString("accountRole")));
		}
	}
}
