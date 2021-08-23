package pro.artse.admin.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import pro.artse.admin.util.Pages;
import pro.artse.dal.dto.AccountDTO;
import pro.artse.dal.dto.AccountRole;
import pro.artse.dal.errorhandling.DbResultMessage;
import pro.artse.dal.services.IAccountService;
import pro.artse.dal.services.ServiceFactory;

@ManagedBean(name = "accountBean")
@SessionScoped
public class AccountBean implements Serializable {
	private static final long serialVersionUID = -1691819248933794533L;

	private IAccountService accountService = ServiceFactory.getAccountService();

	private String username;
	private String password;
	private String name;
	private String lastName;
	private AccountRole accountRole;

	private boolean isLoggedIn;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public AccountRole getAccountRole() {
		return accountRole;
	}

	public void setAccountRole(AccountRole accountRole) {
		this.accountRole = accountRole;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void checkIfLoggedIn() throws IOException {
		if (!isLoggedIn()) {
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ec.redirect(Pages.INDEX_PAGE);
		}
	}

	public String logIn() {
		DbResultMessage<AccountDTO> isLoggedIn = accountService.login(username, password);
		if (!isLoggedIn.isSuccess() || isLoggedIn.getResult() == null) {
			return Pages.SAME_PAGE;
		}

		AccountDTO account = isLoggedIn.getResult();
		setName(account.getName());
		setLastName(account.getLastName());
		setAccountRole(account.getRole());
		setLoggedIn(true);
		return Pages.HOME_PAGE;
	}

	public String logOut() {
		setLoggedIn(false);
		return Pages.INDEX_PAGE;
	}
}
