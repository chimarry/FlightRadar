package pro.artse.dal.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import pro.artse.dal.database.ServiceUtil;
import pro.artse.dal.dto.InputFlightReservationDTO;
import pro.artse.dal.errorhandling.DbResultMessage;
import pro.artse.dal.errorhandling.DbStatus;
import pro.artse.dal.util.FileUtil;

public class TransportFlightReservationService extends FlightReservationService {

	@Override
	protected DbResultMessage<Boolean> reserve(Connection connection, InputFlightReservationDTO reservation,
			int flightId) throws SQLException {
		PreparedStatement insertStatement = null;
		// Save specification file if flight is transport
		if (!saveSpecificationFile(reservation.getFileSpecification(), reservation.getFileSpecificationName()))
			return new DbResultMessage<Boolean>(DbStatus.SERVER_ERROR, "Specification file could not be saved");

		// Save reservation
		insertStatement = ServiceUtil.prepareStatement(connection,
				FlightReservationSqlExtension.INSERT_FLIGHT_RESERVATION_TRANSPORT, false,
				FlightReservationSqlExtension.mapForInsertTransport(reservation, flightId));

		insertStatement.executeUpdate();
		insertStatement.close();
		return new DbResultMessage<Boolean>(true);
	}

	private Boolean saveSpecificationFile(byte[] data, String name) {
		return FileUtil.uploadFile(data, FileUtil.DirectoryStructureBuilder.buildPathForFile(name));
	}

}
