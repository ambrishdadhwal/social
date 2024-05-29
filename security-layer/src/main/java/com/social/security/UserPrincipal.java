package com.social.security;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.social.domain.Profile;

public class UserPrincipal implements UserDetails
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String userName;

	@JsonIgnore
	private String email;

	@JsonIgnore
	private String password;

	private Set<String> authGroups;

	public UserPrincipal(Long id, String email, String userName, String password, Set<String> authGroups)
	{
		this.id = id;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.authGroups = authGroups;
	}

	public static UserPrincipal create(Profile user, Set<String> authGroups)
	{

		return new UserPrincipal(user.getId(), user.getEmail(), user.getEmail(), user.getPassword(), authGroups);
	}

	public Long getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public String getEmail()
	{
		return email;
	}

	@Override
	public String getUsername()
	{
		return userName;
	}

	@Override
	public String getPassword()
	{
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		if (null == authGroups)
		{
			return Collections.emptySet();
		}

		Set<SimpleGrantedAuthority> authorities = new HashSet<>();

		authGroups.forEach(n -> {
			authorities.add(new SimpleGrantedAuthority(n));
		});

		return authorities;
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		return true;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserPrincipal that = (UserPrincipal)o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode()
	{

		return Objects.hash(id);
	}
}
