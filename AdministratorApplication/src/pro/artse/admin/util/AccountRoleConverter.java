package pro.artse.admin.util;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

import pro.artse.dal.dto.AccountRole;

@FacesConverter(value = "accountRoleConverter")
public class AccountRoleConverter extends EnumConverter {

	public AccountRoleConverter() {
		super(AccountRole.class);
	}
}
