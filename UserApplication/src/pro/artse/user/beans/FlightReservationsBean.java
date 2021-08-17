package pro.artse.user.beans;

import java.io.Serializable;
import java.util.List;

public class FlightReservationsBean implements Serializable {

	private static final long serialVersionUID = -1082555933573666277L;

	private List<FlightReservationBean> flightReservations;

	public List<FlightReservationBean> getFlightReservations() {
		return flightReservations;
	}

	public void setFlightReservations(List<FlightReservationBean> flightReservations) {
		this.flightReservations = flightReservations;
	}
	
	
}
