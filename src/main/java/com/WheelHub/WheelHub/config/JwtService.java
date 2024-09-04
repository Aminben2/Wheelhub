package com.WheelHub.WheelHub.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "2740e0876f369ed9f35168a3e67a469e3f6929a064ee38647a29cfc7845bacfef69b8173fa924ba8bfe196d6e02640eaa49b3ff3d85af0b0167f1f9fb39d33f224b6c59307bfd9f7c954d01b1d464a330d45ab5f071ab5ebf6220069cc499882166a623e0e91a91997b77d737fd7a5501ad903c2aab96bef9e44c9bf3303dc89a54e2ccdafdfa7107e0af1d5fd9b34f7e38b746438a9bf7bfd1f2a18ba940701dd85e8cd526cdb003098571ad72f9b35064ce77aa470cbe804642fe4cb69e883aca6125c3c301762c85903d88f2fd4ef43fe00ed19003b4422b8f8b79739f221015d32291ed487cd805229da629373dadda8eb34f0622c1020bc69e905edf4f9430defe62224abfb8c04707d44cc7f11e6abc7be88c4491c3bfae87e6edd8a3e64035402f80ee4a1b327c8ea2d78b7063b321fb42654ff0c96969fb305440c1b4e65fe96c0d3cc2ea605cc3068c2fbda142b464aed3c2300a5e00116f655b27faf0266910de04486d1ba98d9f7b1d7302bfff4184327696c75e26b625b6fe44f79c48238fc9dec63465762d0ed6645b75619ae507b181ba6a3014337674dcfe2a14744abe84176d387a6edcee6cab5df028ec3bf6721b295c61e7f43c20036838cc120e49abd2fbe6c184960275c5da5c5bdd7d5733b06802244822446e3597b5c50927ec855aff29d49b58c9ab4d5054a25f8b7a917e6b243f23b2cc109a9f2";
    public String extractUsername(String jwt) {
        return extractClaims(jwt, Claims::getSubject);
    }

    public <T> T extractClaims(String token, Function<Claims,T> claimsTFunction)
    {
        final Claims claims = extactALlClaims(token);
        return claimsTFunction.apply(claims);
    }

    public String generateToken(UserDetails userDetails)
    {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims,
    UserDetails userDetails)
    {
       return Jwts
               .builder()
               .setClaims(extraClaims)
               .setSubject(userDetails.getUsername())
               .setIssuedAt(new Date(System.currentTimeMillis()))
               .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60* 24))
               .signWith(getSigningKey(), SignatureAlgorithm.HS256)
               .compact();
    }

    private Claims extactALlClaims(String jwt)
    {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    public boolean isTokenValid(String token, UserDetails userDetails)
    {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token)
    {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token)
    {
        return extractClaims(token, Claims::getExpiration);
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
