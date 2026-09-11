package api.models.request;

public class RegistrationRequest {

    private String email;
    private String password;
    private Integer is_accept;
    private String language;
    private String promokey;
    private String type;
    private String fingerprint;
    private DeviceData device;

    private RegistrationRequest(Builder builder) {
        this.email = builder.email;
        this.password = builder.password;
        this.is_accept = builder.is_accept;
        this.language = builder.language;
        this.promokey = builder.promokey;
        this.type = builder.type;
        this.fingerprint = builder.fingerprint;
        this.device = builder.device;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String email;
        private String password;

        private Integer is_accept = 1;
        private String language = "uk";
        private String promokey = "";
        private String type = "email";
        private String fingerprint = "1b942df1be8b0356e346bea9c69838ee";
        private DeviceData device = new DeviceData();

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder is_accept(Integer is_accept) {
            this.is_accept = is_accept;
            return this;
        }

        public Builder language(String language) {
            this.language = language;
            return this;
        }

        public Builder promokey(String promokey) {
            this.promokey = promokey;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder fingerprint(String fingerprint) {
            this.fingerprint = fingerprint;
            return this;
        }

        public Builder device(DeviceData device) {
            this.device = device;
            return this;
        }

        public RegistrationRequest build() {
            return new RegistrationRequest(this);
        }
    }

    public static class DeviceData {
        public String platform = "ANDROID";
        public String device_id = "test_device_12345";
        public String device_model = "QA_Emulator";
        public String os_version = "13";

        public DeviceData() {}
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Integer getIs_accept() { return is_accept; }
    public String getLanguage() { return language; }
    public String getPromokey() { return promokey; }
    public String getType() { return type; }
    public String getFingerprint() { return fingerprint; }
    public DeviceData getDevice() { return device; }
}