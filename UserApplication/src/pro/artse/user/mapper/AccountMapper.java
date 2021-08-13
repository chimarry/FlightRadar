package pro.artse.user.mapper;

import pro.artse.dal.dto.AccountDTO;
import pro.artse.user.beans.AccountBean;

public final class AccountMapper {

	public static AccountDTO mapToDTO(AccountBean source) {
		return new AccountDTO(source.getName(), source.getLastName(), source.getEmail(), source.getUsername(),
				source.getCountry(), source.getAddress(), source.getRole());
	}

	public static AccountBean mapToBean(AccountDTO source) {
		return new AccountBean(source.getAccountId(), source.getName(), source.getLastName(), source.getEmail(),
				source.getUsername(), source.getCountry(), source.getAddress(), source.getRole());
	}
}
