package org.generation.blogPessoalTeste.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImplements service;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("admin"))
				.authorities("ROLE_ADMIN");

		auth.userDetailsService(service);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		//.antMatchers("/**").permitAll()
		.antMatchers("/api/v1/usuario/salvar").permitAll()
		.antMatchers("/api/v1/usuario/credenciais").permitAll().
		anyRequest().authenticated()
		.and().httpBasic()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().cors().and()
		.csrf().disable();

	}

}
