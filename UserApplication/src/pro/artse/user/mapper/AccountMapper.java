package pro.artse.user.mapper;

import javax.servlet.http.HttpServletRequest;

import pro.artse.dal.dto.AccountDTO;
import pro.artse.dal.dto.AccountRole;
import pro.artse.dal.dto.UserDTO;
import pro.artse.user.beans.AccountBean;

public final class AccountMapper {

	public static AccountBean mapToBean(AccountDTO source) {
		return new AccountBean(source.getAccountId(), source.getName(), source.getLastName(), source.getUsername(),
				source.getRole());
	}

	public static UserDTO mapFromRequest(HttpServletRequest request) {
		UserDTO accountDTO = new UserDTO(request.getParameter("email"), request.getParameter("country"),
				request.getParameter("address"));
		accountDTO.setName(request.getParameter("name"));
		accountDTO.setLastName(request.getParameter("lastName"));
		accountDTO.setUsername(request.getParameter("username"));

		boolean isPassenger = request.getParameter("passenger") != null
				&& request.getParameter("passenger").equals("on");
		accountDTO.setRole(isPassenger ? AccountRole.Passenger : AccountRole.Transport);
		return accountDTO;
	}
}
