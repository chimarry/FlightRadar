package pro.artse.dal.util;

import java.util.List;

import pro.artse.dal.dto.AccountRole;
import pro.artse.dal.dto.FlightReservationDTO;
import pro.artse.dal.dto.FlightReservationStatus;
import pro.artse.dal.dto.UserDTO;
import pro.artse.dal.services.IAccountService;
import pro.artse.dal.services.IFlightReservationService;
import pro.artse.dal.services.ServiceFactory;

public class Test {

	public static void main(String... args) {
		/*
		 * IFlightService flightService = ServiceFactory.getFlightService();
		 * List<FlightDTO> flights = flightService.getAll(true); for (FlightDTO flight :
		 * flights) { System.out.println(flight.getFlightId() + ". " +
		 * flight.getArrivalCityName() + " " + flight.getDepartureCityName() + " " +
		 * flight.getDepartureOn() + " " + flight.getType()); }
		 

		IFlightReservationService reservationService = ServiceFactory
				.getFlightReservationService(AccountRole.Passenger);
		InputFlightReservationDTO dto = new InputFlightReservationDTO(LocalDate.of(2021, 8, 15), 3, 1);
		dto.setSeatNumber(12);
		dto.setAccountId(1);
		System.out.println(reservationService.create(dto, AccountRole.Passenger).getStatus());*/
		IFlightReservationService reservationService = ServiceFactory
				.getFlightReservationService(AccountRole.Passenger);
		reservationService.changeStatus(1, FlightReservationStatus.Canceled, 1);
		List<FlightReservationDTO> flightReservationDTOs = reservationService.getAll(1);
		for(var f:flightReservationDTOs)
			System.out.println(f.getArrivalCityName()+ f.getArrivalCountryName()+ f.getSeatNumber()+" "+f.getAccountId()+f.getArrivalOn());
		
		IAccountService accountService  = ServiceFactory.getAccountService();
		UserDTO user = new UserDTO("Marija", "Novakovic", "maki", AccountRole.Passenger, "marija@gmail.com", "Serbia", "Koste Jarica");
		System.out.print(accountService.register(user, "maki97").getStatus());
	}
}
