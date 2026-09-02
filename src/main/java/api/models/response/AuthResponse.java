package api.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthResponse {
    public boolean status;
    public UserResponseData user;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UserResponseData {
        public String token;
    }
}