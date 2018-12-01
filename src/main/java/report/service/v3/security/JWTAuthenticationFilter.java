package report.service.v3.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import report.service.v3.entity.ApplicationUser;
import report.service.v3.exception.InvalidCredentialsException;
import report.service.v3.helper.ConvertToJSON;
import report.service.v3.response.ErrorResponse;
import report.service.v3.response.JWTSuccessResponse;
import report.service.v3.service.TokenAuthenticationService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            ApplicationUser credentials = new ObjectMapper()
                    .readValue(req.getInputStream(), ApplicationUser.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getEmail(),
                            credentials.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new InvalidCredentialsException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String token =  TokenAuthenticationService.createToken(((User) auth.getPrincipal()).getUsername());
        ConvertToJSON converter = new ConvertToJSON(new JWTSuccessResponse(token));
        res.getWriter().write(converter.convert());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest req,
                                              HttpServletResponse res,
                                              AuthenticationException failed) throws IOException, ServletException {

        ErrorResponse errorResponse = new ErrorResponse("Error: Merchant ApplicationUser credentials is not valid");
        res.setStatus(HttpStatus.FORBIDDEN.value());
        ConvertToJSON converter = new ConvertToJSON(errorResponse);
        res.getWriter().write(converter.convert());
    }
}
