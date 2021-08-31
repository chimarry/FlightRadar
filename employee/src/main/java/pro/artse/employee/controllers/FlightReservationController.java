package pro.artse.employee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pro.artse.employee.entities.FlightReservation;
import pro.artse.employee.entities.FlightReservationStatus;
import pro.artse.employee.services.FlightReservationService;

@RestController
@RequestMapping("/api/v0.1/reservations")
public class FlightReservationController {

	@Autowired
	private FlightReservationService flightReservationService;

	@GetMapping
	public ResponseEntity<List<FlightReservation>> getAll(
			@RequestParam(required = false) List<FlightReservationStatus> filters) {
		return ResponseEntity.ok(flightReservationService.getAll(filters));
	}
	
	@PutMapping("/cancel/{flightReservationId}")
	public ResponseEntity<Boolean> cancel(@PathVariable int flightReservationId){
		return ResponseEntity.ok(flightReservationService.cancel(flightReservationId));
	}
	
	@PutMapping("/confirm/{flightReservationId}")
	public ResponseEntity<Boolean> confirm(@PathVariable int flightReservationId){
		return ResponseEntity.ok(flightReservationService.confirm(flightReservationId));
	}
}
