package com.social.filters;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

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