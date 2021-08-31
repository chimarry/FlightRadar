package pro.artse.employee.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="flights")
public class Flight implements Serializable {
	private static final long serialVersionUID = -3185902752082189618L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="flightId")
	private int flightId;
	
	@Column(name="airportDateTime")
	private LocalDateTime airportDateTime;
	
	@Enumerated(EnumType.STRING)
	private FlightType type;
	
	@ManyToOne
	@JoinColumn(name="arrivalCityId", referencedColumnName="cityId")
	private City arrivalCity;
	
	@ManyToOne
	@JoinColumn(name="departureCityId", referencedColumnName="cityId")
	private City departureCity;
	
	@JsonIgnore
	@OneToMany
	@JoinColumn(name="flightId")
	private List<FlightReservation> flightReservations;

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public LocalDateTime getAirportDateTime() {
		return airportDateTime;
	}

	public void setAirportDateTime(LocalDateTime airportDateTime) {
		this.airportDateTime = airportDateTime;
	}

	public FlightType getType() {
		return type;
	}

	public void setType(FlightType type) {
		this.type = type;
	}

	public City getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(City arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	public List<FlightReservation> getFlightReservations() {
		return flightReservations;
	}

	public void setFlightReservations(List<FlightReservation> flightReservations) {
		this.flightReservations = flightReservations;
	}

	public City getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(City departureCity) {
		this.departureCity = departureCity;
	}
	
}
