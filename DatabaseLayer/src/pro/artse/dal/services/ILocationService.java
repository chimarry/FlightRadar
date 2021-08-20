package pro.artse.dal.services;

import java.util.List;

import pro.artse.dal.dto.*;
import pro.artse.dal.errorhandling.DbResultMessage;

public interface ILocationService {
	
	public List<CountryDTO> getCountries();

	public List<CityDTO> getCities(int countryId);
	
	public DbResultMessage<Boolean> deleteCountry(int countryId);
	
	public DbResultMessage<Boolean> deleteCity(int cityId);
	
	public DbResultMessage<Boolean> addCountry(CountryDTO country);
	
	public DbResultMessage<Boolean> addCity(CityDTO city);
	
	public DbResultMessage<Boolean> updateCountry(CountryDTO newInfo);
	
	public DbResultMessage<Boolean> updateCity(CityDTO newInfo);
}
