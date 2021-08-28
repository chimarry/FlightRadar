package pro.artse.dal.dto;

import java.time.LocalDateTime;

import pro.artse.dal.util.ConfigurationUtil;

public class FlightDTO {
	private int flightId;
	private int arrivalCityId;
	private int departureCityId;
	private LocalDateTime airportDateTime;
	private String arrivalCityName;
	private String departureCityName;
	private FlightType type;
	private FlightStatus status;

	public FlightDTO() {
		super();
	}

	public FlightDTO(int flightId, int arrivalCityId, int departureCityId, LocalDateTime airportDateTime,
			String arrivalCityName, String departureCityName, FlightType type) {
		this(arrivalCityId, departureCityId, airportDateTime, arrivalCityName, departureCityName, type);
		this.flightId = flightId;
	}

	public FlightDTO(int arrivalCityId, int departureCityId, LocalDateTime airportDateTime, String arrivalCityName,
			String departureCityName, FlightType type) {
		this(arrivalCityId, departureCityId, airportDateTime, type);
		this.arrivalCityName = arrivalCityName;
		this.departureCityName = departureCityName;
	}

	public FlightDTO(int flightId, int arrivalCityId, int departureCityId, LocalDateTime airportDateTime,
			FlightType type) {
		this(arrivalCityId, departureCityId, airportDateTime, type);
		this.flightId = flightId;
	}

	public FlightDTO(int toCityId, int fromCityId, LocalDateTime airportDateTime, FlightType type) {
		this();
		this.arrivalCityId = toCityId;
		this.departureCityId = fromCityId;
		this.airportDateTime = airportDateTime;
		this.type = type;
	}

	public String getArrivalCityName() {
		return arrivalCityName;
	}

	public void setArrivalCityName(String arrivalCityName) {
		this.arrivalCityName = arrivalCityName;
	}

	public String getDepartureCityName() {
		return departureCityName;
	}

	public void setDepartureCityName(String departureCityName) {
		this.departureCityName = departureCityName;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public int getArrivalCityId() {
		return arrivalCityId;
	}

	public void setArrivalCityId(int toCityId) {
		this.arrivalCityId = toCityId;
	}

	public int getDepartureCityId() {
		return departureCityId;
	}

	public void setDepartureCityId(int fromCityId) {
		this.departureCityId = fromCityId;
	}

	public LocalDateTime getAirportDateTime() {
		return this.airportDateTime;
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

	public FlightStatus getStatus() {
		return status;
	}

	public void setStatus(FlightStatus status) {
		this.status = status;
	}

	public void setStatusBasedOnTime() {
		boolean isDeparture = true;
		if(arrivalCityId == ConfigurationUtil.getNumber("cityId"))
			isDeparture = false;
		LocalDateTime currentDateTime = LocalDateTime.now();
			
		if (isDeparture && currentDateTime.isAfter(airportDateTime))
			status = FlightStatus.Departured;
		else if (!isDeparture && currentDateTime.isAfter(airportDateTime))
			status = FlightStatus.Arrived;
		else
			status = FlightStatus.Waiting;
	}
}