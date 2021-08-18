package pro.artse.user.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pro.artse.user.util.Pages;
import pro.artse.dal.dto.AccountRole;
import pro.artse.dal.dto.FlightReservationStatus;
import pro.artse.dal.dto.InputFlightReservationDTO;
import pro.artse.dal.errorhandling.DbResultMessage;
import pro.artse.dal.errorhandling.ForbiddenAccessException;
import pro.artse.dal.services.IFlightReservationService;
import pro.artse.dal.services.ServiceFactory;
import pro.artse.user.beans.FlightReservationsBean;
import pro.artse.user.mapper.FlightReservationMapper;
import pro.artse.user.util.AlertManager;
import pro.artse.user.util.HttpSessionUtil;
import pro.artse.user.util.Messages;

@WebServlet("/FlightReservationController")
@MultipartConfig
public class FlightReservationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SHOW_RESERVATION_FORM = "form";
	private static final String DOWNLOAD_FILE = "download";
	private static final String CHANGE_STATUS = "change";

	private IFlightReservationService flightReservationService;

	public FlightReservationController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String address = Pages.NOT_AUTHENTICATED;
		HttpSession session = request.getSession();
		if (HttpSessionUtil.isLoggedIn(session)) {
			setFlightReservationService(request);

			String action = request.getParameter("action");
			request.setAttribute("accountRole", getRole(request));
			if (SHOW_RESERVATION_FORM.equals(action))
				address = Pages.FLIGHT_RESERVATION_FORM;
			else {
				if (CHANGE_STATUS.equals(action))
					changeStatus(request);
				if (DOWNLOAD_FILE.equals(action)) {
					request.setAttribute("flightReservationsBean", getAllReservations(request));
					downloadSpecificationFile(request, response);
					return;
				}
				address = Pages.FLIGHT_RESERVATIONS;
				request.setAttribute("flightReservationsBean", getAllReservations(request));
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String address = Pages.INDEX;
		HttpSession session = request.getSession();
		if (!HttpSessionUtil.isLoggedIn(session)) {
			HttpSessionUtil.turnOnGuestMode(session);
		} else {
			AccountRole role = getRole(request);
			setFlightReservationService(request);

			InputFlightReservationDTO dto = FlightReservationMapper.mapFromRequest(request, role);
			dto.setAccountId(HttpSessionUtil.getAccountId(request));

			DbResultMessage<Boolean> created = flightReservationService.create(dto, role);
			AlertManager.addToRequest(request, created, Messages.FAILED_RESERVATION);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	private void changeStatus(HttpServletRequest request) {
		HttpSession session = request.getSession();

		// Get data from request
		FlightReservationStatus status = FlightReservationStatus.valueOf(request.getParameter("status"));
		int flightReservationId = Integer.parseInt(request.getParameter("flightReservationId"));

		DbResultMessage<Boolean> isChanged = flightReservationService.changeStatus(flightReservationId, status,
				HttpSessionUtil.getAccountInfo(session).getAccountId());

		// Notify user about the outcome of the operation
		AlertManager.addToRequest(request, isChanged, Messages.STATUS_NOT_CHANGED);
	}

	private void downloadSpecificationFile(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String fileUri = request.getParameter("file");
		String fileName = Paths.get(fileUri).getFileName().toString();
		String contentType = getServletContext().getMimeType(fileName);

		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		response.reset();
		OutputStream out = response.getOutputStream();
		response.setContentType(contentType);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

		byte[] fileData = null;
		try {
			fileData = flightReservationService.downloadSpecificationFile(fileUri,
					HttpSessionUtil.getAccountId(request));
			response.setContentLength(fileData.length);
			response.setBufferSize(fileData.length+100);
			try {
				out.write(fileData, 0, fileData.length);
				out.flush();
			} finally {
				out.close();
			}

		} catch (ForbiddenAccessException e) {
			AlertManager.writeErrorMessage(request, Messages.FORBIDDEN_ACCESS_EXCEPTION);
		}
	}

	private FlightReservationsBean getAllReservations(HttpServletRequest request) {
		FlightReservationsBean bean = new FlightReservationsBean();
		int accountId = HttpSessionUtil.getAccountId(request);

		setFlightReservationService(request);

		bean.setFlightReservations(flightReservationService.getAll(accountId).stream()
				.map(x -> FlightReservationMapper.mapFromDTO(x)).collect(Collectors.toCollection(ArrayList::new)));

		return bean;
	}

	private void setFlightReservationService(HttpServletRequest request) {
		flightReservationService = ServiceFactory.getFlightReservationService(getRole(request));
	}

	private AccountRole getRole(HttpServletRequest request) {
		return HttpSessionUtil.getAccountInfo(request.getSession()).getRole();
	}
}
