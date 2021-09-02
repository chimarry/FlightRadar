package pro.artse.employee.wrapper;

import java.io.Serializable;

import pro.artse.employee.entities.City;

public class CityWrapper implements Serializable {

	private static final long serialVersionUID = 2610900948443619498L;

	private int cityId;
	
	private String name;

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
	
	public static CityWrapper fromEntity(City city) {
		CityWrapper cityWrapper  = new CityWrapper();
		cityWrapper.setCityId(city.getCityId());
		cityWrapper.setName(city.getName());
		return cityWrapper;
	}
}
