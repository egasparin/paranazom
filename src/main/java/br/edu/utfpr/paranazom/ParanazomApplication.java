package br.edu.utfpr.paranazom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer

public class ParanazomApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParanazomApplication.class, args);
	}

}
