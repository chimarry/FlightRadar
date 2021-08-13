package pro.artse.dal.services;

import pro.artse.dal.dto.AccountDTO;
import pro.artse.dal.errorhandling.DbResultMessage;

public interface IAccountService {

	public DbResultMessage<Boolean> register(AccountDTO account, String password);

	public DbResultMessage<AccountDTO> login(String username, String password);
}
