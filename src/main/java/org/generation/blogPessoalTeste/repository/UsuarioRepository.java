package org.generation.blogPessoalTeste.repository;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoalTeste.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	public Optional<Usuario> findByUsuario (String usuario);
	
	Optional<Usuario> findByEmail (String email);
	
	List<Usuario> findAllByEmailContaining(String email);
}
