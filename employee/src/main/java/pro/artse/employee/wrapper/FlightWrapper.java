package pro.artse.employee.wrapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import pro.artse.employee.entities.FlightType;

public class FlightWrapper implements Serializable{
	private static final long serialVersionUID = -1500181819892368406L;
	
	private int arrivalCityId;
	private int departureCityId;
	private FlightType type;
	private List<LocalDateTime> airportDateTimes;
	
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
	public List<LocalDateTime> getAirportDateTimes() {
		return airportDateTimes;
	}
	public void setAirportDateTimes(List<LocalDateTime> airportDateTimes) {
		this.airportDateTimes = airportDateTimes;
	}
	public FlightType getType() {
		return type;
	}
	public void setType(FlightType type) {
		this.type = type;
	}
}
