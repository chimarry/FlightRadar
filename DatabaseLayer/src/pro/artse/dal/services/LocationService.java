package pro.artse.dal.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pro.artse.dal.database.ConnectionPool;
import pro.artse.dal.database.ServiceUtil;
import pro.artse.dal.dto.CityDTO;
import pro.artse.dal.dto.CountryDTO;
import pro.artse.dal.errorhandling.ErrorHandler;

public class LocationService implements ILocationService {

	@Override
	public List<CountryDTO> getCountries() {
		List<CountryDTO> countries = new ArrayList<>();
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.checkOut();
			ps = ServiceUtil.prepareStatement(connection, LocationSqlExtensions.SELECT_ALL_COUNTRIES);
			ResultSet rs = ps.executeQuery();

			while (rs.next())
				countries.add(LocationSqlExtensions.mapCountryFromSelect(rs));

			return countries;
		} catch (Exception ex) {
			ErrorHandler.handle(ex);
			return countries;
		} finally {
			ServiceUtil.finish(connectionPool, connection, ps);
		}
	}

	@Override
	public List<CityDTO> getCities(int countryId) {
		List<CityDTO> cities = new ArrayList<>();
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.checkOut();
			ps = ServiceUtil.prepareStatement(connection, LocationSqlExtensions.SELECT_ALL_CITIES_FROM_COUNTRY,
					countryId);
			ResultSet rs = ps.executeQuery();

			while (rs.next())
				cities.add(LocationSqlExtensions.mapCityFromSelect(rs));

			return cities;
		} catch (Exception ex) {
			ErrorHandler.handle(ex);
			return cities;
		} finally {
			ServiceUtil.finish(connectionPool, connection, ps);
		}
	}

	private static class LocationSqlExtensions {
		public static final String SELECT_ALL_COUNTRIES = "SELECT * FROM countries";
		public static final String SELECT_ALL_CITIES_FROM_COUNTRY = "SELECT * FROM cities where countryId=%d";

		public static CountryDTO mapCountryFromSelect(ResultSet rs) throws SQLException {
			return new CountryDTO(rs.getInt("countryId"), rs.getString("name"));
		}

		public static CityDTO mapCityFromSelect(ResultSet rs) throws SQLException {
			return new CityDTO(rs.getInt("cityId"), rs.getString("name"));
		}
	}
}
