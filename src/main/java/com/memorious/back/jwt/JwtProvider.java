package com.memorious.back.jwt;

import com.memorious.back.entity.User;
import com.memorious.back.repository.UserMapper;
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
import java.util.HashMap;

@Component
public class JwtProvider {
    private final Key key;
    private final UserMapper userMapper;

    public JwtProvider(@Value("${jwt.secret}") String secret,
                       @Autowired UserMapper userMapper) {
        key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secret));
        this.userMapper = userMapper;
    }

    public String generateToken(User user) {
        String email = user.getEmail();
        String nickname = user.getNickname();
        String oauth2Id = user.getOauth2Id();
        int userId = user.getUserId();
        String role = user.getRole();

        Date expiryDate = new Date(new Date().getTime() + (1000 * 60 * 60* 24));
        return Jwts.builder()
                .setSubject("AccessToken")
                .setExpiration(expiryDate)
                .claim("email", email)
                .claim("nickname", nickname)
                .claim("oauth2Id", oauth2Id)
                .claim("userId", userId)
                .claim("roles", role)
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

        User user = userMapper.findUserByEmail(claims.get("email").toString());

        if(user == null) {
            return null;
        }

        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("id", claims.get("oauth2Id").toString());
        PrincipalUser principalUser = new PrincipalUser(user, attributes, "id");
        return new UsernamePasswordAuthenticationToken(principalUser, null, principalUser.getAuthorities());
    }

}
