package pro.artse.dal.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import pro.artse.dal.database.ConnectionPool;
import pro.artse.dal.database.ServiceUtil;
import pro.artse.dal.dto.AccountRole;
import pro.artse.dal.dto.FlightReservationDTO;
import pro.artse.dal.dto.FlightReservationStatus;
import pro.artse.dal.dto.InputFlightReservationDTO;
import pro.artse.dal.errorhandling.DbResultMessage;
import pro.artse.dal.errorhandling.DbStatus;
import pro.artse.dal.errorhandling.ErrorHandler;
import pro.artse.dal.util.FileUtil;
import pro.artse.dal.util.Validator;

public abstract class FlightReservationService implements IFlightReservationService {

	@Override
	public DbResultMessage<Boolean> create(InputFlightReservationDTO reservation, AccountRole role) {
		ConnectionPool connectionPool = null;
		Connection connection = null;

		try {
			int flightId;
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.checkOut();

			if (Validator.isInvalidFlightReservation(reservation, role))
				return new DbResultMessage<Boolean>(DbStatus.INVALID_DATA, "Missing required fields");

			// Find appropriate flight
			PreparedStatement getStatement = ServiceUtil.prepareStatement(connection,
					FlightReservationSqlExtension.SELECT_FLIGHT_ON_DATE,
					FlightReservationSqlExtension.formatDate(reservation.getDepartureDate()), role.name());
			ResultSet rs = getStatement.executeQuery();
			if (rs.next())
				flightId = rs.getInt("flightId");
			else
				return new DbResultMessage<Boolean>(DbStatus.NOT_FOUND, "There is no flight at the choosen date.");
			getStatement.close();

			return reserve(connection, reservation, flightId);
		} catch (Exception ex) {
			return ErrorHandler.handle(ex);
		} finally {
			ServiceUtil.finish(connectionPool, connection);
		}
	}

	protected abstract DbResultMessage<Boolean> reserve(Connection connection, InputFlightReservationDTO reservation,
			int flightId) throws SQLException;

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

	protected static class FlightReservationSqlExtension {
		public static final String SELECT_WITH_ACCOUNT_ID = "SELECT accountRole FROM accounts WHERE accountId=%d";
		public static final String SELECT_FLIGHT_ON_DATE = "SELECT flightId FROM flights WHERE DATE(departureOn)=%s AND type=%s";
		public static final String MYSQL_DATE_FORMAT = "YYYY-MM-dd";
		public static final String INSERT_FLIGHT_RESERVATION_PASSENGER = "INSERT INTO fligthReservations(accountId, flightId, seatNumber)"
				+ " VALUES(?,?,?)";
		public static final String INSERT_FLIGHT_RESERVATION_TRANSPORT = "INSERT INTO fligthReservations(accountId, flightId, description, fileSpecificationUri)"
				+ " VALUES(?,?,?,?)";

		public static Object[] mapForInsertPassenger(InputFlightReservationDTO reservation, int flightId) {
			return new Object[] { reservation.getAccountId(), flightId, reservation.getSeatNumber() };
		}

		public static Object[] mapForInsertTransport(InputFlightReservationDTO reservation, int flightId) {
			return new Object[] { reservation.getAccountId(), flightId, reservation.getCargoDescription(),
					FileUtil.DirectoryStructureBuilder.buildPathForFile(reservation.getFileSpecificationName()) };
		}

		public static final String formatDate(LocalDate date) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(MYSQL_DATE_FORMAT);
			return formatter.format(date);
		}
	}
}
