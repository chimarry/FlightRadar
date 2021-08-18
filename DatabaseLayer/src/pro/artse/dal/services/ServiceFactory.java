package pro.artse.dal.services;

import pro.artse.dal.dto.AccountRole;

public final class ServiceFactory {

	public static IAccountService getAccountService() {
		return new AccountService();
	}

	public static IFlightService getFlightService() {
		return new FlightService();
	}

	public static IFlightReservationService getFlightReservationService(AccountRole role) {
		if (role == AccountRole.Passenger)
			return new PassengerFlightReservationService();
		else
			return new TransportFlightReservationService();
	}

	public static ILocationService getLocationService() {
		return new LocationService();
	}
	
	public static IMessageService getMessageService() {
		return new MessageService();
	}
}
