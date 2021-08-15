package pro.artse.user.beans;

import java.io.Serializable;
import java.util.List;

public class FlightsBean implements Serializable{

	private static final long serialVersionUID = -8189343126593592381L;

	private List<FlightBean> flights;

	public List<FlightBean> getFlights() {
		return flights;
	}

	public void setFlights(List<FlightBean> flights) {
		this.flights = flights;
	}
}
