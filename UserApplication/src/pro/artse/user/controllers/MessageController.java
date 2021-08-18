package pro.artse.user.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pro.artse.dal.dto.MessageDTO;
import pro.artse.dal.errorhandling.DbResultMessage;
import pro.artse.dal.services.IMessageService;
import pro.artse.dal.services.ServiceFactory;
import pro.artse.user.mapper.MessageMapper;
import pro.artse.user.util.AlertManager;
import pro.artse.user.util.Messages;
import pro.artse.user.util.Pages;

@WebServlet("/MessageController")
public class MessageController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private IMessageService messageService = ServiceFactory.getMessageService();
	
	public MessageController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(Pages.INDEX);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		MessageDTO message = MessageMapper.mapFromRequest(request);
		DbResultMessage<Boolean> isSent = messageService.sent(message);
		AlertManager.addToRequest(request, isSent, Messages.MESSAGE_FAILED);

		RequestDispatcher dispatcher = request.getRequestDispatcher(Pages.INDEX);
		dispatcher.forward(request, response);
	}
}
