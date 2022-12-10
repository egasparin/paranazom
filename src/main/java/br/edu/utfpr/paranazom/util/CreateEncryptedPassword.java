package br.edu.utfpr.paranazom.util;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CreateEncryptedPassword{
	
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("vendedor")); // Senha que deseja que seja gerada
	}
}