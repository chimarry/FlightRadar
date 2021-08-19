package pro.artse.dal.services;

import pro.artse.dal.dto.AccountDTO;
import pro.artse.dal.dto.UserDTO;
import pro.artse.dal.errorhandling.DbResultMessage;

public interface IAccountService {

	public DbResultMessage<Boolean> register(UserDTO user, String password);

	public DbResultMessage<AccountDTO> login(String username, String password);
}
