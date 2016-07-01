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
        request.setAttribute("rooms", getRoomList(request.getParameter("uid")));
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

        request.setAttribute("rooms", getRoomList(request.getParameter("user_uid")));
        request.getRequestDispatcher("/jsp/room_list.jsp").forward(request, response);
    }

    private LinkedList<Room> getRoomList(String userUid) {
        ResultSet roomsResultSet = null;
        ResultSet ownersResultSet = null;
        LinkedList<Room> rooms = new LinkedList<Room>();

        if (userUid == null) {
            Logger.e("User's uid is null");
            return null;
        }

        try {
            // Register JDBC driver
            Class.forName(DBUtils.DB_DRIVER);

            // Open a connection
            Connection conn = DriverManager.getConnection(DBUtils.DB_URL, DBUtils.DB_USER, DBUtils.DB_PASS);

            // Prepare SQL query
            String roomsSQL = "SELECT * FROM rooms WHERE BINARY owner=?";
            PreparedStatement roomsPrepStat = null;

            // Pass parameters to statement
            try {
                roomsPrepStat = conn.prepareStatement(roomsSQL);
                roomsPrepStat.setString(1, userUid);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            // Create ResultSet instance
            if (roomsPrepStat != null) {
                roomsResultSet = roomsPrepStat.executeQuery();
            }

            PreparedStatement ownersPrepStat = null;

            // Create new Room objects
            if (roomsResultSet != null) {
                while (roomsResultSet.next()) {
                    String ownersSQL = "SELECT * FROM users WHERE BINARY user_uid=?";
                    ownersPrepStat = conn.prepareStatement(ownersSQL);
                    ownersPrepStat.setString(1, roomsResultSet.getString("owner"));
                    ownersResultSet = ownersPrepStat.executeQuery();
                    ownersResultSet.first();

                    rooms.add(
                            new Room(
                                    roomsResultSet.getString("name"),
                                    roomsResultSet.getString("description"),
                                    roomsResultSet.getString("owner"),
                                    new User(ownersResultSet.getString("name"),
                                            ownersResultSet.getString("user_uid")),
                                    roomsResultSet.getString("color"),
                                    roomsResultSet.getString("room_uid")
                            ));
                }
            }

            // Close connection
            if (roomsPrepStat != null) {
                roomsPrepStat.close();
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return rooms;
    }

    private void createRoom(String name, String description, String owner, String color, String valueTime) {
        try {
            // Register JDBC driver class
            Class.forName(DBUtils.DB_DRIVER);

            // Open new connection
            Connection connection = DriverManager.getConnection(DBUtils.DB_URL, DBUtils.DB_USER, DBUtils.DB_PASS);

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

            System.out.println("Prepared SQL statement: " + preparedStatement);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
