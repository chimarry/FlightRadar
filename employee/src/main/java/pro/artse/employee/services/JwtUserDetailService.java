package pro.artse.employee.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pro.artse.employee.entities.Account;
import pro.artse.employee.entities.AccountRole;
import pro.artse.employee.repositories.AccountRepository;

@Service
public class JwtUserDetailService implements UserDetailsService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByUsername(username);
		if (account == null)
			throw new UsernameNotFoundException("User not found with username: " + username);
		if (account.getRole() != AccountRole.Employee)
			throw new UsernameNotFoundException("Not empolyee");
		return new User(account.getUsername(), new String(account.getPassword()), new ArrayList<>());
	}
}