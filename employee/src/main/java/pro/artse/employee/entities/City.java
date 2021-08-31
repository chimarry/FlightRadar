package pro.artse.employee.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "cities")
public class City implements Serializable {

	private static final long serialVersionUID = -8254019101496371555L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cityId")
	private int cityId;

	private String name;

	@ManyToOne
	@JoinColumn(name = "countryId")
	private Country country;

	@JsonIgnore
	@OneToMany
	@JoinColumn(name = "departureCityId")
	private List<Flight> departureFlights;

	@JsonIgnore
	@OneToMany
	@JoinColumn(name = "arrivalCityId")
	private List<Flight> arrivalFlights;

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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Flight> getDepartureFlights() {
		return departureFlights;
	}

	public void setDepartureFlights(List<Flight> departureFlights) {
		this.departureFlights = departureFlights;
	}

	public List<Flight> getArrivalFlights() {
		return arrivalFlights;
	}

	public void setArrivalFlights(List<Flight> arrivalFlights) {
		this.arrivalFlights = arrivalFlights;
	}
}
