package com.social.security;

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
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfigNew
{

	@Autowired
	private ProfileAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Autowired
	ProfileAuthentiationProvider authProvider;

	@Autowired
	ProfileFilter profileFilter;

	private final String[] PERMITTED_URLS =
	{	"/resources/**",
		"/login",
		"/about", "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**","/h2-console/**"};

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		http.cors().and().csrf(csrf -> csrf.disable())
				.headers().disable()
		.authorizeHttpRequests(auth -> auth.requestMatchers("/h2-console**").permitAll()
				.requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
				.requestMatchers("/v3/api-docs/**").permitAll()
				.requestMatchers("/swagger-ui.html/**").permitAll()
				.requestMatchers("/login.html/**").permitAll()
				.requestMatchers("/ui/**").permitAll()
				.requestMatchers("/swagger-ui/**").permitAll()
				.requestMatchers(HttpMethod.POST,"/user/").permitAll()
				.requestMatchers(HttpMethod.POST,"/gen-ai/search").permitAll()
				.requestMatchers(HttpMethod.POST,"/login/").permitAll()
				.requestMatchers(HttpMethod.GET,"/actuator/**").permitAll()
				.requestMatchers(HttpMethod.GET,"/index/**").permitAll()
				.requestMatchers(HttpMethod.GET,"/register/**").permitAll()
				.anyRequest().authenticated())
			.httpBasic().authenticationEntryPoint(authenticationEntryPoint)
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authenticationProvider(authProvider);
			//.addFilterBefore(fbFilter, UsernamePasswordAuthenticationFilter.class)

		return http.build();
	}

	/*
	 * Ignore swagger & other stuff
	 * */
	/*
	 * @Bean public WebSecurityCustomizer webSecurityCustomizer() { return (web ->
	 * web.ignoring().anyRequest() .requestMatchers("/h2-console/**")
	 * .requestMatchers("/v3/api-docs/**") .requestMatchers("/swagger-ui.html")
	 * .requestMatchers("/swagger-ui/**") .requestMatchers("/h2-console/**")
	 * .requestMatchers(HttpMethod.POST, "/user/**")); }
	 */

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
