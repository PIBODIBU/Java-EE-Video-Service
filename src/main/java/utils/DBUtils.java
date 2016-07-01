package utils;

import java.security.SecureRandom;

public class DBUtils {
    // JDBC driver name and database URL
    public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/java_ee";

    //  Database credentials
    public static final String DB_USER = "root";
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
}
