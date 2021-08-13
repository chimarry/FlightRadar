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
import pro.artse.user.mapper.AccountMapper;

@WebServlet("/IndexController")
public class IndexController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private IAccountService accountService = ServiceFactory.getAccountService();

	public IndexController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String address = "/WEB-INF/pages/index.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String address = "/WEB-INF/pages/registration.jsp";
		HttpSession session = request.getSession();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		DbResultMessage<AccountDTO> loggedIn = accountService.login(username, password);

		// TODO: Obrada greske
		if (loggedIn.isSuccess())
			session.setAttribute("accountBean", AccountMapper.mapToBean(loggedIn.getResult()));

		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
}
