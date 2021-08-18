package pro.artse.dal.dto;

import java.time.LocalDateTime;

public class MessageDTO {
	private int messageId;
	private String text;
	private String email;
	private String name;
	private LocalDateTime createdOn;
	private LocalDateTime readOn;

	public MessageDTO(int messageId, String text, String email, String name, LocalDateTime createdOn,
			LocalDateTime readOn) {
		this(text, email, name);
		this.messageId = messageId;
		this.createdOn = createdOn;
		this.readOn = readOn;
	}

	public MessageDTO(String text, String email, String name) {
		super();
		this.text = text;
		this.email = email;
		this.name = name;
		this.createdOn = LocalDateTime.now();
		this.readOn = null;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDateTime getReadOn() {
		return readOn;
	}

	public void setReadOn(LocalDateTime readOn) {
		this.readOn = readOn;
	}
}
