package pro.artse.user.mapper;

import javax.servlet.http.HttpServletRequest;

import pro.artse.dal.dto.AccountDTO;
import pro.artse.dal.dto.AccountRole;
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
	
	public static AccountDTO mapFromRequest(HttpServletRequest request) {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setName(request.getParameter("name"));
		accountDTO.setLastName(request.getParameter("lastName"));
		accountDTO.setUsername(request.getParameter("username"));
		accountDTO.setCountry(request.getParameter("country"));
		accountDTO.setEmail(request.getParameter("email"));
		accountDTO.setAddress(request.getParameter("address"));

		boolean isPassenger = request.getParameter("passenger") != null
				&& request.getParameter("passenger").equals("on");
		accountDTO.setRole(isPassenger ? AccountRole.Passenger : AccountRole.Transport);
		return accountDTO;
	}
}
