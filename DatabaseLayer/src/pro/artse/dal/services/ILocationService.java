package pro.artse.dal.services;

import java.util.List;

import pro.artse.dal.dto.*;

public interface ILocationService {
	
	public List<CountryDTO> getCountries();

	public List<CityDTO> getCities(int countryId);
}
