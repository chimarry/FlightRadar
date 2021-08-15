package pro.artse.dal.services;

public final class ServiceFactory {

	public static IAccountService getAccountService() {
		return new AccountService();
	}
	
	public static IFlightService getFlightService() {
		return new FlightService();
	}
	
	public static IFlightReservationService getFlightReservationService() {
		return new FlightReservationService();
	}
}
