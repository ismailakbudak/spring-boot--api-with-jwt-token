package report.service.v3.security;


import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;
import report.service.v3.exception.JWTTokenMissingException;
import report.service.v3.helper.ConvertToJSON;
import report.service.v3.response.ErrorResponse;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (JWTVerificationException | JWTTokenMissingException e) {
            logger.info("JWTException occurred");
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            response.setStatus(HttpStatus.FORBIDDEN.value());
            ConvertToJSON converter = new ConvertToJSON(errorResponse);
            response.getWriter().write(converter.convert());
        }
    }
}