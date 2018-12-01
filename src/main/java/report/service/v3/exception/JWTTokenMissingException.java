package report.service.v3.exception;

import org.springframework.security.core.AuthenticationException;

public class JWTTokenMissingException extends AuthenticationException {

    public JWTTokenMissingException(String msg) {
        super(msg);
    }

}
