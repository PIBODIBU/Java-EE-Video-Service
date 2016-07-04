package servlets.authorization;

import utils.DBUtils;
import utils.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "AuthorizationServlet")
public abstract class AuthorizationServlet extends HttpServlet implements AuthorizationInterface {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");

        if (code == null || code.equals("")) {
            return;
        }

        ResponseModel user = getUserInfo(getAccessToken(code));

        Logger.i(getServletName() + " -> doGet() ->" +
                "\n\tSocial uid: " + user.getSocialUID() +
                "\n\tName: " + user.getName() +
                "\n\tSurname: " + user.getSurname() +
                "\n\tEmail: " + user.getEmail() +
                "\n\tLogin type: " + user.getLoginType()
        );

        if (!isUserSignedUp(user.getEmail())) {
            // User is login for the first time
            Logger.e(getServletName() + " -> doGet() -> User is not signed up");

            addUserToDatabase(user);
        } else {
            // User have logged before, just update info and set session
            // TODO info updating
            Logger.i(getServletName() + " -> doGet() -> User has already signed up");
        }

        HttpSession session = request.getSession();
        session.setAttribute("name", user.getName());
        session.setAttribute("surname", user.getSurname());
        session.setAttribute("email", user.getEmail());

        response.sendRedirect("/home");
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
            Logger.e(getServletName() + " -> isUserSignedUp() -> " + ex.getMessage());
            return false;
        }
    }

    public boolean addUserToDatabase(ResponseModel user) {
        try {
            PreparedStatement prepStat;
            Connection connection;
            int resultCode;

            // Prepare SQL query
            String sql = "INSERT INTO users (name, surname, social_id, email, login_type) VALUES (?,?,?,?,?)";
            String sqlUTF8 = "SET NAMES utf8";

            // Create connection
            connection = DBUtils.getConnection();

            // Set names to UTF8
            prepStat = connection.prepareStatement(sqlUTF8);
            prepStat.execute();

            // Prepare inserting statement
            prepStat = connection.prepareStatement(sql);

            // Pass parameters to statement
            prepStat.setString(1, user.getName());
            prepStat.setString(2, user.getSurname());
            prepStat.setString(3, user.getSocialUID());
            prepStat.setString(4, user.getEmail());
            prepStat.setString(5, user.getLoginType());

            resultCode = prepStat.executeUpdate();

            // Close connections
            prepStat.close();
            connection.close();

            return resultCode == 1;

        } catch (Exception ex) {
            Logger.e(getServletName() + " -> addUserToDatabase() -> " + ex.getMessage());
            return false;
        }
    }

    public String getLoginType() {
        return null;
    }
}
