package report.service.v3.response;

public class JWTSuccessResponse {

    private String token;
    private String status;

    public JWTSuccessResponse(String token) {
        this.token = token;
        this.status = "APPROVED";
    }

    public String getStatus() {
        return status;
    }

    public String getToken() {
        return token;
    }
}
