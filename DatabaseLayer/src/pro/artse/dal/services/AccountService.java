package pro.artse.dal.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pro.artse.dal.database.ConnectionPool;
import pro.artse.dal.database.ServiceUtil;
import pro.artse.dal.dto.AccountDTO;
import pro.artse.dal.dto.AccountRole;
import pro.artse.dal.dto.UserDTO;
import pro.artse.dal.errorhandling.DbResultMessage;
import pro.artse.dal.errorhandling.DbStatus;
import pro.artse.dal.errorhandling.ErrorHandler;
import pro.artse.dal.util.Security;
import pro.artse.dal.util.Validator;

public class AccountService implements IAccountService {

	@Override
	public DbResultMessage<Boolean> register(UserDTO user, String password) {
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement ps = null;
		PreparedStatement insertUser = null;

		if (Validator.isInvalidUser(user))
			return new DbResultMessage<Boolean>(DbStatus.INVALID_DATA);

		byte[] passwordHash = Security.computePasswordHash(password);

		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.checkOut();

			// Check if a user with the specified username already exists
			PreparedStatement psExists = ServiceUtil.prepareStatement(connection,
					AccountSqlExtension.SQL_SELECT_WITH_USERNAME, user.getUsername());
			ResultSet rs = psExists.executeQuery();
			if (rs.next())
				return new DbResultMessage<Boolean>(DbStatus.EXISTS,
						"Account with username:" + user.getUsername() + " already exists.");
			psExists.close();

			// Insert into the parent table - account
			int accountId = 0;
			ps = ServiceUtil.prepareStatement(connection, AccountSqlExtension.SQL_INSERT, true,
					AccountSqlExtension.mapForInsertAccount(user, passwordHash));
			ps.executeUpdate();
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next())
				accountId = generatedKeys.getInt(1);
			if (!ServiceUtil.isSuccess(ps))
				return new DbResultMessage<Boolean>(false, DbStatus.UNKNOWN_ERROR, "Registration failed.");
			ps.close();

			// Save user data
			insertUser = ServiceUtil.prepareStatement(connection, AccountSqlExtension.SQL_INSERT_USER, false,
					AccountSqlExtension.mapForInsertUser(user, accountId));
			insertUser.executeUpdate();

			if (!ServiceUtil.isSuccess(insertUser))
				return new DbResultMessage<Boolean>(false, DbStatus.UNKNOWN_ERROR, "Registration failed.");

			return new DbResultMessage<Boolean>(true);
		} catch (Exception ex) {
			return ErrorHandler.handle(ex);
		} finally {
			ServiceUtil.finish(connectionPool, connection, insertUser);
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

	@Override
	public DbResultMessage<Boolean> addEmployee(AccountDTO account, String password) {
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement ps = null;

		if (Validator.areNullOrEmpty(account.getName(), account.getUsername(), account.getUsername())
				|| account.getRole() != AccountRole.Employee)
			return new DbResultMessage<Boolean>(DbStatus.INVALID_DATA);

		byte[] passwordHash = Security.computePasswordHash(password);

		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.checkOut();

			// Check if a user with the specified username already exists
			PreparedStatement psExists = ServiceUtil.prepareStatement(connection,
					AccountSqlExtension.SQL_SELECT_WITH_USERNAME, account.getUsername());
			ResultSet rs = psExists.executeQuery();
			if (rs.next())
				return new DbResultMessage<Boolean>(DbStatus.EXISTS,
						"Account with username:" + account.getUsername() + " already exists.");
			psExists.close();

			ps = ServiceUtil.prepareStatement(connection, AccountSqlExtension.SQL_INSERT, true,
					AccountSqlExtension.mapForInsertAccount(account, passwordHash));
			ps.executeUpdate();
			if (!ServiceUtil.isSuccess(ps))
				return new DbResultMessage<Boolean>(false, DbStatus.UNKNOWN_ERROR, "Adding employee failed.");

			return new DbResultMessage<Boolean>(true);
		} catch (Exception ex) {
			return ErrorHandler.handle(ex);
		} finally {
			ServiceUtil.finish(connectionPool, connection, ps);
		}
	}

	@Override
	public DbResultMessage<Boolean> updateEmployee(AccountDTO account, String password) {
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement ps = null;

		if (Validator.areNullOrEmpty(account.getName(), account.getUsername(), account.getUsername())
				|| account.getRole() != AccountRole.Employee)
			return new DbResultMessage<Boolean>(DbStatus.INVALID_DATA);

		byte[] passwordHash = Security.computePasswordHash(password);

		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.checkOut();

			// Check if a user with the specified username already exists
			PreparedStatement psExists = ServiceUtil.prepareStatement(connection,
					AccountSqlExtension.SQL_SELECT_WITH_USERNAME, account.getUsername());
			ResultSet rs = psExists.executeQuery();
			if (rs.next())
				return new DbResultMessage<Boolean>(DbStatus.EXISTS,
						"Account with username:" + account.getUsername() + " already exists.");
			psExists.close();

			ps = ServiceUtil.prepareStatement(connection, AccountSqlExtension.SQL_UPDATE_ACCOUNT, true,
					AccountSqlExtension.mapForUpdateAccount(account, passwordHash));
			ps.executeUpdate();
			if (!ServiceUtil.isSuccess(ps))
				return new DbResultMessage<Boolean>(false, DbStatus.UNKNOWN_ERROR, "Updating employee failed.");

			return new DbResultMessage<Boolean>(true);
		} catch (Exception ex) {
			return ErrorHandler.handle(ex);
		} finally {
			ServiceUtil.finish(connectionPool, connection, ps);
		}
	}

	@Override
	public DbResultMessage<Boolean> updateUser(UserDTO user, String password) {
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement ps = null;
		PreparedStatement updateUser = null;

		if (Validator.isInvalidUser(user))
			return new DbResultMessage<Boolean>(DbStatus.INVALID_DATA);

		byte[] passwordHash = Security.computePasswordHash(password);

		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.checkOut();

			// Check if a user with the specified username already exists
			PreparedStatement psExists = ServiceUtil.prepareStatement(connection,
					AccountSqlExtension.SQL_SELECT_WITH_USERNAME, user.getUsername());
			ResultSet rs = psExists.executeQuery();
			if (rs.next())
				return new DbResultMessage<Boolean>(DbStatus.EXISTS,
						"Account with username:" + user.getUsername() + " already exists.");
			psExists.close();

			// Update row in parent table - account
			ps = ServiceUtil.prepareStatement(connection, AccountSqlExtension.SQL_UPDATE_ACCOUNT, false,
					AccountSqlExtension.mapForUpdateAccount(user, passwordHash));
			ps.executeUpdate();

			if (!ServiceUtil.isSuccess(ps))
				return new DbResultMessage<Boolean>(false, DbStatus.UNKNOWN_ERROR, "Update failed.");
			ps.close();

			// Save user data
			updateUser = ServiceUtil.prepareStatement(connection, AccountSqlExtension.SQL_UPDATE_USER, false,
					AccountSqlExtension.mapForUpdateUser(user));
			updateUser.executeUpdate();

			if (!ServiceUtil.isSuccess(updateUser))
				return new DbResultMessage<Boolean>(false, DbStatus.UNKNOWN_ERROR, "Update failed.");

			return new DbResultMessage<Boolean>(true);
		} catch (Exception ex) {
			return ErrorHandler.handle(ex);
		} finally {
			ServiceUtil.finish(connectionPool, connection, updateUser);
		}
	}

	@Override
	public DbResultMessage<Boolean> deleteUser(int accountId) {
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement ps = null;
		PreparedStatement deleteUser = null;

		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.checkOut();

			ps = ServiceUtil.prepareStatement(connection, AccountSqlExtension.SQL_DELETE_ACCOUNT, false,
					new Object[] { accountId });
			ps.executeUpdate();

			if (!ServiceUtil.isSuccess(ps))
				return new DbResultMessage<Boolean>(false, DbStatus.UNKNOWN_ERROR, "Deleting employee failed.");
			ps.close();

			deleteUser = ServiceUtil.prepareStatement(connection, AccountSqlExtension.SQL_DELETE_USER, false,
					new Object[] { accountId });
			deleteUser.executeUpdate();
			if (!ServiceUtil.isSuccess(ps))
				return new DbResultMessage<Boolean>(false, DbStatus.UNKNOWN_ERROR, "Deleting employee failed.");

			return new DbResultMessage<Boolean>(true);
		} catch (Exception ex) {
			return ErrorHandler.handle(ex);
		} finally {
			ServiceUtil.finish(connectionPool, connection, deleteUser);
		}
	}

	@Override
	public DbResultMessage<Boolean> deleteEmployee(int accountId) {
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.checkOut();

			ps = ServiceUtil.prepareStatement(connection, AccountSqlExtension.SQL_DELETE_ACCOUNT, false,
					new Object[] { accountId });
			ps.executeUpdate();

			if (!ServiceUtil.isSuccess(ps))
				return new DbResultMessage<Boolean>(false, DbStatus.UNKNOWN_ERROR, "Deleting employee failed.");
			return new DbResultMessage<Boolean>(true);
		} catch (Exception ex) {
			return ErrorHandler.handle(ex);
		} finally {
			ServiceUtil.finish(connectionPool, connection, ps);
		}
	}

	@Override
	public List<? extends AccountDTO> getAll(AccountDTO account, AccountRole role) {
		return role == AccountRole.Employee ? getEmployees() : getUsers();
	}

	private List<AccountDTO> getEmployees() {
		List<AccountDTO> accounts = new ArrayList<AccountDTO>();
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.checkOut();
			ps = ServiceUtil.prepareStatement(connection, AccountSqlExtension.SQL_SELECT_ACCOUNTS);
			ResultSet rs = ps.executeQuery();

			while (rs.next())
				accounts.add(AccountSqlExtension.mapFromSelect(rs));

			return accounts;
		} catch (Exception ex) {
			ErrorHandler.handle(ex);
			return accounts;
		} finally {
			ServiceUtil.finish(connectionPool, connection, ps);
		}
		return accounts;
	}

	private List<UserDTO> getUsers() {
		List<UserDTO> accounts = new ArrayList<UserDTO>();
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.checkOut();
			ps = ServiceUtil.prepareStatement(connection, AccountSqlExtension.SQL_SELECT_USERS);
			ResultSet rs = ps.executeQuery();

			while (rs.next())
				accounts.add(AccountSqlExtension.mapUserFromSelect(rs));

			return accounts;
		} catch (Exception ex) {
			ErrorHandler.handle(ex);
			return accounts;
		} finally {
			ServiceUtil.finish(connectionPool, connection, ps);
		}
		return accounts;
	}

	private final static class AccountSqlExtension {
		public static final String SQL_SELECT_WITH_USERNAME = "SELECT * FROM accounts WHERE username=%s";
		public static final String SQL_INSERT = "INSERT INTO accounts (name, lastname, username, password, accountrole) VALUES (?, ?, ?, ?, ?)";
		public static final String SQL_INSERT_USER = "INSERT INTO users (accountId, email, country, address) VALUES (?, ?, ?, ?)";
		public static final String SQL_UPDATE_ACCOUNT = "UPDATE accounts SET name=?, lastName=?, username=?, password=?, accountRole=? WHERE accountId=?";
		public static final String SQL_UPDATE_USER = "UPDATE users SET email=?, country=?, address=? WHERE accountId=?";
		public static final String SQL_DELETE_ACCOUNT = "DELETE FROM accounts WHERE accountId=?";
		public static final String SQL_DELETE_USER = "DELETE FROM users WHERE accountId=?";
		public static final String SQL_SELECT_ACCOUNTS = "SELECT * FROM accounts";
		public static final String SQL_SELECT_USERS = "SELECT a.accountId, a.name, a.lastname, a.username, a.accountRole, u.email, u.country, u.address FROM accounts a INNER JOIN users u ON u.accountId=a.accountId";

		public static Object[] mapForInsertAccount(AccountDTO account, byte[] password) {
			return new Object[] { account.getName(), account.getLastName(), account.getUsername(), password,
					account.getRole().name() };
		}

		public static Object[] mapForInsertUser(UserDTO user, int accountId) {
			return new Object[] { accountId, user.getEmail(), user.getCountry(), user.getAddress() };
		}

		public static Object[] mapForUpdateAccount(AccountDTO account, byte[] password) {
			return new Object[] { account.getName(), account.getLastName(), account.getUsername(), password,
					account.getAccountId() };
		}

		public static Object[] mapForUpdateUser(UserDTO user) {
			return new Object[] { user.getEmail(), user.getCountry(), user.getAddress(), user.getAccountId() };
		}

		public static AccountDTO mapFromSelect(ResultSet rs) throws SQLException {
			return new AccountDTO(rs.getInt("accountId"), rs.getString("name"), rs.getString("lastname"),
					rs.getString("username"), AccountRole.valueOf(rs.getString("accountRole")));
		}

		public static UserDTO mapUserFromSelect(ResultSet rs) throws SQLException {
			return new UserDTO(rs.getInt("accountId"), rs.getString("name"), rs.getString("lastName"),
					rs.getString("username"), AccountRole.valueOf(rs.getString("accountRole")), rs.getString("email"),
					rs.getString("country"), rs.getString("address"));
		}
	}
}
