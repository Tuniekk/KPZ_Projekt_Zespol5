package com.example.aplikacjanatelefon;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONObject;

public class ThingSpeakReader {

    // ThingSpeak channel details
    private static final String READ_API_KEY = "KQ28T3Z6UR3TUPQM";  // Replace with your Read API Key
    private static final String CHANNEL_ID = "2813259";  // Replace with your Channel ID
    private static final int FIELD_NUMBER = 1;  // Field number to read (e.g., field1)

    // ThingSpeak URL
    private static final String THINGSPEAK_URL = "https://api.thingspeak.com/channels/" + CHANNEL_ID + "/fields/" + FIELD_NUMBER + ".json";

    public String getThingspeakUrl(){
        return THINGSPEAK_URL;
    }

    public static void main(String[] args) {
        // Main loop
        while (true) {
            String[] result = readFromThingSpeak();
            if (result[0] != null) {
                System.out.println("Latest Value: " + result[0] + ", Timestamp: " + result[1]);
            } else {
                System.out.println("No data available or error occurred.");
            }

            try {
                Thread.sleep(15000);  // Wait 15 seconds (ThingSpeak update rate limit)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static String[] readFromThingSpeak() {
        String[] result = new String[2];  // result[0] = value, result[1] = timestamp
        try {
            // Create the URL and open a connection
            URL url = new URL(THINGSPEAK_URL + "?api_key=" + READ_API_KEY + "&results=1");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);  // Timeout after 5 seconds
            connection.setReadTimeout(5000);

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse the JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());
            if (jsonResponse.has("feeds") && jsonResponse.getJSONArray("feeds").length() > 0) {
                JSONObject latestEntry = jsonResponse.getJSONArray("feeds").getJSONObject(jsonResponse.getJSONArray("feeds").length() - 1);
                result[0] = latestEntry.optString("field" + FIELD_NUMBER);  // Get field value
                result[1] = latestEntry.optString("created_at");  // Get timestamp
            }

        } catch (Exception e) {
            System.out.println("Error fetching data from ThingSpeak: " + e.getMessage());
            result[0] = null;
            result[1] = null;
        }

        return result;
    }
}