package report.service.v3.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import report.service.v3.exception.ArgumentsRequiredException;
import report.service.v3.exception.InvalidFieldException;
import report.service.v3.response.ErrorResponse;

import java.rmi.NotBoundException;

@ControllerAdvice
@RestController
public class ResponseEntityExceptionHandlerConfig extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ArgumentsRequiredException.class)
    public final ResponseEntity<ErrorResponse> handleArgumentsRequiredException(ArgumentsRequiredException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFieldException.class)
    public final ResponseEntity<ErrorResponse> handleArgumentsRequiredException(InvalidFieldException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotBoundException.class)
    public final ResponseEntity<ErrorResponse> handleArgumentsRequiredException(NotBoundException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<ErrorResponse> handleArgumentsRequiredException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}