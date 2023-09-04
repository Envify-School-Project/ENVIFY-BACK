package com.envify.back.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @author semfa
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Value("${conf.envify.host.back.url}")
	private String url;
	
	@Value("${conf.envify.host.front.url}")
	private String frontUrl;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.formLogin().disable()
			.logout().disable()
			.authorizeRequests().antMatchers("/**")
			.authenticated()
			.and()
			.httpBasic().disable()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.addFilterBefore(new ApiKeyAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {

		final CorsConfiguration configuration = new CorsConfiguration();

		configuration.setAllowedOrigins(Arrays.asList(allowedOrigins()));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "PUT", "DELETE"));
		configuration.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Access-Control-Allow-Methods",
				"Access-Control-Allow-Headers",
				"Access-Control-Max-Age", "Access-Control-Request-Headers", "Access-Control-Request-Method",
				"Last-Modified", "ETag"));

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}

	@Bean
	public String[] allowedOrigins() {
		final List<String> allowedOrigins = new ArrayList<>();

		allowedOrigins.add(url);

		if (StringUtils.isNotBlank(frontUrl)) {
			allowedOrigins.add(frontUrl);
		}

		return allowedOrigins.toArray(new String[0]);
	}
}
