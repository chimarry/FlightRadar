package pro.artse.dal.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Stream;

import pro.artse.dal.util.Validator;

public final class ServiceUtil {

	public static PreparedStatement prepareStatement(Connection connection, String sql, boolean returnGeneratedKeys,
			Object... values) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(sql,
				returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
		setValues(preparedStatement, values);
		return preparedStatement;
	}

	public static void setValues(PreparedStatement preparedStatement, Object... values) throws SQLException {
		for (int i = 0; i < values.length; i++) {
			preparedStatement.setObject(i + 1, values[i]);
		}
	}

	public static PreparedStatement prepareStatement(Connection connection, String sql, Object... values)
			throws SQLException {
		Object[] wrappedValues = Stream.of(values).map(x -> x.getClass() == String.class ? String.format("'%s'", x) : x)
				.toArray();
		String sqlToExecute = String.format(sql, wrappedValues);
		return connection.prepareStatement(sqlToExecute,ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);
	}

	public static boolean isSuccess(PreparedStatement ps) throws SQLException {
		return ps.getUpdateCount() > 0;
	}

	public static void finish(ConnectionPool pool, Connection connection) {
		if (!Validator.areNull(pool, connection))
			pool.checkIn(connection);
	}

	public static void finish(ConnectionPool pool, Connection connection, PreparedStatement ps) {
		try {
			if (!Validator.isNull(ps))
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			finish(pool, connection);
		}
	}

	public static boolean isEmpty(ResultSet resultSet) throws SQLException {
		if (resultSet.next()) {
			resultSet.beforeFirst();
			return false;
		}
		return true;
	}

	public static boolean hasMany(ResultSet resultSet) throws SQLException {
		boolean hasMany = resultSet.next() && resultSet.next();
		resultSet.beforeFirst();
		return hasMany;
	}
}
