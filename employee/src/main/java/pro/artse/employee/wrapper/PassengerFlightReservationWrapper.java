package pro.artse.employee.wrapper;

public class PassengerFlightReservationWrapper extends FlightReservationWrapper {

	private static final long serialVersionUID = -4156695146538006456L;
	
	private int seatNumber;

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
}
