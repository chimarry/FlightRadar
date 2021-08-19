package pro.artse.admin.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "testBean")
public class Test implements Serializable {
	private static final long serialVersionUID = -2805976563581110436L;

	private String name;
	private String message;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String writeName() {
		setMessage( "Ovo je poruka: " + name);
		return "index.xhtml";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
