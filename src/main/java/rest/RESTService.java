package rest;

import models.User;
import utils.DBUtils;
import utils.Logger;

import javax.activation.MimeType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

@Path("/users")
public class RESTService {
    @GET
    @Produces("application/json")
    public Response getUsers() {
        LinkedList<User> users = new LinkedList<User>();

        try {
            Class.forName(DBUtils.DB_DRIVER);
            String sql = "SELECT * FROM users";
            Connection connection = DriverManager.getConnection(DBUtils.DB_URL, DBUtils.DB_USER, DBUtils.DB_PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("user_uid")));
            }
        } catch (Exception ex) {
            Logger.e(ex.getMessage());
        }

        return Response.ok(users).build();
    }

    @Path("{uid}")
    @GET
    @Produces("application/json")
    public Response getUser(@PathParam("uid") String uid) {
        LinkedList<User> users = new LinkedList<User>();

        try {
            Class.forName(DBUtils.DB_DRIVER);
            String sql = "SELECT * FROM users WHERE BINARY user_uid=?";
            Connection connection = DriverManager.getConnection(DBUtils.DB_URL, DBUtils.DB_USER, DBUtils.DB_PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, uid);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.first();

            users.add(new User(
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getString("user_uid")
            ));
        } catch (Exception ex) {
            Logger.e(ex.getMessage());
        }

        return Response.ok(users).build();
    }

}