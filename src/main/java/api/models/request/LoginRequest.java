package api.models.request;

public class LoginRequest {
    public String email;
    public String password;
    public String type = "email";

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
