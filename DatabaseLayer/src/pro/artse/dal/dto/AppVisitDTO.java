package pro.artse.dal.dto;

import java.io.Serializable;

public class AppVisitDTO implements Serializable {
	private static final long serialVersionUID = -8899840396540459574L;

	private String visitedOn;
	private int count;

	public AppVisitDTO() {

	}

	public AppVisitDTO(int count, String visitedOn) {
		super();
		this.visitedOn = visitedOn;
		this.count = count;
	}

	public String getVisitedOn() {
		return visitedOn;
	}

	public void setVisitedOn(String visitedOn) {
		this.visitedOn = visitedOn;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
