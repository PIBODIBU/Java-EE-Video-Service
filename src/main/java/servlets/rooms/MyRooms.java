package servlets.rooms;

import models.Room;
import models.User;
import utils.DBUtils;
import utils.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

@WebServlet(urlPatterns = {"/rooms/my"})
public class MyRooms extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("rooms", DBUtils.getRoomByUser(request.getParameter("uid")));
        request.getRequestDispatcher("/jsp/room_list.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            if (action.equals("create_room")) {
                createRoom(
                        request.getParameter("room_name"),
                        request.getParameter("room_description"),
                        request.getParameter("room_owner"),
                        request.getParameter("room_color"),
                        "0"
                );
            }
        }

        request.setAttribute("rooms", DBUtils.getRoomByUser(request.getParameter("user_uid")));
        request.getRequestDispatcher("/jsp/room_list.jsp").forward(request, response);
    }

    private void createRoom(String name, String description, String owner, String color, String valueTime) {
        try {
            // Open new connection
            Connection connection = DBUtils.getConnection();

            // Preparing statement
            String sql = "INSERT INTO rooms (name, description, owner, color, room_uid, value_time) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Generate room's uid
            String roomUID = DBUtils.generateRoomUID();

            // Pass parameters to statement
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, owner);
            preparedStatement.setString(4, color);
            preparedStatement.setString(5, roomUID);
            preparedStatement.setString(6, valueTime);

            preparedStatement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
