package api.models.request;

public class RegistrationRequest {

    public String email;
    public String password;

    public Integer is_accept = 1;

    public String language = "uk";
    public String promokey = "";
    public String type = "email";
    public String fingerprint = "1b942df1be8b0356e346bea9c69838ee";
    public DeviceData device = new DeviceData();

    public RegistrationRequest() {}

    public RegistrationRequest(String email, String password) {
        this();
        this.email = email;
        this.password = password;
    }

    public static class DeviceData {
        public String platform = "ANDROID";
        public String device_id = "test_device_12345";
        public String device_model = "QA_Emulator";
        public String os_version = "13";

        public DeviceData() {}
    }
}