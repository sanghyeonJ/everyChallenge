package com.everyChallenge.config;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.everyChallenge.domain.User;

import lombok.Getter;

@Getter
public class CustomUserDetails implements UserDetails {

	private final Long id;
    private final String loginId;
	private final String password;
	private final String userName;
	private final Boolean isDeleted;
	private final Collection<? extends GrantedAuthority> authorities;
	
	public CustomUserDetails(User user) {
		this.id = user.getId();
		this.loginId = user.getLoginId();
		this.password = user.getPassword();
		this.userName = user.getUserName();
		this.isDeleted = user.getIsDeleted();
		this.authorities = List.of(new SimpleGrantedAuthority(user.getRole()));
	}
	
	public boolean isAdmin() {
	    return authorities != null && authorities.stream()
	        .anyMatch(a -> "ROLE_ADMIN".equals(a.getAuthority()) || "ADMIN".equals(a.getAuthority()));
	}
	
	@Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return isDeleted == null || !isDeleted; }
    @Override public String getUsername() { return loginId; }
	
}
