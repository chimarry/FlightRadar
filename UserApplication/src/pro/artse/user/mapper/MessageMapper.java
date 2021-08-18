package pro.artse.user.mapper;

import javax.servlet.http.HttpServletRequest;

import pro.artse.dal.dto.MessageDTO;

public final class MessageMapper {

	public static MessageDTO mapFromRequest(HttpServletRequest request) {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String text = request.getParameter("text");
		return new MessageDTO(text, email, name);
	}
}
