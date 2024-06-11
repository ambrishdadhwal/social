package com.social.security.jwt;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
// @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestContext
{

	private Long userId;
	private String userName;
	private String email;
	private String token;
	private boolean isactive;

}
