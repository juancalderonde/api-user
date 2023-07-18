package com.juancalderondev.apiuser.security;

import com.juancalderondev.apiuser.models.Users;
import com.juancalderondev.apiuser.modelsDTO.TokenProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

public class JwtUtil {
    private String base64EncodedKey = ""; // Replace with your own custom secret key
    public String generateToken(Users userDetails){

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 900000);//15 minutes for token
        byte[] decodedKey = Base64.getDecoder().decode(base64EncodedKey);
        return Jwts.builder()
                .setSubject(Long.toString(userDetails.getId()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512,decodedKey)
                .compact();
    }
    public TokenProperties decodeToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if(header == null | header.startsWith("Bearer ")){ return null; }
        try {
            String token = header.substring(7);

            Jws<Claims> parsedToken = Jwts.parserBuilder().setSigningKey(base64EncodedKey).build().parseClaimsJws(token);
            Claims claims = parsedToken.getBody();
            TokenProperties validationToken = new TokenProperties(claims.getIssuedAt(), claims.getExpiration(), Long.parseLong(claims.getSubject()));
            return validationToken;
        }catch(SignatureException e){
            return null;
        }
    }
}
