package pro.artse.employee.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.artse.employee.entities.City;
import pro.artse.employee.repositories.CityRepository;

@Service
public class CityService {
	
	@Autowired
	private CityRepository cityRepository;
	
	public List<City> getAll(int countryId){
		return cityRepository.findByCountryCountryId(countryId);
	}
}
