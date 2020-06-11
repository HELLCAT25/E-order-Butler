package eOrderButler.jwt;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import eOrderButler.service.CustomUserDetails;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {

	private static final long jwtExpirationMs = 86400000;
	private static final String jwtKey = "eOrderButler";

	public String generateJwtToken(Authentication auth) {
		
		CustomUserDetails userPrincipal = (CustomUserDetails) auth.getPrincipal();
		
		return Jwts.builder()
				.setSubject(userPrincipal.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtKey)
				.compact();
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtKey).parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtKey).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			e.printStackTrace();
		} catch (MalformedJwtException e) {
			e.printStackTrace();
		} catch (ExpiredJwtException e) {
			e.printStackTrace();
		} catch (UnsupportedJwtException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return false;
	}
}
