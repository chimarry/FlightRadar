package pro.artse.employee.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pro.artse.employee.entities.City;

public interface CityRepository extends JpaRepository<City, Integer>{
	public List<City> findByCountryCountryId(int countryId);
}
