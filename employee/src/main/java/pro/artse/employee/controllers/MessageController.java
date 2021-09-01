package pro.artse.employee.controllers;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pro.artse.employee.entities.Message;
import pro.artse.employee.entities.MessageStatus;
import pro.artse.employee.services.MessageService;

@RestController
@RequestMapping("/api/v0.1/messages")
public class MessageController {

	@Autowired
	private MessageService messageService;

	@GetMapping
	public ResponseEntity<List<Message>> getAll(@RequestParam(required = false) List<MessageStatus> filters) {
		if (filters != null)
			System.out.println(Stream.of(filters).map(x -> x.toString()).reduce((x, y) -> x + " " + y));
		else
			System.out.println("Null");
		return ResponseEntity.ok(messageService.getAll(filters));
	}
}
