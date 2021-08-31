package pro.artse.employee.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pro.artse.employee.entities.Country;
import pro.artse.employee.entities.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

	List<Message> findByReadOnIsNotNullOrderByCreatedOnDesc();
	
	List<Message> findByReadOnIsNullOrderByCreatedOnDesc();
}
