package pro.artse.dal.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import pro.artse.dal.database.ConnectionPool;
import pro.artse.dal.database.ServiceUtil;
import pro.artse.dal.database.SqlDateTimeConvertor;
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
					SqlDateTimeConvertor.toDbDate(reservation.getDepartureDate()), role.name(),
					reservation.getDepartureCityId(), reservation.getArrivalCityId());
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
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.checkOut();

			ps = ServiceUtil.prepareStatement(connection, FlightReservationSqlExtension.UPDATE_STATUS, status.name(),
					flightReservationId, accountId);
			ps.executeUpdate();

			return new DbResultMessage<Boolean>(true, DbStatus.SUCCESS);
		} catch (Exception ex) {
			return ErrorHandler.handle(ex);
		} finally {
			ServiceUtil.finish(connectionPool, connection, ps);
		}
	}

	@Override
	public List<FlightReservationDTO> getAll(int accountId) {
		List<FlightReservationDTO> flightReservations = new ArrayList<>();
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.checkOut();
			ps = ServiceUtil.prepareStatement(connection, FlightReservationSqlExtension.SELECT_ALL_RELATED_TO_ACCOUNT,
					accountId);
			ResultSet rs = ps.executeQuery();

			while (rs.next())
				flightReservations.add(FlightReservationSqlExtension.mapFromSelect(rs));

			return flightReservations;
		} catch (Exception ex) {
			ErrorHandler.handle(ex);
			return flightReservations;
		} finally {
			ServiceUtil.finish(connectionPool, connection, ps);
		}
	}

	public byte[] downloadSpecificationFile(String uri, int accountId) {
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.checkOut();
		
			byte[] data = FileUtil.downloadFile(uri);
			return data;
		} catch (Exception ex) {
			ErrorHandler.handle(ex);
			return new byte[] {};
		} finally {
			ServiceUtil.finish(connectionPool, connection, ps);
		}
	}

	protected static class FlightReservationSqlExtension {
		public static final String SELECT_WITH_ACCOUNT_ID = "SELECT accountRole FROM accounts WHERE accountId=%d";
		public static final String SELECT_FLIGHT_ON_DATE = "SELECT flightId FROM flights WHERE DATE(airportDateTime)=%s AND type=%s AND departureCityId=%d AND arrivalCityId=%d";

		public static final String INSERT_FLIGHT_RESERVATION_PASSENGER = "INSERT INTO flightReservations(accountId, flightId, seatNumber, status, createdOn)"
				+ " VALUES(?,?,?,?,?)";
		public static final String INSERT_FLIGHT_RESERVATION_TRANSPORT = "INSERT INTO flightReservations(accountId, flightId, description, fileSpecificationUri, status, createdOn)"
				+ " VALUES(?,?,?,?,?,?)";
		public static final String SELECT_ALL_RELATED_TO_ACCOUNT = "SELECT fr.flightReservationId, fr.status, fr.createdOn, fr.accountId, ac.name AS arrivalCountryName, a.name AS arrivalCityName,"
				+ "dc.name AS departureCountryName, d.name AS departureCityName, f.airportDateTime, fr.seatNumber, "
				+ "fr.description, fr.fileSpecificationUri FROM flightReservations fr " + "INNER JOIN flights f "
				+ "ON f.flightId=fr.flightId " + "INNER JOIN cities a " + "ON f.arrivalCityId=a.cityId "
				+ "INNER JOIN countries ac " + "ON ac.countryId=a.countryId " + "INNER JOIN cities d "
				+ "ON f.departureCityId=d.cityId " + "INNER JOIN countries dc " + "ON dc.countryId=d.countryId "
				+ "WHERE fr.accountId=%d" + " ORDER BY fr.createdOn DESC";

		public static final String UPDATE_STATUS = "UPDATE flightReservations SET status=%s WHERE flightReservationId=%d AND accountId=%d";

		public static final String CHECK_ACCOUNT = "SELECT * FROM flightReservations WHERE fileSpecificationUri=%s AND accountId=%d";

		public static Object[] mapForInsertPassenger(InputFlightReservationDTO reservation, int flightId) {
			String createdOn = SqlDateTimeConvertor.toDbDateTime(LocalDateTime.now());
			return new Object[] { reservation.getAccountId(), flightId, reservation.getSeatNumber(),
					FlightReservationStatus.New.name(), createdOn };
		}

		public static Object[] mapForInsertTransport(InputFlightReservationDTO reservation, int flightId) {
			String createdOn = SqlDateTimeConvertor.toDbDateTime(LocalDateTime.now());
			return new Object[] { reservation.getAccountId(), flightId, reservation.getCargoDescription(),
					FileUtil.DirectoryStructureBuilder.buildPathForFile(reservation.getFileSpecificationName()),
					FlightReservationStatus.New.name(), createdOn };
		}

		public static final FlightReservationDTO mapFromSelect(ResultSet rs) throws SQLException {
			return new FlightReservationDTO(rs.getInt("accountId"), rs.getInt("flightReservationId"),
					SqlDateTimeConvertor.toLocalDateTime(rs, "airportDateTime"), rs.getString("arrivalCityName"),
					rs.getString("arrivalCountryName"), rs.getString("departureCityName"),
					rs.getString("departureCountryName"), FlightReservationStatus.valueOf(rs.getString("status")),
					SqlDateTimeConvertor.toLocalDateTime(rs, "createdOn"), rs.getInt("seatNumber"),
					rs.getString("description"), rs.getString("fileSpecificationUri"));
		}
	}
}
