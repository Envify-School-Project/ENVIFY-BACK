package com.envify.back.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;


/**
 * @author semfa
 *
 */
public class ApiKeyAuthentication extends AbstractAuthenticationToken{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String apiKey;

    public ApiKeyAuthentication(String apiKey, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.apiKey = apiKey;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return apiKey;
    }
}
