package pro.artse.employee.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pro.artse.employee.entities.FlightReservation;

public interface FlightReservationRepository extends JpaRepository<FlightReservation, Integer>{

}
