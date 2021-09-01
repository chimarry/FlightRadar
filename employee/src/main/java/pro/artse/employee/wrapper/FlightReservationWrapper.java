package pro.artse.employee.wrapper;

import java.io.Serializable;
import java.time.LocalDateTime;

import pro.artse.employee.entities.FlightReservationStatus;
import pro.artse.employee.entities.FlightType;


public class FlightReservationWrapper implements Serializable{
	
	private static final long serialVersionUID = -7580440658968938074L;
	
	private int accountId;
	private int flightReservationId;
	private LocalDateTime airportDateTime;
	private String arrivalCityName;
	private String arrivalCountryName;
	private String departureCityName;
	private String departureCountryName;
	private LocalDateTime createdOn;
	private FlightReservationStatus status;
	private FlightType flightType;
	private String username;
	
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getFlightReservationId() {
		return flightReservationId;
	}
	public void setFlightReservationId(int flightReservationId) {
		this.flightReservationId = flightReservationId;
	}
	public LocalDateTime getAirportDateTime() {
		return airportDateTime;
	}
	public void setAirportDateTime(LocalDateTime airportDateTime) {
		this.airportDateTime = airportDateTime;
	}
	public String getArrivalCityName() {
		return arrivalCityName;
	}
	public void setArrivalCityName(String arrivalCityName) {
		this.arrivalCityName = arrivalCityName;
	}
	public String getArrivalCountryName() {
		return arrivalCountryName;
	}
	public void setArrivalCountryName(String arrivalCountryName) {
		this.arrivalCountryName = arrivalCountryName;
	}
	public String getDepartureCityName() {
		return departureCityName;
	}
	public void setDepartureCityName(String departureCityName) {
		this.departureCityName = departureCityName;
	}
	public String getDepartureCountryName() {
		return departureCountryName;
	}
	public void setDepartureCountryName(String departureCountryName) {
		this.departureCountryName = departureCountryName;
	}
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
	public FlightReservationStatus getStatus() {
		return status;
	}
	public void setStatus(FlightReservationStatus status) {
		this.status = status;
	}
	public FlightType getFlightType() {
		return flightType;
	}
	public void setFlightType(FlightType flightType) {
		this.flightType = flightType;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
