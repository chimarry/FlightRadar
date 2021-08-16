package pro.artse.dal.services;

import java.util.ArrayList;

import pro.artse.dal.dto.FlightReservationDTO;
import pro.artse.dal.dto.FlightReservationStatus;
import pro.artse.dal.dto.InputFlightReservationDTO;
import pro.artse.dal.errorhandling.DbResultMessage;

public interface IFlightReservationService {
	
	public DbResultMessage<Boolean> create(InputFlightReservationDTO reservation);
	
	public DbResultMessage<Boolean> changeStatus(int flightReservationId, FlightReservationStatus status, int accountId);
	
	public ArrayList<FlightReservationDTO> getAll(int accountId);
}
