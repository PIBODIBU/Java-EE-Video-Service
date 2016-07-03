package utils;

import models.Room;
import models.User;

import java.security.SecureRandom;
import java.sql.*;
import java.util.LinkedList;

public class DBUtils {
    // JDBC driver name and database URL
    public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://207.244.95.136:3306/java_ee";

    //  Database credentials
    public static final String DB_USER = "java_ee";
    public static final String DB_PASS = "111111";

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    static SecureRandom rnd = new SecureRandom();

    private static int ROOM_UID_LENGTH = 10;

    public static String generateRoomUID() {
        StringBuilder sb = new StringBuilder(ROOM_UID_LENGTH);
        for (int i = 0; i < ROOM_UID_LENGTH; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // Register JDBC driver
        Class.forName(DBUtils.DB_DRIVER);

        // Open a connection
        return DriverManager.getConnection(DBUtils.DB_URL, DBUtils.DB_USER, DBUtils.DB_PASS);
    }

    public static User getUserByUid(Connection connection, String uid) throws SQLException {
        String ownersSQL = "SELECT * FROM users WHERE BINARY user_uid=?";

        PreparedStatement preparedStatement = connection.prepareStatement(ownersSQL);
        preparedStatement.setString(1, uid);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.first();

        return new User(
                resultSet.getString("name"),
                resultSet.getString("surname"),
                resultSet.getString("user_uid"));
    }

    public static LinkedList<Room> getRooms() {
        ResultSet roomsResultSet = null;
        LinkedList<Room> rooms = new LinkedList<Room>();

        try {
            // Prepare SQL query
            String roomsSQL = "SELECT * FROM rooms";
            PreparedStatement roomsPrepStat = null;

            // Pass parameters to statement
            Connection connection = getConnection();
            roomsPrepStat = connection.prepareStatement(roomsSQL);

            // Create ResultSet instance
            if (roomsPrepStat != null) {
                roomsResultSet = roomsPrepStat.executeQuery();
            }

            // Create new Room objects
            if (roomsResultSet != null) {
                while (roomsResultSet.next()) {
                    rooms.add(
                            new Room(
                                    roomsResultSet.getString("name"),
                                    roomsResultSet.getString("description"),
                                    roomsResultSet.getString("owner"),
                                    getUserByUid(connection, roomsResultSet.getString("owner")),
                                    roomsResultSet.getString("color"),
                                    roomsResultSet.getString("room_uid"),
                                    roomsResultSet.getString("value_time")
                            ));
                }
            }

            // Close connection
            if (roomsPrepStat != null) {
                roomsPrepStat.close();
            }
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return rooms;
    }

    public static LinkedList<Room> getRoomByUser(String userUid) {
        ResultSet roomsResultSet = null;
        LinkedList<Room> rooms = new LinkedList<Room>();

        if (userUid == null) {
            Logger.e("User's uid is null");
            return null;
        }

        try {
            Connection connection = getConnection();

            // Prepare SQL query
            String roomsSQL = "SELECT * FROM rooms WHERE BINARY owner=?";
            PreparedStatement roomsPrepStat = null;

            // Pass parameters to statement
            try {
                roomsPrepStat = connection.prepareStatement(roomsSQL);
                roomsPrepStat.setString(1, userUid);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            // Create ResultSet instance
            if (roomsPrepStat != null) {
                roomsResultSet = roomsPrepStat.executeQuery();
            }

            // Create new Room objects
            if (roomsResultSet != null) {
                while (roomsResultSet.next()) {
                    rooms.add(
                            new Room(
                                    roomsResultSet.getString("name"),
                                    roomsResultSet.getString("description"),
                                    roomsResultSet.getString("owner"),
                                    getUserByUid(connection, roomsResultSet.getString("owner")),
                                    roomsResultSet.getString("color"),
                                    roomsResultSet.getString("room_uid"),
                                    roomsResultSet.getString("value_time")
                            ));
                }
            }

            // Close connection
            if (roomsPrepStat != null) {
                roomsPrepStat.close();
            }
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return rooms;
    }
}
