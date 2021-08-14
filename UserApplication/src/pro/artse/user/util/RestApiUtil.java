package pro.artse.user.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import pro.artse.user.beans.CountryBean;

public final class RestApiUtil {

	private static final String COUNTRIES_REST = "https://restcountries.eu/rest/v2/region/europe";

	public static final ArrayList<CountryBean> getCountries() {

		HttpURLConnection connection = null;
		try {
			URL url = new URL(COUNTRIES_REST);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try (BufferedReader bufferedReader = RestApiUtil.getReader(connection)) {
			JsonArray array = readJsonList(bufferedReader);
			connection.disconnect();
			return deserialize(array, CountryBean.class);
		} catch (IOException e) {
			System.err.println(e);
			return null;
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}

	public static BufferedReader getReader(HttpURLConnection connection) throws IOException {
		return new BufferedReader(new java.io.InputStreamReader(
				(connection.getResponseCode() <= 299 ? connection.getInputStream() : connection.getErrorStream())));
	}

	/**
	 * Parses java string as JSON array.
	 * 
	 * @param reader Opened reader.
	 * @return Result of method.
	 * @throws IOException
	 */
	public static JsonArray readJsonList(BufferedReader reader) throws IOException {
		String resultString = reader.readLine();
		JsonParser parser = new JsonParser();
		JsonElement elem = parser.parse(resultString);
		JsonArray array = elem.getAsJsonArray();
		return array;
	}

	public static <T> ArrayList<T> deserialize(JsonArray array, Class resultType) {
		Gson gson = new Gson();
		ArrayList<T> list = new ArrayList<T>();
		for (int i = 0; i < array.size(); ++i)
			list.add((T) gson.fromJson(array.get(i), resultType));
		return list;
	}
}
