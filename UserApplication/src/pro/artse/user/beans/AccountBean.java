package pro.artse.user.beans;

import java.io.Serializable;

import pro.artse.dal.dto.AccountRole;

public class AccountBean implements Serializable {

	private static final long serialVersionUID = 4418335031308490754L;

	private int accountId;
	private String name;
	private String lastName;
	private String username;
	private AccountRole role;
	private boolean isLoggedIn;

	public AccountBean() {

	}

	public AccountBean(int accountId, String name, String lastName, String username, AccountRole role) {
		super();
		this.accountId = accountId;
		this.name = name;
		this.lastName = lastName;
		this.username = username;
		this.role = role;
		this.isLoggedIn = false;
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

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	@Override
	public String toString() {
		return String.format("%s %s", getName(), getLastName());
	}
	
	public static AccountBean createGuest() {
		AccountBean accountBean =  new AccountBean();
		accountBean.setLoggedIn(false);
		return accountBean;
	}
}
