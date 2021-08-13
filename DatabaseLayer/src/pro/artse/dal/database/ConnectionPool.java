package pro.artse.dal.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import pro.artse.dal.util.ConfigurationUtil;

public final class ConnectionPool {

	private String jdbcURL;
	private String username;
	private String password;
	private int preconnectCount;
	private int maxIdleConnections;
	private int maxConnections;

	private int connectCount;
	private List<Connection> usedConnections;
	private List<Connection> freeConnections;

	private static ConnectionPool instance;

	public static ConnectionPool getInstance() {
		if (instance == null)
			instance = new ConnectionPool();
		return instance;
	}

	private ConnectionPool() {
		try {
			readConfiguration();
			freeConnections = new ArrayList<Connection>();
			usedConnections = new ArrayList<Connection>();

			for (int i = 0; i < preconnectCount; i++) {
				Connection conn = DriverManager.getConnection(jdbcURL, username, password);
				freeConnections.add(conn);
			}
			connectCount = preconnectCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void readConfiguration() {
		try {
			ResourceBundle bundle = ConfigurationUtil.getBundle();
			jdbcURL = ConfigurationUtil.get("jdbcURL", bundle);
			username = ConfigurationUtil.get("username", bundle);
			password = ConfigurationUtil.get("password", bundle);
			preconnectCount = ConfigurationUtil.getNumber("preconnectCount", bundle);
			maxIdleConnections = ConfigurationUtil.getNumber("maxIdleConnections", bundle);
			maxConnections = ConfigurationUtil.getNumber("maxConnections", bundle);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized Connection checkOut() throws SQLException {
		Connection conn = null;
		if (freeConnections.size() > 0) {
			conn = freeConnections.remove(0);
			usedConnections.add(conn);
		} else {
			if (connectCount < maxConnections) {
				conn = DriverManager.getConnection(jdbcURL, username, password);
				usedConnections.add(conn);
				connectCount++;
			} else {
				try {
					wait();
					conn = freeConnections.remove(0);
					usedConnections.add(conn);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return conn;
	}

	public synchronized void checkIn(Connection conn) {
		if (conn == null)
			return;
		if (usedConnections.remove(conn)) {
			freeConnections.add(conn);
			while (freeConnections.size() > maxIdleConnections) {
				int lastOne = freeConnections.size() - 1;
				Connection c = freeConnections.remove(lastOne);
				try {
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			notify();
		}
	}
}
