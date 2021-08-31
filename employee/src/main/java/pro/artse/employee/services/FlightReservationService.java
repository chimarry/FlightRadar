package pro.artse.employee.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.artse.employee.entities.FlightReservation;
import pro.artse.employee.entities.FlightReservationStatus;
import pro.artse.employee.repositories.FlightReservationRepository;

@Service
public class FlightReservationService {

	@Autowired
	private FlightReservationRepository flightReservationRepository;

	public List<FlightReservation> getAll(List<FlightReservationStatus> filters) {
		List<FlightReservation> reservations = new ArrayList<FlightReservation>();
		if (filters == null)
			return flightReservationRepository.findAll();
		else
			filters.forEach(filter -> reservations.addAll(flightReservationRepository.findByStatus(filter)));
		return reservations;
	}
}
