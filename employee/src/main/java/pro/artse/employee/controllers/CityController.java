package pro.artse.employee.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pro.artse.employee.entities.City;
import pro.artse.employee.services.CityService;
import pro.artse.employee.wrapper.CityWrapper;

@RestController
@RequestMapping("/api/v0.1/cities")
public class CityController {

	@Autowired
	private CityService cityService;

	@GetMapping("/{countryId}")
	public ResponseEntity<List<CityWrapper>> getAllBy(@PathVariable int countryId) {
		List<City> cities = cityService.getAll(countryId);
		List<CityWrapper> responseCities = cities.stream().map(city -> CityWrapper.fromEntity(city))
				.collect(Collectors.toCollection(ArrayList::new));
		return ResponseEntity.ok(responseCities);
	}
}
