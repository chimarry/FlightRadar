package pro.artse.user.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FlightsController")
public class FlightsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ARRIVALS = "arrivals";
	private static final String DEPARTURES = "departures";
	
    public FlightsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String address = "/WEB-INF/pages/index.jsp";

		String action = request.getParameter("action");
		if (ARRIVALS.equals(action))
			address = "/WEB-INF/pages/arrivals.jsp";
		if (DEPARTURES.equals(action))
			address = "/WEB-INF/pages/departures.jsp";

		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
