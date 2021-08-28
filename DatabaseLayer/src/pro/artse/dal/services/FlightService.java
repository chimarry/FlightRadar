package pro.artse.dal.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import pro.artse.dal.database.*;
import pro.artse.dal.dto.FlightDTO;
import pro.artse.dal.dto.FlightType;
import pro.artse.dal.errorhandling.ErrorHandler;
import pro.artse.dal.util.ConfigurationUtil;

public class FlightService implements IFlightService {

	@Override
	public List<FlightDTO> getAll(LocalDate date, boolean isDepartured) {
		String sql = FlightSqlExtensions.SELECT_ARRIVALS;
		if (isDepartured)
			sql = FlightSqlExtensions.SELECT_DEPARTURES;
		return getFlights(sql, date);
	}

	public List<FlightDTO> getFeatured(boolean isDepartured) {
		String sql = FlightSqlExtensions.SELECT_FEATURED_ARRIVALS;
		if (isDepartured)
			sql = FlightSqlExtensions.SELECT_FEATURED_DEPARTURES;
		// TODO: Change to now
		return getFlights(sql, LocalDate.of(2021, 8, 15));
	}

	private List<FlightDTO> getFlights(String sql, LocalDate date) {
		List<FlightDTO> flights = new ArrayList<>();
		String cityName = ConfigurationUtil.get("cityName");
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.checkOut();
			ps = ServiceUtil.prepareStatement(connection, sql, cityName, SqlDateTimeConvertor.toDbDate(date));
			ResultSet rs = ps.executeQuery();

			while (rs.next())
				flights.add(FlightSqlExtensions.mapFromSelect(rs));

			return flights;
		} catch (Exception ex) {
			ErrorHandler.handle(ex);
			return flights;
		} finally {
			ServiceUtil.finish(connectionPool, connection, ps);
		}
	}

	private static class FlightSqlExtensions {

		private static final String SELECT_FLIGHTS = "select a.name as arrivalCityName, d.name as departureCityName, f.arrivalCityId, f.departureCityId, f.flightId, f.airportDateTime, f.type  ";

		private static final String SELECT_DEPARTURES = SELECT_FLIGHTS + " from cities d " + "left join flights f "
				+ "on d.cityId=f.departureCityId " + "left join cities a on a.cityId=f.arrivalCityId "
				+ " WHERE d.name=%s AND date(f.airportDateTime)=%s";

		private static final String SELECT_ARRIVALS = SELECT_FLIGHTS + " from cities a " + "left join flights f "
				+ "on a.cityId=f.arrivalCityId " + "left join cities d on d.cityId=f.departureCityId "
				+ " WHERE a.name=%s AND date(f.airportDateTime)=%s";

		static final String SELECT_FEATURED_ARRIVALS = SELECT_ARRIVALS + " LIMIT "
				+ ConfigurationUtil.getNumber("featured");

		private static final String SELECT_FEATURED_DEPARTURES = SELECT_DEPARTURES + " LIMIT "
				+ ConfigurationUtil.getNumber("featured");

		public static FlightDTO mapFromSelect(ResultSet rs) throws SQLException {
			FlightDTO flight = new FlightDTO(rs.getInt("flightId"), rs.getInt("arrivalCityId"),
					rs.getInt("departureCityId"), SqlDateTimeConvertor.toLocalDateTime(rs, "airportDateTime"),
					rs.getString("arrivalCityName"), rs.getString("departureCityName"),
					FlightType.valueOf(rs.getString("type")));
			flight.setStatusBasedOnTime();
			return flight;
		}
	}
}
