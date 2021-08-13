package pro.artse.dal.errorhandling;

import java.sql.SQLException;
import java.time.format.DateTimeParseException;

public class ErrorHandler {
	public static <T> DBResultMessage<T> handle(Exception ex, T result) {
		DBResultMessage<T> resultMessage = handle(ex);
		resultMessage.setResult(result);
		return resultMessage;
	}

	public static <T> DBResultMessage<T> handle(Exception ex) {
		// TODO: Add logger
		if(ex instanceof SQLException)
			return new DBResultMessage<T>(DbStatus.SERVER_ERROR);
		else if (ex instanceof DateTimeParseException) {
			return new DBResultMessage<T>(DbStatus.INVALID_DATA);
		} else if (ex instanceof ArrayIndexOutOfBoundsException)
			return new DBResultMessage<T>(DbStatus.SERVER_ERROR);
		return new DBResultMessage<T>(DbStatus.UNKNOWN_ERROR);
	}
}
