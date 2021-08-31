package pro.artse.employee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pro.artse.employee.services.FlightService;
import pro.artse.employee.wrapper.FlightWrapper;

@RestController
@RequestMapping("/api/v0.1/flights")
public class FlightController {

	@Autowired
	private FlightService flightService;
	
	@PostMapping
	public ResponseEntity<Boolean> add(@RequestBody FlightWrapper flight){
		Boolean status = flightService.add(flight);
		return ResponseEntity.ok(status);
	}
}
