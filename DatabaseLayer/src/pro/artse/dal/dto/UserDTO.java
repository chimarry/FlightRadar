package pro.artse.dal.dto;

public class UserDTO extends AccountDTO{
	private String email;
	private String country;
	private String address;
	
	public UserDTO(int accountId, String name, String lastName, String username, AccountRole role, String email,
			String country, String address) {
		super(accountId, name, lastName, username, role);
		this.email = email;
		this.country = country;
		this.address = address;
	}

	public UserDTO(String name, String lastName, String username, AccountRole role, String email, String country,
			String address) {
		super(name, lastName, username, role);
		this.email = email;
		this.country = country;
		this.address = address;
	}

	public UserDTO(String email, String country, String address) {
		super();
		this.email = email;
		this.country = country;
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
}
