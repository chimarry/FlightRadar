package pro.artse.user.util;

import javax.servlet.http.HttpServletRequest;

import pro.artse.dal.errorhandling.DbResultMessage;

public final class AlertManager {

	public static <T> void addToRequest(HttpServletRequest request, DbResultMessage<T> resultMessage,
			String errorMessage) {
		if (resultMessage.isSuccess()) {
			request.setAttribute("successMessage", Messages.SUCCESS);
		} else
			request.setAttribute("errorMessage",
					Validator.isEmptyOrNull(resultMessage.getMessage()) ? errorMessage : resultMessage.getMessage());
	}

	public static void writeErrorMessage(HttpServletRequest request, String message) {
		request.setAttribute("errorMessage", message);
	}
}
