package com.memorious.back.jwt;

import com.memorious.back.entity.User;
import com.memorious.back.repository.AuthMapper;
import com.memorious.back.security.PrincipalUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {
    private final Key key;
    private final AuthMapper authMapper;

    public JwtProvider(@Value("${jwt.secret}") String secret,
                       @Autowired AuthMapper authMapper) {
        key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secret));
        this.authMapper = authMapper;
    }

    public String generateToken(PrincipalUser principalUser) {
        String email = principalUser.getUser().getEmail();
        String nickname = principalUser.getUser().getNickname();
        String oauth2Id = principalUser.getUser().getOauth2Id();
        int userId = principalUser.getUser().getUserId();

        Date expiryDate = new Date(new Date().getTime() + (1000 * 60 * 60* 24));
        return Jwts.builder()
                .setSubject("AccessToken")
                .setExpiration(expiryDate)
                .claim("email", email)
                .claim("nickname", nickname)
                .claim("oauth2Id", oauth2Id)
                .claim("userId", userId)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims getClaims(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }catch(Exception e) {
            System.out.println("토큰오류");
        }
        return claims;
    }

    public String getToken(String bearerToken){

        if(!StringUtils.hasText(bearerToken)) {
            return null;
        }
        return bearerToken.substring("Bearer ".length());
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        if(claims == null) {
            return null;
        }
        System.out.println(claims);
        User user = authMapper.findUserByEmail(claims.get("email").toString());
        if(user == null) {
            return null;
        }
        PrincipalUser principalUser = new PrincipalUser(user);
        return new UsernamePasswordAuthenticationToken(principalUser, null, principalUser.getAuthorities());
    }

    public String generateAuthMailToken(String email) {
    Date date = new Date(new Date().getTime() + 1000 * 60 * 5);

    return Jwts.builder()
            .setSubject("AuthenticationEmailToken")
            .setExpiration(date)
            .claim("email", email)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

}
