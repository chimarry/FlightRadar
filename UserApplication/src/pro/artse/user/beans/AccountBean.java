package pro.artse.user.beans;

import java.io.Serializable;

import pro.artse.dal.dto.AccountRole;

public class AccountBean implements Serializable {

	private static final long serialVersionUID = 4418335031308490754L;

	private int accountId;
	private String name;
	private String lastName;
	private String email;
	private String username;
	private String country;
	private String address;
	private AccountRole role;

	public AccountBean() {

	}

	public AccountBean(int accountId, String name, String lastName, String email, String username, String country,
			String address, AccountRole role) {
		super();
		this.accountId = accountId;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.country = country;
		this.address = address;
		this.role = role;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public AccountRole getRole() {
		return role;
	}

	public void setRole(AccountRole role) {
		this.role = role;
	}
}
