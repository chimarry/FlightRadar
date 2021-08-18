package pro.artse.dal.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class SqlDateTimeConvertor {
	public static final String MYSQL_DATE_FORMAT = "YYYY-MM-dd";
	public static final String MYSQL_DATETIME_FORMAT = "YYYY-MM-dd hh:mm:ss";
	
	public static String toDbDateTime(LocalDateTime dateTime) {
		return  dateTime.format(DateTimeFormatter.ofPattern(MYSQL_DATETIME_FORMAT));
	}
	
	public static final String toDbDate(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(MYSQL_DATE_FORMAT);
		return formatter.format(date);
	}
	
	public static final LocalDateTime toLocalDateTime(ResultSet rs, String columnName) throws SQLException {
		return rs.getTimestamp(columnName).toLocalDateTime();
	}
}
