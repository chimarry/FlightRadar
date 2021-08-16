package pro.artse.dal.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import pro.artse.dal.database.ServiceUtil;
import pro.artse.dal.dto.InputFlightReservationDTO;
import pro.artse.dal.errorhandling.DbResultMessage;

public class PassengerFlightReservationService extends FlightReservationService {

	@Override
	protected DbResultMessage<Boolean> reserve(Connection connection, InputFlightReservationDTO reservation,
			int flightId) throws SQLException {
		PreparedStatement insertStatement = ServiceUtil.prepareStatement(connection,
				FlightReservationSqlExtension.INSERT_FLIGHT_RESERVATION_PASSENGER, false,
				FlightReservationSqlExtension.mapForInsertPassenger(reservation, flightId));

		insertStatement.executeUpdate();
		insertStatement.close();
		return new DbResultMessage<Boolean>(true);
	}

}
