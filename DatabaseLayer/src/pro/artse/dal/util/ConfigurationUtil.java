package pro.artse.dal.util;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * @author Marija Manipulates with project's configuration.
 */
public final class ConfigurationUtil {

	public static ResourceBundle getBundle() {
		return PropertyResourceBundle.getBundle("pro.artse.dal.util.config");
	}
	
	/**
	 * Reads value that corresponds provided key, from configuration file.
	 * 
	 * @param key Key that identifies value.
	 * @return Value if found or null.
	 */
	public static String get(String key) {
		return getBundle().getString(key);
	}

	/**
	 * Reads an integer value that corresponds provided key, from configuration
	 * file.
	 * 
	 * @param key Key that identifies value.
	 * @return Value if found or null.
	 */

	public static int getNumber(String key) {
		return Integer.parseInt(get(key));
	}

	public static String get(String key, ResourceBundle bundle) {
		return bundle.getString(key);
	}

	public static int getNumber(String key, ResourceBundle bundle) {
		return Integer.parseInt(get(key, bundle));
	}
}
