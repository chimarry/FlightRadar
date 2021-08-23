package pro.artse.admin.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pro.artse.admin.util.Pages;
import pro.artse.dal.dto.CountryDTO;
import pro.artse.dal.services.ILocationService;
import pro.artse.dal.services.ServiceFactory;

@ManagedBean(name = "countriesBean")
@ViewScoped
public class CountriesBean implements Serializable {

	private static final long serialVersionUID = -5388807456410608388L;

	private ILocationService locationService = ServiceFactory.getLocationService();

	private CountryDTO selectedCountry;
	private CountryDTO newCountry = new CountryDTO();
	private List<CountryDTO> countries = getAll();

	public CountryDTO getSelectedCountry() {
		return selectedCountry;
	}

	public void setSelectedCountry(CountryDTO selectedCountry) {
		this.selectedCountry = selectedCountry;
	}

	public CountryDTO getCountry() {
		return newCountry;
	}

	public void setCountry(CountryDTO country) {
		this.newCountry = country;
	}

	public List<CountryDTO> getCountries() {
		return countries;
	}

	public void setCountries(List<CountryDTO> countries) {
		this.countries = countries;
	}

	public String add() {
		locationService.addCountry(newCountry);
		newCountry = new CountryDTO();
		return refresh();
	}

	public String update() {
		if (selectedCountry != null)
			locationService.updateCountry(selectedCountry);
		return refresh();
	}

	public List<CountryDTO> getAll() {
		return locationService.getCountries();
	}

	public String delete() {
		if (selectedCountry != null)
			locationService.deleteCountry(selectedCountry.getCountryId());
		return refresh();
	}

	private String refresh() {
		setCountries(getAll());
		return Pages.SAME_PAGE;
	}
}
