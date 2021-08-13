package pro.artse.dal.errorhandling;

public class DbResultMessage<T> {
	private T result;
	private DbStatus status;
	private String message = "";

	public DbResultMessage(T result, DbStatus status, String message) {
		this(result, status);
		this.message = message;
	}

	public DbResultMessage(T result, DbStatus status) {
		this(status);
		this.result = result;
	}

	public DbResultMessage(DbStatus status) {
		super();
		this.status = status;
	}

	public DbResultMessage(DbStatus status, String message) {
		this(null, status, message);
	}

	public DbResultMessage(T result) {
		this(result, DbStatus.SUCCESS);
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
