package br.edu.utfpr.paranazom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


@Profile("oauth-security")
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService; 

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
				.withClient("angular")
				.secret("$2a$10$v.Ks3TsBe4ZUfkCdMG/vJepbIUvKbRrJ9gUPUyeJCDW4a4Qjp268y") // @umaSenha0 : gerada pelo CreateEncrypted
				.scopes("read", "write")
				.authorizedGrantTypes("password", "refresh_token")
				.accessTokenValiditySeconds(15) // nao é ideal que fique muito tempo, vamos reduzir para alguns segundos e atualizar constantemente com refresh token
				.refreshTokenValiditySeconds(3600 * 10) // o token que estara no cookie terá um tempo de vida de 10 horas
			.and()
				.withClient("mobile")
				.secret("$2a$10$UqQCCkQ3Z8w20eyBt0a1T.iV6hf3YUKhugfQ.qCdta38VxxUqvGvW") // m0b1le : gerada pelo CreateEncrypted
				.scopes("read") // acesso via mobile somente tera escopo de leitura
				.authorizedGrantTypes("password", "refresh_token")
				.accessTokenValiditySeconds(15) // nao é ideal que fique muito tempo, vamos reduzir para alguns segundos e atualizar constantemente com refresh token
				.refreshTokenValiditySeconds(3600 * 10) // o token que estara no cookie terá um tempo de vida de 10 horas
			.and()
				.withClient("react")
				.secret("$2a$10$v.Ks3TsBe4ZUfkCdMG/vJepbIUvKbRrJ9gUPUyeJCDW4a4Qjp268y") // : gerada pelo CreateEncrypted
				.scopes("read", "write") //escopo
				.authorizedGrantTypes("password", "refresh_token")
				.accessTokenValiditySeconds(15) // nao é ideal que fique muito tempo, vamos reduzir para alguns segundos e atualizar constantemente com refresh token
				.refreshTokenValiditySeconds(3600 * 10);
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		endpoints
		.tokenStore(tokenStore())
		.accessTokenConverter(accessTokenConverter())
		.reuseRefreshTokens(false)
		.userDetailsService(userDetailsService)
		.authenticationManager(authenticationManager);
	}
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
		accessTokenConverter.setSigningKey("tds");
		return accessTokenConverter;
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
}