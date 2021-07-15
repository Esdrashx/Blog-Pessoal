package org.generation.blogPessoalTeste.security;

import java.util.Optional;

import org.generation.blogPessoalTeste.model.Usuario;
import org.generation.blogPessoalTeste.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplements implements UserDetailsService {

	@Autowired
	private UsuarioRepository repositoryU;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Usuario> usuarioExistente = repositoryU.findByEmail(username);

		if (usuarioExistente.isPresent()) {
			return new UserDetailsImplements(usuarioExistente.get());
		} else {
			throw new UsernameNotFoundException(username + " não existe.");
		}
	}
	
	
}
