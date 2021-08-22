package pro.artse.dal.services;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import pro.artse.dal.database.ConnectionPool;
import pro.artse.dal.database.ServiceUtil;
import pro.artse.dal.database.SqlDateTimeConvertor;
import pro.artse.dal.dto.AppVisitDTO;
import pro.artse.dal.errorhandling.ErrorHandler;

public class AppVisitService implements IAppVisitService, Serializable {
	private static final long serialVersionUID = 8504708142278348575L;

	@Override
	public void add() {
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.checkOut();

			ps = ServiceUtil.prepareStatement(connection, VisitSqlExtensions.INSERT, false,
					new Object[] { SqlDateTimeConvertor.toDbDate(LocalDate.now()) });
			ps.executeUpdate();
		} catch (Exception ex) {
			ErrorHandler.handle(ex);
		} finally {
			ServiceUtil.finish(connectionPool, connection, ps);
		}
	}

	@Override
	public int getOn(LocalDate date) {
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.checkOut();

			ps = ServiceUtil.prepareStatement(connection, VisitSqlExtensions.GET_ON,
					SqlDateTimeConvertor.toDbDate(date));
			ResultSet rs = ps.executeQuery();
			return rs.getInt(1);
		} catch (Exception ex) {
			ErrorHandler.handle(ex);
			return 0;
		} finally {
			ServiceUtil.finish(connectionPool, connection, ps);
		}
	}

	@Override
	public List<AppVisitDTO> getInLast(int numberOfDays) {
		List<AppVisitDTO> visits = new ArrayList<AppVisitDTO>();
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.checkOut();

			ps = ServiceUtil.prepareStatement(connection, VisitSqlExtensions.GET_ALL, numberOfDays);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				visits.add(VisitSqlExtensions.mapFromSelect(rs));
			return visits;
		} catch (Exception ex) {
			ErrorHandler.handle(ex);
			return visits;
		} finally {
			ServiceUtil.finish(connectionPool, connection, ps);
		}
	}

	private static class VisitSqlExtensions {
		public static final String INSERT = "INSERT INTO visits(visitedOn) VALUES(?)";
		public static final String GET_ON = "SELECT count(*) FROM visits WHERE visitedOn=%s";
		public static final String GET_ALL = "SELECT visitedOn, count(*) AS count FROM visits GROUP BY visitedOn ORDER BY visitedOn DESC LIMIT %d";

		public static AppVisitDTO mapFromSelect(ResultSet rs) throws SQLException {
			return new AppVisitDTO(rs.getInt("count"), rs.getString("visitedOn"));
		}
	}
}
