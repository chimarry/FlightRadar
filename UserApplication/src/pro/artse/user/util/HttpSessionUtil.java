package pro.artse.user.util;

import javax.servlet.http.HttpSession;

import pro.artse.user.beans.AccountBean;

public final class HttpSessionUtil {

	public static final void logIn(HttpSession session, AccountBean account) {
		session.setAttribute(SessionBeans.ACCOUNT_BEAN, account);
	}

	public static final boolean isLoggedIn(HttpSession session) {
		Object accountBean = session.getAttribute(SessionBeans.ACCOUNT_BEAN);
		if (accountBean != null && accountBean instanceof AccountBean)
			return ((AccountBean) accountBean).isLoggedIn();
		return false;
	}

	public static final void turnOnGuestMode(HttpSession session) {
		session.setAttribute(SessionBeans.ACCOUNT_BEAN, AccountBean.createGuest());
	}

	public static final void logOut(HttpSession session, AccountBean account) {

	}
}
