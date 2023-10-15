package com.envify.back.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.envify.back.exception.EnvifyException;

import io.jsonwebtoken.Claims;

/**
 * @author semfa
 *
 */
@Configuration
public class AuthenticationService {
	
	private static JWTUtil jwtUtil;
	
	public AuthenticationService(JWTUtil jwtUtil) {
		AuthenticationService.jwtUtil = jwtUtil;
	}
	
	public static Authentication getAuthentication(HttpServletRequest request) throws EnvifyException {
    	
    	String accessToken = jwtUtil.resolveToken(request);
    	
    	//TODO get api key values from conf
        String apiKey = request.getHeader("ENVIFY-API-Key");
        if (apiKey == null || !apiKey.equals("R1rQpb6ZZQY2ViNIaC6X--") 
        		|| !jwtUtil.validateToken(accessToken)) {
        	
            throw new BadCredentialsException("Invalid API Key");
        }
        
        List<GrantedAuthority> authorities = new ArrayList<>();
    	authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return new ApiKeyAuthentication(apiKey, authorities);
    }
	
}
