package report.service.v3.security.service;

import com.auth0.jwt.JWT;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static report.service.v3.security.SecurityConstants.EXPIRATION_TIME;
import static report.service.v3.security.SecurityConstants.SECRET;

public class TokenAuthenticationService {

    public static String createToken(String username) {
        String token = JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));

        return token;
    }
}