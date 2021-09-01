package pro.artse.employee.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.artse.employee.entities.Message;
import pro.artse.employee.entities.MessageStatus;
import pro.artse.employee.repositories.MessageRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;

	public List<Message> getAll(List<MessageStatus> filters) {
		if (filters == null || (filters.contains(MessageStatus.Read) && filters.contains(MessageStatus.NotRead)))
			return messageRepository.findAll();
		else if (filters.contains(MessageStatus.Read))
			return messageRepository.findByReadOnIsNotNullOrderByCreatedOnDesc();
		return messageRepository.findByReadOnIsNullOrderByCreatedOnDesc();
	}
	
	public Boolean read(int messageId) {
		Message message = messageRepository.findById(messageId).get();
		message.setReadOn(LocalDateTime.now());
		messageRepository.saveAndFlush(message);
		return true;
	}
}
