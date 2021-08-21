package pro.artse.admin.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import pro.artse.admin.util.Pages;
import pro.artse.dal.dto.CityDTO;
import pro.artse.dal.services.ILocationService;
import pro.artse.dal.services.ServiceFactory;

@ManagedBean(name = "citiesBean")
@ViewScoped
public class CitiesBean implements Serializable {

	private static final long serialVersionUID = -2468079231832110262L;

	private ILocationService locationService = ServiceFactory.getLocationService();

	private int countryId;
	private List<CityDTO> cities;
	private CityDTO selectedCity;
	private CityDTO newCity = new CityDTO();

	public CitiesBean() {
		Map<String, String> reqMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if (reqMap.containsKey("countryId"))
			countryId = Integer.parseInt(reqMap.get("countryId"));
		System.out.println(countryId);
		setCities(getAll());
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public List<CityDTO> getCities() {
		return cities;
	}

	public void setCities(List<CityDTO> cities) {
		this.cities = cities;
	}

	public CityDTO getSelectedCity() {
		return selectedCity;
	}

	public void setSelectedCity(CityDTO selectedCity) {
		this.selectedCity = selectedCity;
	}

	public CityDTO getNewCity() {
		return newCity;
	}

	public void setNewCity(CityDTO newCity) {
		this.newCity = newCity;
	}

	public List<CityDTO> getAll() {
		return locationService.getCities(countryId);
	}

	public String add() {
		if (newCity != null) {
			newCity.setCountryId(countryId);
			System.out.println(locationService.addCity(newCity).getStatus());
			newCity = new CityDTO();
		}
		setCities(getAll());
		return Pages.SAME_PAGE;
	}

	public String update() {
		if (selectedCity != null)
			locationService.updateCity(selectedCity);

		setCities(getAll());
		return Pages.SAME_PAGE;
	}

	public String delete() {
		if (selectedCity != null)
			locationService.deleteCity(selectedCity.getCityId());

		setCities(getAll());
		return Pages.SAME_PAGE;
	}

	public String goBack() {
		return Pages.COUNTRIES_PAGE;
	}
}
