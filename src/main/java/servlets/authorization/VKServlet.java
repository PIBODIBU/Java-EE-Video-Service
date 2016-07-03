package servlets.authorization;

import com.sun.istack.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.DBUtils;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "VKServlet", urlPatterns = {"/login/vk"})
public class VKServlet extends HttpServlet implements AuthorizationInterface {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");

        if (code == null || code.equals("")) {
            return;
        }

        JSONObject accessTokenJSON = getAccessToken(code);

        if (accessTokenJSON == null) {
            Logger.e(getServletName() + " -> Server response is null");
            return;
        }

        ResponseModel user = getUserInfo(
                accessTokenJSON.getString("access_token"),
                String.valueOf(accessTokenJSON.getInt("user_id")),
                accessTokenJSON.getString("email")
        );

        Logger.i(getServletName() + " -> doGet() ->" +
                "\n\tSocial uid: " + user.getSocialUID() +
                "\n\tName: " + user.getName() +
                "\n\tSurname: " + user.getSurname() +
                "\n\tEmail: " + user.getEmail() +
                "\n\tLogin type: " + user.getLoginType()
        );

        if (!isUserSignedUp(user.getEmail())) {
            // User is login for the first time
            Logger.i(getServletName() + " -> doGet() -> isUserSignedUp() -> false");

            addUserToDatabase(user);
        } else {
            // User have logged before, just update info and set session
            Logger.i(getServletName() + " -> doGet() -> isUserSignedUp() -> true");

            HttpSession session = request.getSession();
            session.setAttribute("name", user.getName());
            session.setAttribute("surname", user.getSurname());
            session.setAttribute("email", user.getEmail());

            response.sendRedirect("/home");
        }
    }

    public String getLoginType() {
        return loginTypes.vk.toString();
    }

    @Nullable
    public JSONObject getAccessToken(String code) {
        URL url;

        String BASE_URL = "https://oauth.vk.com/access_token?";
        String CLIENT_ID = "5084652";
        String CLIENT_SECRET = "PaY3jqJZO6c2iLjwDGsQ";
        String REDIRECT_URI = "http://localhost:8080/login/vk";

        try {
            url = new URL(BASE_URL + "client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&redirect_uri=" + REDIRECT_URI + "&code=" + code);
        } catch (MalformedURLException ex) {
            Logger.e(getServletName() + " -> getAccessToken() -> " + ex.getMessage());
            return null;
        }

        return Utils.getJSONObjectViaGET(url);
    }

    public ResponseModel getUserInfo(String accessToken, String userId, String email) {
        String BASE_URL = "https://api.vk.com/method/users.get?";
        String FIELDS = "screen_name";
        String VERSION = "5.52";

        JSONObject jsonObject;
        URL url;

        try {
            url = new URL(BASE_URL + "user_ids=" + userId + "&fields=" + FIELDS + "&v=" + VERSION);
            Logger.i(getServletName() + " -> getUserInfo() -> URL: " + url);
        } catch (MalformedURLException ex) {
            Logger.e(getServletName() + " -> getUserInfo() -> " + ex.getMessage());
            return null;
        }

        jsonObject = Utils.getJSONObjectViaGET(url);
        JSONArray jsonArray = jsonObject.getJSONArray("response");

        Logger.i(getServletName() + " -> getUserInfo() -> " + jsonObject.toString());

        if (jsonArray == null) {
            Logger.e(getServletName() + " -> getUserInfo() -> Server response is null");
            return null;
        }

        return new ResponseModel(
                userId,
                jsonArray.getJSONObject(0).getString("first_name"),
                jsonArray.getJSONObject(0).getString("last_name"),
                email,
                getLoginType()
        );
    }

    public boolean isUserSignedUp(String email) {
        try {
            PreparedStatement prepStat;
            ResultSet resultSet;
            Connection connection;
            boolean isLoggedIn;

            // Prepare SQL query
            String sql = "SELECT * FROM users WHERE BINARY email=?";

            // Create connection
            connection = DBUtils.getConnection();
            prepStat = connection.prepareStatement(sql);

            // Pass parameters to statement
            prepStat.setString(1, email);

            // Create ResultSet instance
            resultSet = prepStat.executeQuery();
            isLoggedIn = resultSet.first();

            // Close connections
            prepStat.close();
            connection.close();

            return isLoggedIn;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean addUserToDatabase(ResponseModel user) {
        // TODO
        return false;
    }
}
