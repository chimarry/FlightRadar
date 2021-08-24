package pro.artse.user.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import pro.artse.dal.services.ServiceFactory;
import pro.artse.user.beans.AccountBean;

public final class HttpSessionUtil {

	public static final void logIn(HttpSession session, AccountBean account) {
		session.setAttribute(SessionBeans.ACCOUNT_BEAN, account);
		
		// Count visits
		ServiceFactory.getAppVisitService().add();
	}

	public static final boolean isLoggedIn(HttpSession session) {
		Object accountBean = session.getAttribute(SessionBeans.ACCOUNT_BEAN);
		if (accountBean != null && accountBean instanceof AccountBean)
			return ((AccountBean) accountBean).isLoggedIn();
		return false;
	}

	public static final AccountBean getAccountInfo(HttpSession session) {
		return (AccountBean) session.getAttribute(SessionBeans.ACCOUNT_BEAN);
	}

	public static final int getAccountId(HttpServletRequest request) {
		return getAccountInfo(request.getSession()).getAccountId();
	}

	public static final void turnOnGuestMode(HttpSession session) {
		session.setAttribute(SessionBeans.ACCOUNT_BEAN, AccountBean.createGuest());
	}

	public static final void logOut(HttpSession session) {
		turnOnGuestMode(session);
	}
}
