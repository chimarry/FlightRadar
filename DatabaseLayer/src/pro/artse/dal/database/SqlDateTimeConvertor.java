package pro.artse.dal.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class SqlDateTimeConvertor {
	public static final String MYSQL_DATE_FORMAT = "yyyy-MM-dd";
	public static final String MYSQL_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static String toDbDateTime(LocalDateTime dateTime) {
		return dateTime.format(DateTimeFormatter.ofPattern(MYSQL_DATETIME_FORMAT));
	}

	public static final String toDbDate(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(MYSQL_DATE_FORMAT);
		return formatter.format(date);
	}

	public static final LocalDateTime toLocalDateTime(ResultSet rs, String columnName) throws SQLException {
		var formatter = DateTimeFormatter.ofPattern(MYSQL_DATETIME_FORMAT);
		return LocalDateTime.parse(rs.getString(columnName), formatter);
	}
}
