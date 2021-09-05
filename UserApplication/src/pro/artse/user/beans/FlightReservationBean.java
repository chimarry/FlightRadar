package pro.artse.user.beans;

import java.io.Serializable;
import java.time.LocalDateTime;

import pro.artse.dal.dto.FlightReservationStatus;

public class FlightReservationBean implements Serializable {

	private static final long serialVersionUID = -1782171914069334882L;

	private int accountId;
	private int flightReservationId;
	private LocalDateTime airportDateTime;
	private String arrivalCityName;
	private String arrivalCountryName;
	private String departureCityName;
	private String departureCountryName;
	private FlightReservationStatus status;
	private LocalDateTime createdOn;
	private String cancellationReason;
	private int seatNumber;
	private String cargoDescription;
	private String fileSpecificationName;

	public FlightReservationBean(int accountId, int flightReservationId, LocalDateTime airportDateTime,
			String arrivalCityName, String arrivalCountryName, String depatureCityName, String departureCountryName,
			FlightReservationStatus status, LocalDateTime createdOn, int seatNumber, String cargoDescription,
			String fileSpecificationName) {
		super();
		this.accountId = accountId;
		this.flightReservationId = flightReservationId;
		this.airportDateTime = airportDateTime;
		this.arrivalCityName = arrivalCityName;
		this.arrivalCountryName = arrivalCountryName;
		this.departureCityName = depatureCityName;
		this.departureCountryName = departureCountryName;
		this.status = status;
		this.createdOn = createdOn;
		this.seatNumber = seatNumber;
		this.cargoDescription = cargoDescription;
		this.fileSpecificationName = fileSpecificationName;
	}

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

	public void setDepartureCityName(String depatureCityName) {
		this.departureCityName = depatureCityName;
	}

	public String getDepartureCountryName() {
		return departureCountryName;
	}

	public void setDepartureCountryName(String departureCountryName) {
		this.departureCountryName = departureCountryName;
	}

	public FlightReservationStatus getStatus() {
		return status;
	}

	public void setStatus(FlightReservationStatus status) {
		this.status = status;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getCargoDescription() {
		return cargoDescription;
	}

	public void setCargoDescription(String cargoDescription) {
		this.cargoDescription = cargoDescription;
	}

	public String getFileSpecificationName() {
		return fileSpecificationName;
	}

	public void setFileSpecificationName(String fileSpecificationName) {
		this.fileSpecificationName = fileSpecificationName;
	}

	public String getCancellationReason() {
		return cancellationReason;
	}

	public void setCancellationReason(String cancellationReason) {
		this.cancellationReason = cancellationReason;
	}
}
