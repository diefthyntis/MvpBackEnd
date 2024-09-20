package com.diefthyntis.MinimumViableProduct.security;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.diefthyntis.MinimumViableProduct.model.Speaker;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Internaut implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String emailaddress;
	
	private String pseudonym;
	
	

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	
	private static final List<String> roles = List.of("ROLE_USER");
	// les tableaux en JAVA ont une taille fixe, contrairement aux listes

	public Internaut(Integer id, String pseudonym,String emailaddress, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.setPseudonym(pseudonym);
		this.setEmailaddress(emailaddress);
		this.password = password;
		this.authorities = authorities;
	}

	public static Internaut build(Speaker speaker) {
		List<GrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role))
				.collect(Collectors.toList());

		return new Internaut(speaker.getId(), speaker.getPseudonym(),speaker.getEmailaddress(), speaker.getPassword(),
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String getPassword() {
		return password;
	}




	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Internaut user = (Internaut) o;
		return Objects.equals(id, user.id);
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return emailaddress;
	}



	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getPseudonym() {
		return pseudonym;
	}

	public void setPseudonym(String pseudonym) {
		this.pseudonym = pseudonym;
	}


}
