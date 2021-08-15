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
	private LocalDateTime arrivalOn;
	private LocalDateTime departureOn;
	private String arrivalCityName;
	private String departureCityName;
	private FlightType type;
	private FlightStatus status;
	private String time;

	public FlightBean() {
		super();
	}

	public FlightBean(int flightId, int arrivalCityId, int departureCityId, LocalDateTime arrivalOn,
			LocalDateTime departureOn, String arrivalCityName, String departureCityName, FlightType type) {
		this(arrivalCityId, departureCityId, arrivalOn, departureOn, arrivalCityName, departureCityName, type);
		this.flightId = flightId;
	}

	public FlightBean(int arrivalCityId, int departureCityId, LocalDateTime arrivalOn, LocalDateTime departureOn,
			String arrivalCityName, String departureCityName, FlightType type) {
		this(arrivalCityId, departureCityId, arrivalOn, departureOn, type);
		this.arrivalCityName = arrivalCityName;
		this.departureCityName = departureCityName;
	}

	public FlightBean(int flightId, int arrivalCityId, int departureCityId, LocalDateTime arrivalOn,
			LocalDateTime departureOn, FlightType type) {
		this(arrivalCityId, departureCityId, arrivalOn, departureOn, type);
		this.flightId = flightId;
	}

	public FlightBean(int toCityId, int fromCityId, LocalDateTime arrivalOn, LocalDateTime departureOn,
			FlightType type) {
		this();
		this.arrivalCityId = toCityId;
		this.departureCityId = fromCityId;
		this.arrivalOn = arrivalOn;
		this.departureOn = departureOn;
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

	public String getArrivalCityName() {
		return arrivalCityName;
	}

	public void setArrivalCityName(String arrivalCityName) {
		this.arrivalCityName = arrivalCityName;
	}

	public String getDepartureCityName() {
		return departureCityName;
	}

	public void calculateAndSetTime(boolean isDeparture) {
		if (isDeparture)
			this.time = String.format("%d:%d", departureOn.getHour(), departureOn.getMinute());
		else
			this.time = String.format("%d:%d", arrivalOn.getHour(), arrivalOn.getMinute());
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
