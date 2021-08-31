package pro.artse.employee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
