package pro.artse.dal.dto;

public class CityDTO {
	private int cityId;
	private String name;
	
	public CityDTO() {
		
	}
	
	public CityDTO(int cityId, String name) {
		super();
		this.cityId = cityId;
		this.name = name;
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
	
}
