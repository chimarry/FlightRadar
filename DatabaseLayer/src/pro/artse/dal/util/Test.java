package pro.artse.dal.util;

import java.time.LocalDate;
import java.util.List;

import pro.artse.dal.dto.AccountRole;
import pro.artse.dal.dto.FlightDTO;
import pro.artse.dal.dto.InputFlightReservationDTO;
import pro.artse.dal.services.IFlightReservationService;
import pro.artse.dal.services.IFlightService;
import pro.artse.dal.services.ServiceFactory;

public class Test {

	public static void main(String... args) {
		/*
		 * IFlightService flightService = ServiceFactory.getFlightService();
		 * List<FlightDTO> flights = flightService.getAll(true); for (FlightDTO flight :
		 * flights) { System.out.println(flight.getFlightId() + ". " +
		 * flight.getArrivalCityName() + " " + flight.getDepartureCityName() + " " +
		 * flight.getDepartureOn() + " " + flight.getType()); }
		 */

		IFlightReservationService reservationService = ServiceFactory
				.getFlightReservationService(AccountRole.Passenger);
		InputFlightReservationDTO dto = new InputFlightReservationDTO(LocalDate.of(2021, 8, 15), 1, 1);
		dto.setSeatNumber(12);
		dto.setAccountId(1);
		System.out.println(reservationService.create(dto, AccountRole.Passenger).getStatus());
	}
}
