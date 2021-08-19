package pro.artse.admin.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="accountBean")
@SessionScoped
public class AccountBean implements Serializable{
	private static final long serialVersionUID = -1691819248933794533L;
	
	private String username;
	private String password;
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
	
	public String logIn() {
		isLoggedIn = !isLoggedIn;
		return "index.xhtml";
	}
}
