package pro.artse.employee.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import pro.artse.employee.entities.FlightType;
import pro.artse.employee.mapper.IFlightReservationMapper;
import pro.artse.employee.services.FlightReservationService;
import pro.artse.employee.wrapper.FlightReservationWrapper;

@RestController
@RequestMapping("/api/v0.1/reservations")
public class FlightReservationController {

	@Autowired
	private FlightReservationService flightReservationService;

	@Autowired
	private IFlightReservationMapper mapper;

	@GetMapping
	public ResponseEntity<List<FlightReservationWrapper>> getAll(

			@RequestParam(required = false) List<FlightReservationStatus> filters) {
		List<FlightReservationWrapper> data = flightReservationService.getAll(filters).stream()
				.map(x -> mapper.toWrapper(x)).collect(Collectors.toCollection(ArrayList::new));

		return ResponseEntity.ok(data);
	}

	@GetMapping("/{flightReservationId}")
	public ResponseEntity<FlightReservationWrapper> getDetails(@PathVariable int flightReservationId,
			@RequestParam FlightType flightType) {
		FlightReservation reservation = flightReservationService.getDetails(flightReservationId);
		if (flightType == FlightType.Passenger)
			return ResponseEntity.ok(mapper.toPassengerWrapper(reservation));
		else
			return ResponseEntity.ok(mapper.toTransportWrapper(reservation));
	}

	@PutMapping("/cancel/{flightReservationId}")
	public ResponseEntity<Boolean> cancel(@PathVariable int flightReservationId) {
		return ResponseEntity.ok(flightReservationService.cancel(flightReservationId));
	}

	@PutMapping("/confirm/{flightReservationId}")
	public ResponseEntity<Boolean> confirm(@PathVariable int flightReservationId) {
		return ResponseEntity.ok(flightReservationService.confirm(flightReservationId));
	}

}
