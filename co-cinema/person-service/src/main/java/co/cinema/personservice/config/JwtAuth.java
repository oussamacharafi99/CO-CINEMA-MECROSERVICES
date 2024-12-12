package co.cinema.personservice.config;

import co.cinema.personservice.enums.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Set;

@Service
public class JwtAuth {

    // Clé secrète en base64 encodée
    private static final String SECRET_KEY_BASE64 = "357638792F423F4428472B4B6250655368566D597133743677397A2443264629";

    // Convertir la clé base64 en objet Key
    public static final Key SECRET_KEY = Keys.hmacShaKeyFor(java.util.Base64.getDecoder().decode(SECRET_KEY_BASE64));

    public static String generateToken(String username, Set<String> roles) {
        System.out.println("///////////////////" + username + "GENERATETOKEN JWTAUTH");
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) // 24 hours
                .claim("roles", roles)
                .signWith(SECRET_KEY)
                .compact();
    }
}