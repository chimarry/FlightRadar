package pro.artse.employee.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.artse.employee.entities.City;
import pro.artse.employee.entities.Flight;
import pro.artse.employee.repositories.CityRepository;
import pro.artse.employee.repositories.FlightRepository;
import pro.artse.employee.wrapper.FlightWrapper;

@Service
public class FlightService {

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private CityRepository cityRepository;

	public Boolean add(FlightWrapper wrapper) {
		wrapper.getAirportDateTimes().forEach(airportDateTime->{
			City arrivalCity = cityRepository.findById(wrapper.getArrivalCityId()).get();
			City departureCity = cityRepository.findById(wrapper.getDepartureCityId()).get();

			Flight flight = new Flight();
			flight.setArrivalCity(arrivalCity);
			flight.setDepartureCity(departureCity);
			flight.setAirportDateTime(airportDateTime);
			flight.setType(wrapper.getType());

			arrivalCity.getArrivalFlights().add(flight);
			departureCity.getDepartureFlights().add(flight);

			flightRepository.saveAndFlush(flight);
		});
		return true;
	}
}
