package servlets.authorization;

public class ResponseModel {
    private String socialUID;
    private String name;
    private String surname;
    private String email;
    private String loginType;

    public ResponseModel(String socialUID, String name, String surname, String email, String loginType) {
        this.socialUID = socialUID;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.loginType = loginType;
    }

    public String getSocialUID() {
        return socialUID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getLoginType() {
        return loginType;
    }
}
