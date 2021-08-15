package pro.artse.dal.util;

import java.util.List;

import pro.artse.dal.dto.FlightDTO;
import pro.artse.dal.services.IFlightService;
import pro.artse.dal.services.ServiceFactory;

public class Test {

	public static void main(String... args) {
		IFlightService flightService = ServiceFactory.getFlightService();
		List<FlightDTO> flights = flightService.getAll(true);
		for(FlightDTO flight: flights){
		  System.out.println(flight.getFlightId()+ ". "+ flight.getArrivalCityName()+" " + flight.getDepartureCityName() +" " + flight.getDepartureOn()+ " "
				  + flight.getType());
		}
	}
}
