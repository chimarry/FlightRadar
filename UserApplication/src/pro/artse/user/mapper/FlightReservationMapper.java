package pro.artse.user.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import pro.artse.dal.dto.AccountRole;
import pro.artse.dal.dto.InputFlightReservationDTO;

public class FlightReservationMapper {
	public static InputFlightReservationDTO mapFromRequest(HttpServletRequest request, AccountRole role) throws IOException, ServletException {
		int arrivalCityId = Integer.parseInt(request.getParameter("arrivalCityName"));
		int departureCityId = Integer.parseInt(request.getParameter("departureCityName"));

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate date = LocalDate.parse(request.getParameter("departureDate"), formatter);
		InputFlightReservationDTO dto = new InputFlightReservationDTO(date, arrivalCityId, departureCityId);
		if (role == AccountRole.Passenger) {
			dto.setSeatNumber(Integer.parseInt(request.getParameter("seatNumber")));
			dto.setCargoDescription(request.getParameter("cargoDescription"));
			Part filePart = request.getPart("specificationFile"); // Retrieves <input type="file" name="file">
		    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		    InputStream fileContent = filePart.getInputStream();
			byte[] data = fileContent.readAllBytes();
			int x =2;
		}
		return dto;
	}
}
