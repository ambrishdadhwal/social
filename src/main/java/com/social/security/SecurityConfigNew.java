package com.social.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authorization.AuthorizationEventPublisher;
import org.springframework.security.authorization.SpringAuthorizationEventPublisher;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfigNew
{

	@Autowired
	private SocialAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Autowired
	FBAuthentiationProvider authProvider;

	@Autowired
	FBFilter fbFilter;

	private final String[] PERMITTED_URLS =
	{	"/resources/**",
		"/login",
		"/about"};

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		http.cors().and().csrf().disable()
			.authorizeHttpRequests()
			.antMatchers(PERMITTED_URLS).permitAll()
			.anyRequest().authenticated()
			.and()
			.httpBasic().authenticationEntryPoint(authenticationEntryPoint)
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authenticationProvider(authProvider)
			//.addFilterBefore(fbFilter, UsernamePasswordAuthenticationFilter.class)
			.formLogin();

		return http.build();
	}

	/*
	 * Ignore swagger & other stuff
	 * */
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer()
	{
		return (web -> web.ignoring()
			.antMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**")
			.antMatchers("/h2-console/**")
			.antMatchers(HttpMethod.POST, "/user/**"));
	}

	// custom request matcher
	public class MyCustomRequestMatcher implements RequestMatcher
	{

		@Override
		public boolean matches(HttpServletRequest request)
		{
			request.getRequestURI();
			return false;
		}
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(customUserDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		provider.setAuthoritiesMapper(authoritiesMapper());
		return provider;
	}

	@Bean
	public GrantedAuthoritiesMapper authoritiesMapper()
	{
		SimpleAuthorityMapper mapper = new SimpleAuthorityMapper();
		mapper.setConvertToUpperCase(true);
		mapper.setDefaultAuthority("ADMIN");
		return mapper;
	}

	@Bean
	public AuthenticationEventPublisher authenticationEventPublisher(ApplicationEventPublisher applicationEventPublisher)
	{
		return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
	}

	@Bean
	public AuthorizationEventPublisher authorizationEventPublisher(ApplicationEventPublisher applicationEventPublisher)
	{
		return new SpringAuthorizationEventPublisher(applicationEventPublisher);
	}

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}
