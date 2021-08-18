package pro.artse.dal.services;

import java.sql.Connection;
import java.sql.PreparedStatement;

import pro.artse.dal.database.ConnectionPool;
import pro.artse.dal.database.ServiceUtil;
import pro.artse.dal.dto.MessageDTO;
import pro.artse.dal.errorhandling.DbResultMessage;
import pro.artse.dal.errorhandling.DbStatus;
import pro.artse.dal.errorhandling.ErrorHandler;
import pro.artse.dal.util.Validator;

public class MessageService implements IMessageService {

	@Override
	public DbResultMessage<Boolean> sent(MessageDTO message) {
		ConnectionPool connectionPool = null;
		Connection connection = null;
		PreparedStatement ps = null;

		if (Validator.isInvalidMessage(message))
			return new DbResultMessage<Boolean>(DbStatus.INVALID_DATA);

		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.checkOut();

			ps = ServiceUtil.prepareStatement(connection,MessageSqlExtensions.INSERT_MESSAGE, false,
					MessageSqlExtensions.mapForInsert(message));
			ps.executeUpdate();
			if (!ServiceUtil.isSuccess(ps))
				return new DbResultMessage<Boolean>(false, DbStatus.UNKNOWN_ERROR, "Sending message failed.");

			return new DbResultMessage<Boolean>(true);
		} catch (Exception ex) {
			return ErrorHandler.handle(ex);
		} finally {
			ServiceUtil.finish(connectionPool, connection, ps);
		}
	}

	private static class MessageSqlExtensions {
		public static final String INSERT_MESSAGE = "INSERT INTO messages(text, name, email, createdOn) VALUES (?, ?, ?, ?)";

		public static Object[] mapForInsert(MessageDTO message) {
			return new Object[] { message.getText(), message.getName(), message.getEmail(), message.getCreatedOn() };
		}
	}
}
