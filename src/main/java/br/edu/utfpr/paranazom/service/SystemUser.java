package br.edu.utfpr.paranazom.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class SystemUser extends User {

	private static final long serialVersionUID = 1L;
	
	private br.edu.utfpr.paranazom.model.User usuario;

	public SystemUser(br.edu.utfpr.paranazom.model.User usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getPassword(), authorities);
		this.usuario = usuario;
	}

	public br.edu.utfpr.paranazom.model.User getUsuario() {
		return usuario;
	}

}