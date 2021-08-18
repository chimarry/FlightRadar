package pro.artse.dal.services;

import pro.artse.dal.dto.MessageDTO;
import pro.artse.dal.errorhandling.DbResultMessage;

public interface IMessageService {
	public DbResultMessage<Boolean> sent(MessageDTO message);
}
