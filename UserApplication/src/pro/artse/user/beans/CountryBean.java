package pro.artse.user.beans;

import java.io.Serializable;

public class CountryBean implements Serializable {

	private static final long serialVersionUID = -7460261143469916743L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
