package pro.artse.dal.errorhandling;

public class DBResultMessage<T> {
	private T result;
	private DbStatus status;
	private String message = "";

	public DBResultMessage(T result, DbStatus status, String message) {
		this(result, status);
		this.message = message;
	}

	public DBResultMessage(T result, DbStatus status) {
		this(status);
		this.result = result;
	}

	public DBResultMessage(DbStatus status) {
		super();
		this.status = status;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public DbStatus getStatus() {
		return status;
	}

	public void setStatus(DbStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return DbStatus.SUCCESS == status;
	}
}
