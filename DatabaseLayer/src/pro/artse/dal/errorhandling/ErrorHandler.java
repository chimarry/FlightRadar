package pro.artse.dal.errorhandling;

import java.sql.SQLException;
import java.time.format.DateTimeParseException;

public class ErrorHandler {
	public static <T> DbResultMessage<T> handle(Exception ex, T result) {
		DbResultMessage<T> resultMessage = handle(ex);
		resultMessage.setResult(result);
		return resultMessage;
	}

	public static <T> DbResultMessage<T> handle(Exception ex) {
		// TODO: Add logger
		ex.printStackTrace();
		if (ex instanceof SQLException)
			return new DbResultMessage<T>(DbStatus.SERVER_ERROR);
		else if (ex instanceof DateTimeParseException) {
			return new DbResultMessage<T>(DbStatus.INVALID_DATA);
		} else if (ex instanceof ArrayIndexOutOfBoundsException)
			return new DbResultMessage<T>(DbStatus.SERVER_ERROR);
		return new DbResultMessage<T>(DbStatus.UNKNOWN_ERROR);
	}
}
