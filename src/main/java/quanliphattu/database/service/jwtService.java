package quanliphattu.database.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import quanliphattu.database.Repository.phattuRepo;
import quanliphattu.database.Repository.tokenRepo;
import quanliphattu.database.dto.InfoToken;
import quanliphattu.database.models.phattu;
import quanliphattu.database.models.token;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Component
public class jwtService {
    InfoToken itk = new InfoToken();
    @Autowired
    private tokenRepo tkr;
    @Autowired
    private phattuRepo ptr;

    public static final String SECRET = "7a337564396c573765734e45573576614152536848637173434751546732674f36504d3937524e464c44576f776c4432485a6971302b72397a587237725457660a";


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String genarateToken(String gmail) {
        Map<String, Object> claims = new HashMap<>();
//        String token = createToken(claims, gmail);
//       addTokenDb(gmail);


        return createToken(claims, gmail);
    }

    public void addTokenDb(String gmail) {
        String token = genarateToken(gmail);
        List<token> tokenList = tkr.findAll();
        Optional<phattu> phattuOptional = ptr.findByEmail(gmail);
        phattu phattu = phattuOptional.get();
        for(token i:tokenList)
        {
            if(i.getPhattuid() == phattu.getPhattuid())
            {
                i.setStoken(token);
                tkr.save(i);
                return;
            }
        }
        token tknew = new token();
        tknew.setStoken(token);
        tknew.setPhattu(phattu);
        tkr.save(tknew);
    }



    public String getThongtinGmail (String gmail)
    {
        return gmail;
    }
    public String getThongtinToken(String token)
    {
        return token;
    }
    private String createToken(Map<String, Object> claims, String gmail) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(gmail)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24*2))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
//    setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
}
