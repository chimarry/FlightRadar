package pro.artse.dal.services;

import java.util.List;

import pro.artse.dal.dto.AccountRole;
import pro.artse.dal.dto.FlightReservationDTO;
import pro.artse.dal.dto.FlightReservationStatus;
import pro.artse.dal.dto.InputFlightReservationDTO;
import pro.artse.dal.errorhandling.DbResultMessage;

public interface IFlightReservationService {
	
	public DbResultMessage<Boolean> create(InputFlightReservationDTO reservation, AccountRole role);
	
	public DbResultMessage<Boolean> changeStatus(int flightReservationId, FlightReservationStatus status, int accountId);
	
	public List<FlightReservationDTO> getAll(int accountId);
	
	public byte[] downloadSpecificationFile(String uri, int accountId);
}
