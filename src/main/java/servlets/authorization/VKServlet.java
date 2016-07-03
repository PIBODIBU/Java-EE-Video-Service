package servlets.authorization;

import com.sun.istack.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@WebServlet(name = "VKServlet", urlPatterns = {"/login/vk"})
public class VKServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");

        if (code == null || code.equals("")) {
            return;
        }

        String accessToken = getAccessToken(code);

        if (accessToken == null) {
            Logger.e("Serer response is null");
            return;
        }

        JSONObject jsonObject = new JSONObject(accessToken);
        Logger.i(jsonObject.getString("access_token"));
    }

    @Nullable
    private String getAccessToken(String code) {
        URL url;
        String accessToken = null;

        try {
            url = new URL("https://oauth.vk.com/access_token?client_id=5084652&client_secret=PaY3jqJZO6c2iLjwDGsQ&redirect_uri=http://localhost:8080/login/vk&code=" + code);
        } catch (MalformedURLException ex) {
            Logger.e(ex.getMessage());
            return null;
        }

        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            accessToken = response.toString();

        } catch (IOException ex) {
            Logger.e(ex.getMessage());
        } catch (NullPointerException ex) {
            Logger.e(ex.getMessage());
        }

        return accessToken;
    }
}
