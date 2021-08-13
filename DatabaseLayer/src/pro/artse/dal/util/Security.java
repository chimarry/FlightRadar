package pro.artse.dal.util;

public final class Security {

	public static byte[] computePasswordHash(String password) {
		return password.getBytes();
	}
}
