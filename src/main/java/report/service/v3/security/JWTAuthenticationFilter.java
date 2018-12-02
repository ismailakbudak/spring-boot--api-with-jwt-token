package report.service.v3.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import report.service.v3.model.security.Merchant;
import report.service.v3.security.exception.InvalidCredentialsException;
import report.service.v3.util.JsonMapperUtil;
import report.service.v3.response.ErrorResponse;
import report.service.v3.security.response.JWTSuccessResponse;

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
            Merchant credentials = new ObjectMapper()
                    .readValue(req.getInputStream(), Merchant.class);

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

        String token =  TokenAuthenticationUtil.createToken(((User) auth.getPrincipal()).getUsername());
        res.getWriter().write(JsonMapperUtil.convert(new JWTSuccessResponse(token)));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest req,
                                              HttpServletResponse res,
                                              AuthenticationException failed) throws IOException, ServletException {

        ErrorResponse errorResponse = new ErrorResponse("Error: Merchant Merchant credentials is not valid");
        res.setStatus(HttpStatus.FORBIDDEN.value());
        res.getWriter().write(JsonMapperUtil.convert(errorResponse));
    }
}
