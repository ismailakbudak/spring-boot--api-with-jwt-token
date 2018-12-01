package report.service.v3.response;

public class ErrorResponse {

    private Integer code;
    private String message;
    private String status;

    public ErrorResponse(String message) {
        this.code = 0;
        this.message = message;
        this.status = "DECLINED";
    }

    public Integer getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
