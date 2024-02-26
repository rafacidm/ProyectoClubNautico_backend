package com.sistema.club.services;

import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.lang.Function;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService{

	@Value("${jwt.secret.key}")
	private String secretKey;
	
	public String generateToken(UserDetails userDetails) {
		return Jwts.builder()
				.claim("sub", userDetails.getUsername())
				.claim("iat", new Date(System.currentTimeMillis()))
				.claim("exp", new Date(System.currentTimeMillis()+100000*60*24))
				.signWith(getSigninKey())
				.compact();
	}

	private SecretKey getSigninKey() {
		byte[] key = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(key);
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
		final Claims claims = extractAllClaims(token);
		return claimsResolvers.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().verifyWith(getSigninKey()).build().parseSignedClaims(token).getPayload();
	}
	
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return(username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		return extractClaim(token, Claims::getExpiration).before(new Date());
	}

	@Override
	public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		return Jwts.builder()
				.claims(extraClaims)
				.claim("sub", userDetails.getUsername())
				.claim("iat", new Date(System.currentTimeMillis()))
				.claim("exp", new Date(System.currentTimeMillis()+1000*60*24))
				.signWith(getSigninKey())
				.compact();
	}
}
