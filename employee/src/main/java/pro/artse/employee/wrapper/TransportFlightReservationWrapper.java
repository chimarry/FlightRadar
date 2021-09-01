package pro.artse.employee.wrapper;

public class TransportFlightReservationWrapper extends FlightReservationWrapper{
	private static final long serialVersionUID = 3922831145563281534L;
	
	private String cargoDescription;

	public String getCargoDescription() {
		return cargoDescription;
	}

	public void setCargoDescription(String cargoDescription) {
		this.cargoDescription = cargoDescription;
	}
}
