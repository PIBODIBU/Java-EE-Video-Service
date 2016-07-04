package servlets.authorization;

import com.sun.istack.Nullable;
import org.apache.commons.lang3.CharSet;
import org.apache.commons.lang3.CharSetUtils;
import org.json.JSONException;
import org.json.JSONObject;
import utils.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Utils {
    @Nullable
    public static JSONObject getJSONObjectViaGET(URL url) {
        JSONObject jsonObject = null;

        Logger.i("Utils -> getJSONObjectViaGET() -> URL: " + url);

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

    @Nullable
    public static JSONObject getJSONObjectViaPOST(URL url, HashMap<String, Object> parameters) {
        JSONObject jsonObject = null;

        Logger.i("Utils -> getJSONObjectViaGET() -> URL: " + url);

        try {
            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, Object> param : parameters.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            StringBuilder stringBuilder = new StringBuilder();
            for (int c; (c = in.read()) >= 0; )
                stringBuilder.append((char) c);

            Logger.e("Utils -> getJSONObjectViaPOST() -> Response; " + stringBuilder.toString());

            return new JSONObject(stringBuilder.toString());
        } catch (Exception ex) {
            Logger.e("Utils -> getJSONObjectViaPOST() -> " + ex.getMessage());
        }

        return jsonObject;
    }
}
