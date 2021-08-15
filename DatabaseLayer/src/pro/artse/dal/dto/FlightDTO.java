package pro.artse.dal.dto;

import java.time.LocalDateTime;

public class FlightDTO {
	private int flightId;
	private int arrivalCityId;
	private int departureCityId;
	private LocalDateTime arrivalOn;
	private LocalDateTime departureOn;
	private String arrivalCityName;
	private String departureCityName;
	private FlightType type;
	private FlightStatus status;
	
	public FlightDTO() {
		super();
	}
	
	public FlightDTO(int flightId, int arrivalCityId, int departureCityId, LocalDateTime arrivalOn,
			LocalDateTime departureOn, String arrivalCityName, String departureCityName, FlightType type) {
		this(arrivalCityId, departureCityId, arrivalOn, departureOn, arrivalCityName, departureCityName, type);
		this.flightId = flightId;
	}

	public FlightDTO(int arrivalCityId, int departureCityId, LocalDateTime arrivalOn, LocalDateTime departureOn,
			String arrivalCityName, String departureCityName, FlightType type) {
		this(arrivalCityId, departureCityId, arrivalOn, departureOn, type);
		this.arrivalCityName = arrivalCityName;
		this.departureCityName = departureCityName;
	}

	public FlightDTO(int flightId, int arrivalCityId, int departureCityId, LocalDateTime arrivalOn, LocalDateTime departureOn,
			FlightType type) {
		this(arrivalCityId, departureCityId, arrivalOn, departureOn, type);
		this.flightId = flightId;
	}

	public FlightDTO(int toCityId, int fromCityId, LocalDateTime arrivalOn, LocalDateTime departureOn,
			FlightType type) {
		this();
		this.arrivalCityId = toCityId;
		this.departureCityId = fromCityId;
		this.arrivalOn = arrivalOn;
		this.departureOn = departureOn;
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

	public LocalDateTime getArrivalOn() {
		return arrivalOn;
	}

	public void setArrivalOn(LocalDateTime arrivalOn) {
		this.arrivalOn = arrivalOn;
	}

	public LocalDateTime getDepartureOn() {
		return departureOn;
	}

	public void setDepartureOn(LocalDateTime departureOn) {
		this.departureOn = departureOn;
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
		LocalDateTime currentDateTime = LocalDateTime.now();
		if (currentDateTime.isAfter(arrivalOn))
			status = FlightStatus.Arrived;
		else if (currentDateTime.isAfter(departureOn))
			status = FlightStatus.Departured;
		else
			status = FlightStatus.Waiting;
	}
}