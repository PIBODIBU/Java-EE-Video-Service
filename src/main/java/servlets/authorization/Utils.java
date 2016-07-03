package servlets.authorization;

import com.sun.istack.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import utils.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {
    @Nullable
    public static JSONObject getJSONObjectViaGET(URL url) {
        JSONObject jsonObject = null;

        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Create JSON from response
            jsonObject = new JSONObject(response.toString());
        } catch (IOException ex) {
            Logger.e(ex.getMessage());
        } catch (NullPointerException ex) {
            Logger.e(ex.getMessage());
        } catch (JSONException ex) {
            Logger.e(ex.getMessage());
        }

        return jsonObject;
    }
}
