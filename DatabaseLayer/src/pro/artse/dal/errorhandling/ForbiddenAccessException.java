package pro.artse.dal.errorhandling;

public class ForbiddenAccessException extends Exception {
	private static final long serialVersionUID = 1506061022489471378L;

	public ForbiddenAccessException() {
		super("You are forbidden to access this resource");
	}
}
