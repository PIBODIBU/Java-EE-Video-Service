package servlets.authorization;

import org.json.JSONObject;

public interface AuthorizationInterface {
    enum loginTypes {vk, googleplus, fb};

    JSONObject getAccessToken(String code);

    ResponseModel getUserInfo(JSONObject response);

    boolean isUserSignedUp(String email);

    boolean addUserToDatabase(ResponseModel user);

    String getLoginType();
}
