package pro.artse.employee.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pro.artse.employee.entities.Flight;

public interface FlightRepository extends JpaRepository<Flight, Integer>{

}
