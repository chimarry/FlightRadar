package pro.artse.employee.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="flightReservations")
public class FlightReservation implements Serializable{
	
	private static final long serialVersionUID = 121341602214286640L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="flightReservationId")
	private int flightReservationId;
	
	@Column(name="seatnumber")
	private Integer seatNumber = 0;

	@Column(name="description")
	private String cargoDescription;
	
	@Column(name="fileSpecificationUri")
	private String fileSpecificationUri;
	
	@Enumerated(EnumType.STRING)
	private FlightReservationStatus status;
	
	@Column(name="createdOn")
	private LocalDateTime createdOn;
	
	@ManyToOne
	@JoinColumn(name = "accountId")
	private Account account;
	
	@ManyToOne
	@JoinColumn(name = "flightId")
	private Flight flight;

	public int getFlightReservationId() {
		return flightReservationId;
	}

	public void setFlightReservationId(int flightReservationId) {
		this.flightReservationId = flightReservationId;
	}

	public Integer getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(Integer seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getCargoDescription() {
		return cargoDescription;
	}

	public void setCargoDescription(String cargoDescription) {
		this.cargoDescription = cargoDescription;
	}

	public String getFileSpecificationUri() {
		return fileSpecificationUri;
	}

	public void setFileSpecificationUri(String fileSpecificationUri) {
		this.fileSpecificationUri = fileSpecificationUri;
	}

	public FlightReservationStatus getStatus() {
		return status;
	}

	public void setStatus(FlightReservationStatus status) {
		this.status = status;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
}
