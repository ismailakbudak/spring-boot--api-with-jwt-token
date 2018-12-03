package report.service.v3.exception;

public class ArgumentsRequiredException extends RuntimeException {
    public ArgumentsRequiredException(String msg) {
        super(msg);
    }
}
