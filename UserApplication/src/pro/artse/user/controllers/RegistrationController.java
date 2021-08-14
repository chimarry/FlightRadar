package pro.artse.user.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pro.artse.dal.dto.AccountDTO;
import pro.artse.dal.dto.AccountRole;
import pro.artse.dal.errorhandling.DbResultMessage;
import pro.artse.dal.services.IAccountService;
import pro.artse.dal.services.ServiceFactory;
import pro.artse.user.beans.CountriesBean;
import pro.artse.user.util.Messages;
import pro.artse.user.util.Pages;
import pro.artse.user.util.RestApiUtil;
import pro.artse.user.util.Validator;

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
		
		CountriesBean bean = new CountriesBean();
		bean.setCountries(RestApiUtil.getCountries());
		request.setAttribute("countriesBean", bean);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String address = Pages.INDEX;

		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setName(request.getParameter("name"));
		accountDTO.setLastName(request.getParameter("lastName"));
		accountDTO.setUsername(request.getParameter("username"));
		accountDTO.setCountry(request.getParameter("country"));
		accountDTO.setEmail(request.getParameter("email"));
		accountDTO.setAddress(request.getParameter("address"));
		
		boolean isPassenger = request.getParameter("passenger") != null
				&& request.getParameter("passenger").equals("on");
		accountDTO.setRole(isPassenger ? AccountRole.Passenger : AccountRole.Transport);
		String password = request.getParameter("password");
		DbResultMessage<Boolean> isRegistered = accountService.register(accountDTO, password);

		if (isRegistered.isSuccess()) {
			request.setAttribute("successMessage", Messages.SUCCESS);
		} else
			request.setAttribute("errorMessage",
					Validator.isEmptyOrNull(isRegistered.getMessage()) ? Messages.FAILED_REGISTER
							: isRegistered.getMessage());

		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
}
