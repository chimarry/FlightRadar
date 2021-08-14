package pro.artse.user.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class CountriesBean implements Serializable{

	private static final long serialVersionUID = 1443531226332354637L;
	
	private ArrayList<CountryBean> countries;

	public ArrayList<CountryBean> getCountries() {
		return countries;
	}

	public void setCountries(ArrayList<CountryBean> countries) {
		this.countries = countries;
	}
}
