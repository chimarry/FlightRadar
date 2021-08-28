package pro.artse.user.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import pro.artse.dal.dto.FlightDTO;
import pro.artse.dal.services.IFlightService;
import pro.artse.dal.services.ServiceFactory;
import pro.artse.user.beans.FlightBean;
import pro.artse.user.mapper.FlightMapper;
import pro.artse.user.util.Pages;

@WebServlet("/FlightsController")
public class FlightsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String NOT_DEPARTURE = "false";
	private static final String LOAD = "load";

	private IFlightService flightService = ServiceFactory.getFlightService();

	public FlightsController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<FlightBean> flights = new ArrayList<>();

		String address = Pages.FLIGHTS;
		String action = request.getParameter("action");
		String isDeparture = request.getParameter("isDeparture");
		String date = request.getParameter("date");
		LocalDate dayToGet = date != null ? LocalDate.parse(date) : LocalDate.now();

		if (NOT_DEPARTURE.equals(isDeparture))
			flights = getFlights(false, dayToGet);
		else
			flights = getFlights(true, dayToGet);

		String json = new Gson().toJson(flights);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		if (LOAD.equals(action)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private List<FlightBean> getFlights(boolean isDeparture, LocalDate dayToGet) {
		List<FlightDTO> flights = flightService.getAll(dayToGet, isDeparture);
		return FlightMapper.mapToBeans(flights);
	}
}
