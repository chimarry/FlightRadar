package pro.artse.employee.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pro.artse.employee.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{

	public Account findByUsername(String username);
}
