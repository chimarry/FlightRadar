package pro.artse.employee.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.artse.employee.entities.Message;
import pro.artse.employee.repositories.MessageRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;
	
	public List<Message> getAll(boolean read){
		return read ? messageRepository.findByReadOnIsNotNullOrderByCreatedOnDesc()
				: messageRepository.findByReadOnIsNullOrderByCreatedOnDesc();
	} 
}
