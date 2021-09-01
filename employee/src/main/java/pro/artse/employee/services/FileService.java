package pro.artse.employee.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.artse.employee.entities.FlightReservation;
import pro.artse.employee.repositories.FlightReservationRepository;
import pro.artse.employee.wrapper.FileInfo;

@Service
public class FileService {

	@Autowired
	private FlightReservationRepository flightReservationRepository;

	public FileInfo downloadFile(int flightReservationId) {
		FileInfo fileInfo = new FileInfo();
		FlightReservation reservation = flightReservationRepository.findById(flightReservationId).get();
		try {
			byte[] data = Files.readAllBytes(Paths.get(reservation.getFileSpecificationUri()));
			fileInfo.setFileNameFromPath(reservation.getFileSpecificationUri());
			fileInfo.setData(data);
			return fileInfo;
		} catch (IOException e) {
			return null;
		}
	}
}
