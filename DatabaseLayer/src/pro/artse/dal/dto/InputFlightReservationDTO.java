package pro.artse.dal.dto;

import java.time.LocalDate;

public class InputFlightReservationDTO {
	private int accountId;
	private LocalDate departureDate;
	private int arrivalCityId;
	private int departureCityId;
	private int seatNumber;
	private String cargoDescription;
	private String fileSpecificationName;
	private byte[] fileSpecification;
	
	public InputFlightReservationDTO(LocalDate departureDate, int arrivalCityId, int departureCityId) {
		super();
		this.departureDate = departureDate;
		this.arrivalCityId = arrivalCityId;
		this.departureCityId = departureCityId;
	}
	
	public LocalDate getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
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

	public byte[] getFileSpecification() {
		return fileSpecification;
	}

	public void setFileSpecification(byte[] fileSpecification) {
		this.fileSpecification = fileSpecification;
	}

	public String getFileSpecificationName() {
		return fileSpecificationName;
	}

	public void setFileSpecificationName(String fileSpecificationName) {
		this.fileSpecificationName = fileSpecificationName;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
}
