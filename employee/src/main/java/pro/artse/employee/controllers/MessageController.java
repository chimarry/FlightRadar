package pro.artse.employee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pro.artse.employee.entities.Message;
import pro.artse.employee.entities.MessageStatus;
import pro.artse.employee.services.MessageService;
import pro.artse.employee.wrapper.MessageWrapper;

@RestController
@RequestMapping("/api/v0.1/messages")
public class MessageController {

	@Autowired
	private MessageService messageService;

	@GetMapping
	public ResponseEntity<List<Message>> getAll(@RequestParam(required = false) List<MessageStatus> filters) {
		return ResponseEntity.ok(messageService.getAll(filters));
	}

	@PutMapping("/{messageId}")
	public ResponseEntity<Boolean> read(@PathVariable int messageId) {
		return ResponseEntity.ok(messageService.read(messageId));
	}

	@PostMapping
	public ResponseEntity<Boolean> reply(@RequestBody MessageWrapper messageWrapper) {
		messageService.sendMessage(messageWrapper);
		return ResponseEntity.ok(true);
	}
}
