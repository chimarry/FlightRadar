package pro.artse.dal.dto;

public class CountryDTO {
	private int countryId;
	private String name;

	public CountryDTO() {

	}

	public CountryDTO(int countryId, String name) {
		super();
		this.countryId = countryId;
		this.name = name;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
