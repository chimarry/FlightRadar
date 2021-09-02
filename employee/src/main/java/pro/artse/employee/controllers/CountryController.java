package pro.artse.employee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pro.artse.employee.entities.Country;
import pro.artse.employee.services.CountryService;

@RestController
@RequestMapping("/api/v0.1/countries")
public class CountryController {

	@Autowired
	private CountryService countryService;
	
	@GetMapping
	public ResponseEntity<List<Country>> getAll(){
		return ResponseEntity.ok(countryService.getAll());
	}
}
