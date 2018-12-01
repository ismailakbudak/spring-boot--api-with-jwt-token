package report.service.v3.security;

public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 600_000; // 10 minutes
    public static final String HEADER_STRING = "Authorization";
    public static final String LOGIN_PATH = "/api/v3/merchant/user/login";
}