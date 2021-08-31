package pro.artse.employee.wrapper;

import java.io.Serializable;

public class MessageWrapper implements Serializable{
	private static final long serialVersionUID = -1373505388357033615L;
	
	private String email;
	private String title;
	private String content;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
