package pro.artse.employee.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pro.artse.employee.entities.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
	
}