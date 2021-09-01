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

	public FlightReservation getDetails(int flightReservationId) {
		return flightReservationRepository.findById(flightReservationId).get();
	}
	
	public Boolean cancel(int flightReservationId) {
		FlightReservation entity = flightReservationRepository.findById(flightReservationId).get();
		if (entity == null)
			return false;
		entity.setStatus(FlightReservationStatus.Canceled);
		flightReservationRepository.saveAndFlush(entity);
		return true;
	}
	
	public Boolean confirm(int flightReservationId) {
		FlightReservation entity = flightReservationRepository.findById(flightReservationId).get();
		if (entity == null)
			return false;
		entity.setStatus(FlightReservationStatus.Confirmed);
		flightReservationRepository.saveAndFlush(entity);
		return true;
	}
}
