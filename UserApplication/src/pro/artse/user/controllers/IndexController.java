package pro.artse.user.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pro.artse.dal.dto.AccountDTO;
import pro.artse.dal.errorhandling.DbResultMessage;
import pro.artse.dal.services.IAccountService;
import pro.artse.dal.services.ServiceFactory;
import pro.artse.user.beans.AccountBean;
import pro.artse.user.mapper.AccountMapper;
import pro.artse.user.util.Pages;
import pro.artse.user.util.HttpSessionUtil;
import pro.artse.user.util.Messages;

@WebServlet("/IndexController")
public class IndexController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String LOG_OUT = "logout";

	private IAccountService accountService = ServiceFactory.getAccountService();

	public IndexController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String address = Pages.INDEX;
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		if (LOG_OUT.equals(action))
			HttpSessionUtil.logOut(session);
		else if (!HttpSessionUtil.isLoggedIn(session))
			HttpSessionUtil.turnOnGuestMode(session);

		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String address = Pages.INDEX;
		HttpSession session = request.getSession();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		DbResultMessage<AccountDTO> loggedIn = accountService.login(username, password);

		if (loggedIn.getResult() != null) {
			AccountBean account = AccountMapper.mapToBean(loggedIn.getResult());
			account.setLoggedIn(true);
			HttpSessionUtil.logIn(session, account);
		} else
			request.setAttribute("errorMessage", Messages.FAILED_LOG_IN);

		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
}
