package pro.artse.user.util;

import pro.artse.user.beans.AccountBean;

public final class Validator {

	public static boolean isEmptyOrNull(String value) {
		return value == null || value.equals("");
	}

	public static boolean isLoggedIn(Object accountBean) {
		if (accountBean != null && accountBean instanceof AccountBean)
			return ((AccountBean) accountBean).isLoggedIn();
		return false;
	}
}
