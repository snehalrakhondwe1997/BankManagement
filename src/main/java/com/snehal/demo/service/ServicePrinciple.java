package com.snehal.demo.service;

import java.util.Collection;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.snehal.demo.Repository.UserRepo;
import com.snehal.demo.entity.User;

public class ServicePrinciple implements UserDetails {
	
	 User user;//only create user refrence then apply constructor

public ServicePrinciple(User user) {
	super();
	this.user = user;
}

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	// TODO Auto-generated method stub
	String s=user.getName();
	String str= s.equals("admin")?"ADMIN":"USER";
	return Collections.singleton(new SimpleGrantedAuthority(str));
}

@Override
public String getPassword() {
	// TODO Auto-generated method stub
	return user.getPassword();
}

@Override
public String getUsername() {
	// TODO Auto-generated method stub
	return user.getUsername();
}

@Override
public boolean isAccountNonExpired() {
	// TODO Auto-generated method stub
	return true;
}

@Override
public boolean isAccountNonLocked() {
	// TODO Auto-generated method stub
	return true;
}

@Override
public boolean isCredentialsNonExpired() {
	// TODO Auto-generated method stub
	return true;
}

@Override
public boolean isEnabled() {
	// TODO Auto-generated method stub
	return true;
}

}
