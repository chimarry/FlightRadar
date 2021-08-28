package pro.artse.user.beans;

import java.io.Serializable;
import java.time.LocalDateTime;

import pro.artse.dal.dto.FlightStatus;
import pro.artse.dal.dto.FlightType;

public class FlightBean implements Serializable {

	private static final long serialVersionUID = 8314585840525561959L;

	private int flightId;
	private int arrivalCityId;
	private int departureCityId;
	private LocalDateTime airportDateTime;
	private String arrivalCityName;
	private String departureCityName;
	private FlightType type;
	private FlightStatus status;
	private String time;

	public FlightBean() {
		super();
	}

	public FlightBean(int flightId, int arrivalCityId, int departureCityId, LocalDateTime airportDateTime,
			String arrivalCityName, String departureCityName, FlightType type) {
		this(arrivalCityId, departureCityId, airportDateTime, arrivalCityName, departureCityName, type);
		this.flightId = flightId;
	}

	public FlightBean(int arrivalCityId, int departureCityId, LocalDateTime airportDateTime, String arrivalCityName,
			String departureCityName, FlightType type) {
		this(arrivalCityId, departureCityId, airportDateTime, type);
		this.arrivalCityName = arrivalCityName;
		this.departureCityName = departureCityName;
	}

	public FlightBean(int flightId, int arrivalCityId, int departureCityId, LocalDateTime aiportDateTime,
			FlightType type) {
		this(arrivalCityId, departureCityId, aiportDateTime, type);
		this.flightId = flightId;
	}

	public FlightBean(int toCityId, int fromCityId, LocalDateTime aiportDateTime, FlightType type) {
		this();
		this.arrivalCityId = toCityId;
		this.departureCityId = fromCityId;
		this.airportDateTime = aiportDateTime;
		this.type = type;
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

	public void setArrivalCityId(int arrivalCityId) {
		this.arrivalCityId = arrivalCityId;
	}

	public int getDepartureCityId() {
		return departureCityId;
	}

	public void setDepartureCityId(int departureCityId) {
		this.departureCityId = departureCityId;
	}

	public LocalDateTime getAirportDateTime() {
		return airportDateTime;
	}

	public void setAirportDateTime(LocalDateTime arrivalOn) {
		this.airportDateTime = arrivalOn;
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

	public void calculateAndSetTime() {
		this.time = String.format("%d:%d", airportDateTime.getHour(), airportDateTime.getMinute());
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

	public void setTime(String time) {
		this.time = time;
	}

	public String getTime() {
		return time;
	}
}
