package pro.artse.employee.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pro.artse.employee.entities.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

	@Query(value = "select b from messages b where name='marija'", nativeQuery = true)
	List<Message> getNewerThan(@Param("year") Integer year);
}
