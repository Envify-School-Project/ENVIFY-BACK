package com.envify.back.security;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.envify.back.config.Config;
import com.envify.back.entity.UserEntity;
import com.envify.back.exception.EnvifyException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * @author semfa
 *
 */
@Component
public class JWTUtil {

	private static final String TOKEN_HEADER = "Authorization";
	private static final String TOKEN_PREFIX = "Bearer ";

	@Autowired
	private Config config;
	private Key key;

	@PostConstruct
	public void init() {
		key = Keys.hmacShaKeyFor(config.getTokenSecretKey().getBytes());
	}

	public Claims getAllClaimsFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
	}

	public String extractUsername(String token) throws EnvifyException {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) throws EnvifyException {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws EnvifyException {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) throws EnvifyException {
		Claims claims;
		try {
			claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
		} catch (Exception e) {
			throw new EnvifyException("Echec de l'authentification avec le jwt");
		}
		return claims;
	}

	private Boolean isTokenExpired(String token) throws EnvifyException {
		return extractExpiration(token).before(new Date());
	}

	public String resolveToken(HttpServletRequest request) {

		String bearerToken = request.getHeader(TOKEN_HEADER);
		if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
			return bearerToken.substring(TOKEN_PREFIX.length());
		}
		return null;
	}

	public String generateToken(UserEntity user, String expirationDate) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		Map<String, Object> claims = new HashMap<>();
		claims.put("role", authorities);
		claims.put("id", user.getId());
		claims.put("email", user.getEmail());
		return createToken(claims, user.getEmail(), user.getId(),expirationDate);
	}

	private String createToken(Map<String, Object> claims, String email, Integer id, String expirationTime) {
		final Long expirationTimeInMs = getExpirationTime(expirationTime);

		final Date createdDate = new Date();
		final Date expirationDate = new Date(createdDate.getTime() + expirationTimeInMs);

		return Jwts.builder().setClaims(claims).setIssuedAt(createdDate)
				.setExpiration(expirationTimeInMs == 0 ? null : expirationDate).signWith(key).compact();
	}

	public Long getExpirationTime(String expirationTime) {
		Long expirationTimeInMs = Long.parseLong(config.getTokenExpirationTimeInMs());
		if (StringUtils.isNoneBlank(expirationTime)) {
			expirationTimeInMs = defineExpirationTime(expirationTime);
		}
		return expirationTimeInMs;
	}

	private Long defineExpirationTime(String expirationTime) {
		Long expirationTimeInMs;
		if ("-1".equals(expirationTime)) {
			expirationTimeInMs = 0L;
		} else {
			expirationTimeInMs = (long) (Double.parseDouble(expirationTime) * 60 * 60 * 1000);
		}
		return expirationTimeInMs;
	}

	public Boolean validateToken(String token, UserEntity userEntity) throws EnvifyException {
		final String username = extractUsername(token);
		return (username.equals(userEntity.getEmail()) && !isTokenExpired(token));
	}

	public Boolean validateToken(String token) throws EnvifyException {
		return !isTokenExpired(token);
	}

}
