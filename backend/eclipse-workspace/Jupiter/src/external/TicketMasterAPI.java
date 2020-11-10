package external;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TicketMasterAPI {
	private static final String URL = "https://app.ticketmaster.com/discovery/v2/events.json";
	private static final String DEFAULT_KEYWORD = ""; // no restriction
	private static final String API_KEY = "	MXtQ0WalKGTMmHcq18LuWGAzyrWXd3UV"; // avoid malicious visiting

	private static final String EMBEDDED = "_embedded";
	private static final String EVENTS = "events";

	public JSONArray search(double lat, double lon, String keyword) {
		if (keyword == null) {
			keyword = DEFAULT_KEYWORD;
		}

		try {
			keyword = java.net.URLEncoder.encode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String geoHash = GeoHash.encodeGeohash(lat, lon, 8);

		String query = String.format("apikey=%s&geoPoint=%s&keyword=%s&radius=%s", API_KEY, geoHash, keyword, 50);

		try {
			// initiate http request
			HttpURLConnection connection = (HttpURLConnection) new URL(URL + "?" + query).openConnection();
			int responseCode = connection.getResponseCode();

			System.out.println("nSending 'GET' request to URL:" + URL + "?" + query);
			System.out.println("Response Code" + responseCode);

			StringBuilder response = new StringBuilder(); // need a modifiable String
			try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
				String inputline;
				while ((inputline = in.readLine()) != null) {
					response.append(inputline);
				}
			} // BufferedReader closed automatically after try {}

			JSONObject obj = new JSONObject(response.toString());
			if (obj.isNull(EMBEDDED)) { // key does not appear in given JSONObject
				return new JSONArray();
			}

			JSONObject embedded = obj.getJSONObject(EMBEDDED);
			JSONArray events = embedded.getJSONArray(EVENTS);
			return events;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new JSONArray();
	}

	// set a internal debug function
	private void queryAPI(double lat, double lon) {
		JSONArray events = search(lat, lon, null);
		try {
			for (int i = 0; i < events.length(); i++) {
				JSONObject event = events.getJSONObject(i);
				System.out.println(event);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TicketMasterAPI tmAPI = new TicketMasterAPI();
		// Mountain View, CA
		// tmApi.queryAPI(37.38, -122.08);
		// London, UK
		// tmApi.queryAPI(51.503364, -0.12);
		// Houston, TX
		tmAPI.queryAPI(29.682684, -95.295410);

	}
}
