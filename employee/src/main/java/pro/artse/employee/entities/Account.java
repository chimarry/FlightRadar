package pro.artse.employee.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "accounts")
public class Account implements Serializable {
	private static final long serialVersionUID = 9024074550274695954L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="accountId")
	private int accountId;
	
	private String name;
	
	@Column(name="lastname")
	private String lastName;
	
	private String username;

	@Enumerated(EnumType.STRING)
	@Column(name="accountRole")
	private AccountRole role;
	
	@JsonIgnore
	private byte[] password;
	
	@JsonIgnore
	@OneToMany
	@JoinColumn(name="accountId")
	private List<FlightReservation> flightReservations;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public AccountRole getRole() {
		return role;
	}

	public void setRole(AccountRole role) {
		this.role = role;
	}

	public List<FlightReservation> getFlightReservations() {
		return flightReservations;
	}

	public void setFlightReservations(List<FlightReservation> flightReservations) {
		this.flightReservations = flightReservations;
	}

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}
}
