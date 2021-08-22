package pro.artse.admin.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pro.artse.admin.util.Pages;
import pro.artse.dal.dto.AccountRole;
import pro.artse.dal.dto.UserDTO;
import pro.artse.dal.services.IAccountService;
import pro.artse.dal.services.ServiceFactory;

@ManagedBean(name = "usersBean")
@ViewScoped
public class UsersBean implements Serializable {

	private static final long serialVersionUID = 7465760282089076952L;

	private IAccountService accountService = ServiceFactory.getAccountService();

	private List<UserDTO> users = getAll();
	private UserDTO selectedUser;
	private UserDTO newUser = new UserDTO();
	private AccountRole[] possibleRoles = new AccountRole[] { AccountRole.Passenger, AccountRole.Transport };

	private String password;
	private String confirmPassword;

	public List<UserDTO> getUsers() {
		return users;
	}

	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}

	public UserDTO getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(UserDTO selectedUser) {
		this.selectedUser = selectedUser;
	}

	public UserDTO getNewUser() {
		return newUser;
	}

	public void setNewUser(UserDTO newUser) {
		this.newUser = newUser;
	}

	public AccountRole[] getPossibleRoles() {
		return possibleRoles;
	}

	public void setPossibleRoles(AccountRole[] possibleRoles) {
		this.possibleRoles = possibleRoles;
	}

	public List<UserDTO> getAll() {
		return accountService.getUsers();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String add() {
		if (newUser != null && password != null && password.equals(confirmPassword)) {
			accountService.register(newUser, password);
			newUser = new UserDTO();
		}
		setUsers(getAll());
		setPassword(null);
		return Pages.SAME_PAGE;
	}

	public String update() {
		if (selectedUser != null) {
			if (password == null || password.isBlank())
				setPassword(null);
			accountService.updateUser(selectedUser, getPassword());
		}
		setUsers(getAll());
		setPassword(null);
		return Pages.SAME_PAGE;
	}

	public String delete() {
		if (selectedUser != null) {
			accountService.deleteEmployee(selectedUser.getAccountId());
		}
		setUsers(getAll());
		return Pages.SAME_PAGE;
	}
}
