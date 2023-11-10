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
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {
    private final Key key;
    private final UserMapper userMapper;

    public JwtProvider(@Value("${jwt.secret}") String secret,
                       @Autowired UserMapper userMapper) {
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.userMapper = userMapper; //@reqArgs 말고 직접 생성자 통해 DI 하는 법
    }

    public String generateToken(PrincipalUser principalUser) {
        int userId = principalUser.getUser().getUserId();
        String email = principalUser.getUser().getEmail();
        String oauth2Id = principalUser.getUser().getOauth2Id();

        Date date = new Date(new Date().getTime() + (1000 * 60 * 60 * 24));
        //토큰 생성
        return Jwts.builder()
                .setSubject("AccessToken")
                .setExpiration(date)
                .claim("userId", userId)
                .claim("email", email)
                .claim("oauth2Id", oauth2Id)
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
//            System.out.println("getClaims() >> " + claims);
        } catch (Exception e) {
//            System.out.println(e.getClass() + " >>> " + e.getMessage());
        }
        return claims;
    }

    //bearer 떼기
    public String getToken(String bearerToken) {
        if(!StringUtils.hasText(bearerToken)){
            return null;
        }
        return bearerToken.substring("Bearer ".length());
    }

    public Authentication getAuthentication(String token) {
        //db에서 User를 찾아와서 principalUser에 넣음

        Claims claims = getClaims(token); //getClaims: 토큰의 내용을 추출
//        System.out.println(claims);
        if(claims == null) {
            return null; //Auth가 null이다 -> 토큰이 유효하지 x
        }

        //토큰이 유효하지만 db에서 User를 지웠을 수 있음(탈퇴)
        User user = userMapper.findUserByEmail(claims.get("email").toString());

        if(user == null){
            return null; //회원탈퇴했을 시
        }

//        PrincipalUser principalUser = new PrincipalUser(user, claims.get("") ); //PrincipalUser(User, Map<> attributes, nameAttributeKey)
//        return new OAuth2AuthenticationToken();
        return null;
    }
}
