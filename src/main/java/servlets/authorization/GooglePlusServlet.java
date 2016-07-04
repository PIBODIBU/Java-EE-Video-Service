package servlets.authorization;

import org.json.JSONArray;
import org.json.JSONObject;
import utils.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

@WebServlet(name = "GooglePlusServlet", urlPatterns = {"/login/googleplus"})
public class GooglePlusServlet extends AuthorizationServlet implements AuthorizationInterface {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    public String getLoginType() {
        return loginTypes.googleplus.toString();
    }

    public JSONObject getAccessToken(String code) {
        URL url;
        HashMap<String, Object> parameters = new HashMap<String, Object>();

        String BASE_URL = "https://www.googleapis.com/oauth2/v4/token";
        String CLIENT_ID = "506968348094-7u8kbg2q3v4jn5glpr1q4ti8jhnk597p.apps.googleusercontent.com";
        String CLIENT_SECRET = "mjM7-6Ohi3Gn-wHo54MvWgPg";
        String REDIRECT_URI = "http://localhost:8080/login/googleplus";

        try {
            url = new URL(BASE_URL);
        } catch (MalformedURLException ex) {
            Logger.e(getServletName() + " -> getAccessToken() -> " + ex.getMessage());
            return null;
        }

        parameters.put("client_id", CLIENT_ID);
        parameters.put("client_secret", CLIENT_SECRET);
        parameters.put("redirect_uri", REDIRECT_URI);
        parameters.put("code", code);
        parameters.put("grant_type", "authorization_code");

        return Utils.getJSONObjectViaPOST(url, parameters);
    }

    public ResponseModel getUserInfo(JSONObject response) {
        String BASE_URL = "https://www.googleapis.com/oauth2/v2/userinfo?";
        String ALT = "json";

        JSONObject jsonObject;
        URL url;

        try {
            url = new URL(BASE_URL + "&alt=" + ALT + "&access_token=" + response.getString("access_token"));
            Logger.i(getServletName() + " -> getUserInfo() -> URL: " + url);
        } catch (MalformedURLException ex) {
            Logger.e(getServletName() + " -> getUserInfo() -> " + ex.getMessage());
            return null;
        }

        jsonObject = Utils.getJSONObjectViaGET(url);

        Logger.i(getServletName() + " -> getUserInfo() -> " + jsonObject.toString());

        return new ResponseModel(
                jsonObject.getString("id"),
                jsonObject.getString("name").split(" ")[0],
                jsonObject.getString("name").split(" ")[1],
                jsonObject.getString("email"),
                getLoginType()
        );
    }
}
