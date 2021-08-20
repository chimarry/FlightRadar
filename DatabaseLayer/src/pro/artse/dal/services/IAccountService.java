package pro.artse.dal.services;

import java.util.List;

import pro.artse.dal.dto.AccountDTO;
import pro.artse.dal.dto.AccountRole;
import pro.artse.dal.dto.UserDTO;
import pro.artse.dal.errorhandling.DbResultMessage;

public interface IAccountService {

	public DbResultMessage<Boolean> register(UserDTO user, String password);

	public DbResultMessage<AccountDTO> login(String username, String password);

	public DbResultMessage<Boolean> addEmployee(AccountDTO account, String password);

	public DbResultMessage<Boolean> updateEmployee(AccountDTO account, String password);

	public DbResultMessage<Boolean> updateUser(UserDTO user, String password);

	public DbResultMessage<Boolean> deleteUser(int accountId);

	public DbResultMessage<Boolean> deleteEmployee(int accountId);

	public List<? extends AccountDTO> getAll(AccountDTO account, AccountRole role);
}
