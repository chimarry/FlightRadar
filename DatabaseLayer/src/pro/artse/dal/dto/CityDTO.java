package pro.artse.dal.dto;

public class CityDTO {
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
