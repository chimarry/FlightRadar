package pro.artse.user.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pro.artse.user.util.Pages;
import pro.artse.user.util.HttpSessionUtil;

@WebServlet("/FlightReservationController")
public class FlightReservationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SHOW_ALL_RESERVATIONS = "all";
	private static final String SHOW_RESERVATION_FORM = "form";

	public FlightReservationController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String address = Pages.NOT_AUTHENTICATED;
		HttpSession session = request.getSession();
		if (HttpSessionUtil.isLoggedIn(session)) {
			String action = request.getParameter("action");
			if (SHOW_ALL_RESERVATIONS.equals(action))
				address = Pages.FLIGHT_RESERVATIONS;
			if (SHOW_RESERVATION_FORM.equals(action))
				address = Pages.FLIGHT_RESERVATION_FORM;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
