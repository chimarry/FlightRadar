package pro.artse.user.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pro.artse.dal.dto.UserDTO;
import pro.artse.dal.errorhandling.DbResultMessage;
import pro.artse.dal.services.IAccountService;
import pro.artse.dal.services.ServiceFactory;
import pro.artse.user.beans.CountriesBean;
import pro.artse.user.mapper.AccountMapper;
import pro.artse.user.util.AlertManager;
import pro.artse.user.util.Messages;
import pro.artse.user.util.Pages;
import pro.artse.user.util.RestApiUtil;

@WebServlet("/RegistrationController")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IAccountService accountService = ServiceFactory.getAccountService();

	public RegistrationController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String address = Pages.REGISTRATION_FORM;

		setCountries(request);

		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String address = Pages.INDEX;

		UserDTO accountDTO = AccountMapper.mapFromRequest(request);
		String password = request.getParameter("password");

		DbResultMessage<Boolean> isRegistered = accountService.register(accountDTO, password);
		AlertManager.addToRequest(request, isRegistered, Messages.FAILED_REGISTER);

		if (!isRegistered.isSuccess()) {
			setCountries(request);
			address = Pages.REGISTRATION_FORM;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	private void setCountries(HttpServletRequest request) {
		CountriesBean bean = new CountriesBean();
		bean.setCountries(RestApiUtil.getCountries());
		request.setAttribute("countriesBean", bean);
	}
}
