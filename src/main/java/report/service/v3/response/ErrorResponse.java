package report.service.v3.response;

import static report.service.v3.constants.ResponseConstants.DECLINED;
import static report.service.v3.constants.ResponseConstants.DECLINED_CODE;

public class ErrorResponse {

    private Integer code;
    private String message;
    private String status;

    public ErrorResponse(String message) {
        this.code = DECLINED_CODE;
        this.message = message;
        this.status = DECLINED;
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
