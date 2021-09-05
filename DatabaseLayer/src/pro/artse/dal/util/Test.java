package pro.artse.dal.util;


import pro.artse.dal.dto.AccountDTO;
import pro.artse.dal.errorhandling.DbResultMessage;
import pro.artse.dal.services.IAccountService;
import pro.artse.dal.services.ServiceFactory;

public class Test {

	public static void main(String... args) {
		/*
		 * IFlightService flightService = ServiceFactory.getFlightService();
		 * List<FlightDTO> flights = flightService.getAll(true); for (FlightDTO flight :
		 * flights) { System.out.println(flight.getFlightId() + ". " +
		 * flight.getArrivalCityName() + " " + flight.getDepartureCityName() + " " +
		 * flight.getDepartureOn() + " " + flight.getType()); }
		 * 
		 * 
		 * IFlightReservationService reservationService = ServiceFactory
		 * .getFlightReservationService(AccountRole.Passenger);
		 * InputFlightReservationDTO dto = new
		 * InputFlightReservationDTO(LocalDate.of(2021, 8, 15), 3, 1);
		 * dto.setSeatNumber(12); dto.setAccountId(1);
		 * System.out.println(reservationService.create(dto,
		 * AccountRole.Passenger).getStatus());
		 */
		 IAccountService accountService = ServiceFactory.getAccountService();
		 DbResultMessage<AccountDTO> account =  accountService.login("maki", "maki");
		 System.out.println(account.getMessage());
	}
}
