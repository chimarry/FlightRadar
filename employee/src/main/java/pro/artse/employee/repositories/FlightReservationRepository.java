package pro.artse.employee.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pro.artse.employee.entities.FlightReservation;
import pro.artse.employee.entities.FlightReservationStatus;

public interface FlightReservationRepository extends JpaRepository<FlightReservation, Integer>{
	public List<FlightReservation> findByStatus(FlightReservationStatus status);
}
