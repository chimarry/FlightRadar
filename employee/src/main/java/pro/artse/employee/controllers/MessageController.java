package pro.artse.employee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pro.artse.employee.entities.City;
import pro.artse.employee.entities.FlightReservation;
import pro.artse.employee.entities.Message;
import pro.artse.employee.repositories.CountryRepository;
import pro.artse.employee.repositories.FlightReservationRepository;
import pro.artse.employee.repositories.MessageRepository;
import pro.artse.employee.services.MessageService;

@RestController
@RequestMapping("/api/v0.1/messages")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private FlightReservationRepository fRepository;
	
	@GetMapping
	public ResponseEntity<FlightReservation> findByName(){
		return ResponseEntity.ok(fRepository.findById(1).get());
	}
}
