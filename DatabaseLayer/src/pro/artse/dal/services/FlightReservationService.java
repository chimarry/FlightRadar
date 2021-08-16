package pro.artse.dal.services;

import java.util.ArrayList;

import pro.artse.dal.dto.FlightReservationDTO;
import pro.artse.dal.dto.FlightReservationStatus;
import pro.artse.dal.dto.InputFlightReservationDTO;
import pro.artse.dal.errorhandling.DbResultMessage;

public class FlightReservationService implements IFlightReservationService {

	@Override
	public DbResultMessage<Boolean> create(InputFlightReservationDTO reservation) {
		// Create reservation for active account
		// Check if the flight with specified date exists and get first
		// Make an reservation
		// Return status
		return null;
	}

	@Override
	public DbResultMessage<Boolean> changeStatus(int flightReservationId, FlightReservationStatus status,
			int accountId) {
		// Get the specified reservation
		// Do authorization - if employee or owner of the account
		// Change status
		return null;
	}

	@Override
	public ArrayList<FlightReservationDTO> getAll(int accountId) {
		// Get all reservations for the specified account
		return null;
	}

	private static class FlightReservationSqlExtension{
		
	}
}
