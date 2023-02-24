package com.social.filters;

import java.io.IOException;
import java.util.Optional;

import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class LoggingFilter extends GenericFilterBean
{

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
	{
		final HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
		final String url = httpServletRequest.getRequestURL().toString();
		final String queryString = Optional.ofNullable(httpServletRequest.getQueryString()).map(value -> "?" + value).orElse("");

		System.out.println(String.format("applying LoggingFilter for URI: %s%s", url, queryString));

		filterChain.doFilter(servletRequest, servletResponse);
	}
}