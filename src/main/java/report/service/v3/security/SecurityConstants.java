package report.service.v3.security;

public class SecurityConstants {
    public static final String LOGIN_PATH = "/api/v3/merchant/user/login";
    public static final String AUTH_REQUIRED_MESSAGE = "Token is required";

    static final String SECRET = "SecretKeyToGenJWTs";
    static final long EXPIRATION_TIME = 600_000; // 10 minutes
    static final String HEADER_STRING = "Authorization";
    static final String LOGIN_FAIL_MESSAGE = "Error: Merchant credentials is not valid";
}