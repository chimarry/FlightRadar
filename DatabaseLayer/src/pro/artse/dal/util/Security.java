package pro.artse.dal.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Security {

	public static byte[] computePasswordHash(String password) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());

			return getString(md.digest()).getBytes();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return new byte[0];
		}
	}

	public static boolean compare(byte[] hash, String password) {
		return getString(computePasswordHash(password)).equals(Security.getString(hash));
	}

	public static String getString(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			byte b = bytes[i];
			String hex = Integer.toHexString((int) 0x00FF & b);
			if (hex.length() == 1) {
				sb.append("0");
			}
			sb.append(hex);
		}
		return sb.toString();
	}
}
