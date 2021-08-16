package pro.artse.user.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import pro.artse.dal.dto.CityDTO;
import pro.artse.dal.dto.CountryDTO;
import pro.artse.dal.services.ILocationService;
import pro.artse.dal.services.ServiceFactory;

@WebServlet("/LocationController")
public class LocationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String GET_CITIES = "cities";
	private ILocationService locationService = ServiceFactory.getLocationService();

	public LocationController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String json;

		if (GET_CITIES.equals(action)) {
			int countryId = Integer.parseInt(request.getParameter("countryId"));
			List<CityDTO> cities = locationService.getCities(countryId);
			json = new Gson().toJson(cities);
		} else {
			List<CountryDTO> countries = locationService.getCountries();
			json = new Gson().toJson(countries);
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
