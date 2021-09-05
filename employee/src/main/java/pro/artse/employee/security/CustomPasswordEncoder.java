package pro.artse.employee.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(rawPassword.toString().getBytes());
			byte[] bytes = md.digest();
			return javax.xml.bind.DatatypeConverter.printHexBinary(bytes).toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encode(rawPassword).toUpperCase().equals(encodedPassword.toUpperCase());
	}
}
