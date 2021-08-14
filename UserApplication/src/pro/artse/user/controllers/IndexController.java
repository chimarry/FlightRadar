package pro.artse.user.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.manager.util.SessionUtils;

import pro.artse.dal.dto.AccountDTO;
import pro.artse.dal.errorhandling.DbResultMessage;
import pro.artse.dal.services.IAccountService;
import pro.artse.dal.services.ServiceFactory;
import pro.artse.user.beans.AccountBean;
import pro.artse.user.mapper.AccountMapper;
import pro.artse.user.util.Pages;
import pro.artse.user.util.SessionBeans;
import pro.artse.user.util.HttpSessionUtil;

@WebServlet("/IndexController")
public class IndexController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private IAccountService accountService = ServiceFactory.getAccountService();

	public IndexController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String address = Pages.INDEX;
		HttpSession session = request.getSession();

		if (!HttpSessionUtil.isLoggedIn(session))
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

		if (loggedIn.isSuccess()) {
			AccountBean account = AccountMapper.mapToBean(loggedIn.getResult());
			account.setLoggedIn(true);
			HttpSessionUtil.logIn(session, account);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
}
