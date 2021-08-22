package pro.artse.dal.services;

import java.io.Serializable;
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
import pro.artse.dal.errorhandling.DbResultMessage;
import pro.artse.dal.errorhandling.DbStatus;
import pro.artse.dal.errorhandling.ErrorHandler;
import pro.artse.dal.util.Validator;

public class LocationService implements ILocationService, Serializable {

	private static final long serialVersionUID = 7674554067333904271L;

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

	@Override
	public DbResultMessage<Boolean> deleteCountry(int countryId) {
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.checkOut();

			ps = ServiceUtil.prepareStatement(connection, LocationSqlExtensions.DELETE_COUNTRY, false,
					new Object[] { countryId });
			ps.executeUpdate();

			if (!ServiceUtil.isSuccess(ps))
				return new DbResultMessage<Boolean>(false, DbStatus.UNKNOWN_ERROR, "Deleting country failed.");
			return new DbResultMessage<Boolean>(true);
		} catch (Exception ex) {
			return ErrorHandler.handle(ex);
		} finally {
			ServiceUtil.finish(connectionPool, connection, ps);
		}
	}

	@Override
	public DbResultMessage<Boolean> deleteCity(int cityId) {
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.checkOut();

			ps = ServiceUtil.prepareStatement(connection, LocationSqlExtensions.DELETE_CITY, false,
					new Object[] { cityId });
			ps.executeUpdate();

			if (!ServiceUtil.isSuccess(ps))
				return new DbResultMessage<Boolean>(false, DbStatus.UNKNOWN_ERROR, "Deleting city failed.");
			return new DbResultMessage<Boolean>(true);
		} catch (Exception ex) {
			return ErrorHandler.handle(ex);
		} finally {
			ServiceUtil.finish(connectionPool, connection, ps);
		}
	}

	@Override
	public DbResultMessage<Boolean> addCountry(CountryDTO country) {
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement ps = null;

		if (Validator.isNullOrEmpty(country.getName()))
			return new DbResultMessage<Boolean>(DbStatus.INVALID_DATA);

		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.checkOut();

			// Check if a country with specified name already exists
			PreparedStatement psExists = ServiceUtil.prepareStatement(connection, LocationSqlExtensions.SELECT_COUNTRY,
					country.getName());
			ResultSet rs = psExists.executeQuery();
			if (rs.next())
				return new DbResultMessage<Boolean>(DbStatus.EXISTS,
						"Country with name=" + country.getName() + " already exists.");
			psExists.close();

			ps = ServiceUtil.prepareStatement(connection, LocationSqlExtensions.INSERT_COUNTRY, true,
					LocationSqlExtensions.mapCountryInsert(country));
			ps.executeUpdate();

			if (!ServiceUtil.isSuccess(ps))
				return new DbResultMessage<Boolean>(false, DbStatus.UNKNOWN_ERROR, "Adding country failed.");
			return new DbResultMessage<Boolean>(true);
		} catch (Exception ex) {
			return ErrorHandler.handle(ex);
		} finally {
			ServiceUtil.finish(connectionPool, connection, ps);
		}
	}

	@Override
	public DbResultMessage<Boolean> addCity(CityDTO city) {
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement ps = null;

		if (Validator.isNullOrEmpty(city.getName()))
			return new DbResultMessage<Boolean>(DbStatus.INVALID_DATA);

		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.checkOut();

			// Check if a city with specified name in specified country already exists
			PreparedStatement psExists = ServiceUtil.prepareStatement(connection, LocationSqlExtensions.SELECT_CITY,
					city.getCountryId(), city.getName());
			ResultSet rs = psExists.executeQuery();
			if (rs.next())
				return new DbResultMessage<Boolean>(DbStatus.EXISTS,
						"City with name=" + city.getName() + " already exists in specified country.");
			psExists.close();

			ps = ServiceUtil.prepareStatement(connection, LocationSqlExtensions.INSERT_CITY, false,
					LocationSqlExtensions.mapCityInsert(city));
			ps.executeUpdate();

			if (!ServiceUtil.isSuccess(ps))
				return new DbResultMessage<Boolean>(false, DbStatus.UNKNOWN_ERROR, "Adding city failed.");
			return new DbResultMessage<Boolean>(true);
		} catch (Exception ex) {
			return ErrorHandler.handle(ex);
		} finally {
			ServiceUtil.finish(connectionPool, connection, ps);
		}
	}

	@Override
	public DbResultMessage<Boolean> updateCountry(CountryDTO country) {
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement ps = null;

		if (Validator.isNullOrEmpty(country.getName()))
			return new DbResultMessage<Boolean>(DbStatus.INVALID_DATA);

		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.checkOut();

			// Check if a country with specified id exists
			PreparedStatement psExists = ServiceUtil.prepareStatement(connection,
					LocationSqlExtensions.SELECT_COUNTRY_ID, country.getCountryId());
			ResultSet rs = psExists.executeQuery();
			if (ServiceUtil.isEmpty(rs))
				return new DbResultMessage<Boolean>(DbStatus.NOT_FOUND);
			psExists.close();

			ps = ServiceUtil.prepareStatement(connection, LocationSqlExtensions.UPDATE_COUNTRY, false,
					LocationSqlExtensions.mapCountryUpdate(country));
			ps.executeUpdate();

			if (!ServiceUtil.isSuccess(ps))
				return new DbResultMessage<Boolean>(false, DbStatus.UNKNOWN_ERROR, "Updating country failed.");
			return new DbResultMessage<Boolean>(true);
		} catch (Exception ex) {
			return ErrorHandler.handle(ex);
		} finally {
			ServiceUtil.finish(connectionPool, connection, ps);
		}
	}

	@Override
	public DbResultMessage<Boolean> updateCity(CityDTO city) {
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement ps = null;

		if (Validator.isNullOrEmpty(city.getName()) || Validator.isNull(city.getCountryId()))
			return new DbResultMessage<Boolean>(DbStatus.INVALID_DATA);

		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.checkOut();

			ps = ServiceUtil.prepareStatement(connection, LocationSqlExtensions.UPDATE_CITY, false,
					LocationSqlExtensions.mapCityUpdate(city));
			ps.executeUpdate();

			if (!ServiceUtil.isSuccess(ps))
				return new DbResultMessage<Boolean>(false, DbStatus.UNKNOWN_ERROR, "Updating city failed.");
			return new DbResultMessage<Boolean>(true);
		} catch (Exception ex) {
			return ErrorHandler.handle(ex);
		} finally {
			ServiceUtil.finish(connectionPool, connection, ps);
		}
	}

	private static class LocationSqlExtensions {
		public static final String SELECT_ALL_COUNTRIES = "SELECT * FROM countries";
		public static final String SELECT_COUNTRY = "SELECT * FROM countries WHERE name=%s";
		public static final String SELECT_COUNTRY_ID = "SELECT * FROM countries WHERE countryId=%d";
		public static final String SELECT_CITY = "SELECT * FROM cities WHERE countryId=%d AND name=%s";
		public static final String SELECT_ALL_CITIES_FROM_COUNTRY = "SELECT * FROM cities where countryId=%d";
		public static final String DELETE_COUNTRY = "DELETE FROM countries WHERE countryId=?";
		public static final String DELETE_CITY = "DELETE FROM cities WHERE cityId=?";
		public static final String UPDATE_COUNTRY = "UPDATE countries SET name=? WHERE countryId=?";
		public static final String UPDATE_CITY = "UPDATE cities SET name=?, countryId=? WHERE cityId=?";
		public static final String INSERT_COUNTRY = "INSERT INTO countries(name) VALUES (?)";
		public static final String INSERT_CITY = "INSERT INTO cities(countryId, name) VALUES (?, ?)";

		public static CountryDTO mapCountryFromSelect(ResultSet rs) throws SQLException {
			return new CountryDTO(rs.getInt("countryId"), rs.getString("name"));
		}

		public static CityDTO mapCityFromSelect(ResultSet rs) throws SQLException {
			return new CityDTO(rs.getInt("countryId"), rs.getInt("cityId"), rs.getString("name"));
		}

		public static Object[] mapCountryInsert(CountryDTO country) {
			return new Object[] { country.getName() };
		}

		public static Object[] mapCountryUpdate(CountryDTO country) {
			return new Object[] { country.getName(), country.getCountryId() };
		}

		public static Object[] mapCityInsert(CityDTO city) {
			return new Object[] { city.getCountryId(), city.getName() };
		}

		public static Object[] mapCityUpdate(CityDTO city) {
			return new Object[] { city.getName(), city.getCountryId(), city.getCityId() };
		}
	}
}
