package com.assessment.userserver.user;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class TokenService {

	@Value("${jwt.secret}")
	private String secret;

	public String generateToken(Long userId) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + 86400000); // Token expires in 24 hours

		return Jwts.builder()
				.setSubject(userId.toString())
				.setIssuedAt(now)
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}

	public boolean validateToken(Long userId, String token) {
		try {
			String extractedUserId = Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody()
					.getSubject();

			return userId.toString().equals(extractedUserId);
		} catch (Exception e) {
			return false;
		}
	}
}
