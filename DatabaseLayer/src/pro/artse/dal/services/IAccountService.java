package pro.artse.dal.services;

import pro.artse.dal.dto.AccountDTO;
import pro.artse.dal.errorhandling.DBResultMessage;

public interface IAccountService {

	public DBResultMessage<Boolean> register(AccountDTO account);

	public DBResultMessage<Boolean> login(String username, String password);
}
