package pro.artse.user.util;

public final class Validator {

	public static boolean isEmptyOrNull(String value) {
		return value == null || value.equals("");
	}
}
