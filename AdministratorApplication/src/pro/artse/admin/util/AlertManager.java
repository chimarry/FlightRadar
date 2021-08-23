package pro.artse.admin.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import pro.artse.dal.errorhandling.DbResultMessage;

public final class AlertManager {

	public static <T> void alert(DbResultMessage<T> result) {
		if (result.isSuccess())
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", null));
		else
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					result.getMessage() == null ? result.getMessage() : "Failed", null));
	}
}
