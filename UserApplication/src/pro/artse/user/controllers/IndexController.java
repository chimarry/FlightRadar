package pro.artse.user.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import pro.artse.dal.dto.AccountDTO;
import pro.artse.dal.errorhandling.DbResultMessage;
import pro.artse.dal.services.IAccountService;
import pro.artse.dal.services.IFlightService;
import pro.artse.dal.services.ServiceFactory;
import pro.artse.user.beans.AccountBean;
import pro.artse.user.beans.FlightBean;
import pro.artse.user.mapper.AccountMapper;
import pro.artse.user.mapper.FlightMapper;
import pro.artse.user.util.Pages;
import pro.artse.user.util.HttpSessionUtil;
import pro.artse.user.util.Messages;

@WebServlet("/IndexController")
public class IndexController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String LOG_OUT = "logout";
	private static final String REFRESH = "refresh";

	private IAccountService accountService = ServiceFactory.getAccountService();
	private IFlightService flightService = ServiceFactory.getFlightService();

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

		// Add arrivals and departures on home page
		addFlights(response);

		if (!REFRESH.equals(action)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		}
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

		// Add arrivals and departures on home page
		addFlights(response);

		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	private void addFlights(HttpServletResponse response) throws IOException {
		List<FlightBean> departureFlightsBean = FlightMapper.mapToBeans(flightService.getFeatured(true), true);
		List<FlightBean> arrivalFlightsBean = FlightMapper.mapToBeans(flightService.getFeatured(false), false);
		
		Gson gson = new Gson();
		JsonArray array = new JsonArray();
		array.add(gson.toJsonTree(departureFlightsBean));
		array.add(gson.toJsonTree(arrivalFlightsBean));
		String json = array.toString();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}
}
