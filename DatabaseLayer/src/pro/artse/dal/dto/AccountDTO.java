package pro.artse.dal.dto;

import java.io.Serializable;

public class AccountDTO implements Serializable{

	private static final long serialVersionUID = 9024074550274695954L;
	private int accountId;
	private String name;
	private String lastName;
	private String username;
	private AccountRole role;

	public AccountDTO() {
	}

	public AccountDTO(int accountId, String name, String lastName, String username, AccountRole role) {
		super();
		this.accountId = accountId;
		this.name = name;
		this.lastName = lastName;
		this.username = username;
		this.role = role;
	}

	public AccountDTO(String name, String lastName, String username, AccountRole role) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.username = username;
		this.role = role;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int id) {
		accountId = id;
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
}
