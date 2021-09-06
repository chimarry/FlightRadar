package pro.artse.user.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndContentImpl;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import com.rometools.rome.io.SyndFeedOutput;

import pro.artse.dal.dto.FlightDTO;
import pro.artse.dal.services.IFlightService;
import pro.artse.dal.services.ServiceFactory;

@WebServlet("/rss")
public class RSSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IFlightService flightService = ServiceFactory.getFlightService();

	private static final String RSS_AIRPORT_LOCATION = "Banja Luka";

	public RSSServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SyndFeed feed = new SyndFeedImpl();
		feed.setFeedType("rss_2.0");
		feed.setTitle("Fligths");
		feed.setDescription("Flights planned for today");
		feed.setLink("http://localhost:8080/UserApplication/FlightsController?action=refresh&date=2021-08-15");
		// + LocalDate.now().toString());
		List<SyndEntry> entries = new ArrayList<>();

		List<FlightDTO> flights = flightService.getFeatured(true);
		flights.addAll(flightService.getFeatured(false));
		int i = 1;
		for (FlightDTO dto : flights) {
			SyndEntry item = new SyndEntryImpl();
			item.setTitle(
					String.format("Flight %d: %s -> %s", i, dto.getDepartureCityName(), dto.getArrivalCityName()));

			SyndContent content = new SyndContentImpl();
			content.setValue(String.format("Description: %s, %d:%d", dto.getStatus(),
					dto.getAirportDateTime().getHour(), dto.getAirportDateTime().getMinute()));
			item.setDescription(content);
			boolean isDeparture = !RSS_AIRPORT_LOCATION.equals(dto.getArrivalCityName());
			item.setLink("http://localhost:8080/UserApplication/FlightsController?action=refresh" + "&isDeparture="
					+ isDeparture + "&date=2021-08-15");
			entries.add(item);
			++i;
		}

		feed.setEntries(entries);
		try {
			response.getWriter().println(new SyndFeedOutput().outputString(feed));
		} catch (Exception ex) {
			System.out.println(ex.toString());
			response.sendError(500);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}