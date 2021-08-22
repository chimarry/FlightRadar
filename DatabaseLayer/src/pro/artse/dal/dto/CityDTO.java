package pro.artse.dal.dto;

import java.io.Serializable;

public class CityDTO implements Serializable {
	private static final long serialVersionUID = -1339002464083041366L;
	private int cityId;
	private String name;
	private int countryId;

	public CityDTO() {

	}

	public CityDTO(int cityId, String name) {
		super();
		this.cityId = cityId;
		this.name = name;
	}

	public CityDTO(int countryId, int cityId, String name) {
		this(cityId, name);
		this.countryId = countryId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

}
