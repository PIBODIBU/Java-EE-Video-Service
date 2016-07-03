package servlets.rooms;

import models.User;
import utils.DBUtils;
import utils.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@WebServlet(urlPatterns = {"/rooms/enter"})
public class Room extends HttpServlet {
    @Override
    public void init() throws ServletException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        models.Room room = null;
        String roomUID = request.getParameter("room_uid");

        try {
            // Register driver's class
            Class.forName(DBUtils.DB_DRIVER);

            // Open a connection
            Connection connection = DriverManager.getConnection(DBUtils.DB_URL, DBUtils.DB_USER, DBUtils.DB_PASS);

            // Prepare SQL query
            String sql = "SELECT * FROM rooms WHERE BINARY room_uid=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, roomUID);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.first();

            room = new models.Room(
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getString("owner"),
                    getUser(resultSet.getString("owner")),
                    resultSet.getString("color"),
                    resultSet.getString("room_uid"),
                    resultSet.getString("value_time")
            );


        } catch (Exception ex) {
            Logger.e(ex.getMessage());
        }

        request.setAttribute("room", room);
        request.getRequestDispatcher("/jsp/room.jsp").forward(request, response);
    }

    private User getUser(String uid) {
        try {
            Connection connection = DriverManager.getConnection(DBUtils.DB_URL, DBUtils.DB_USER, DBUtils.DB_PASS);
            String sql = "SELECT * FROM users WHERE BINARY user_uid=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, uid);

            ResultSet resultSet = preparedStatement.executeQuery();

            return new User(
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getString("user_uid"));
        } catch (SQLException ex) {
            Logger.e(ex.getMessage());
            return null;
        }
    }
}
