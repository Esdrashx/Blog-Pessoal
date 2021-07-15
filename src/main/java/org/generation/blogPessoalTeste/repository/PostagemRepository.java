package org.generation.blogPessoalTeste.repository;

import java.util.List;

import org.generation.blogPessoalTeste.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long>{

	//public List<Postagem> findAllByTituloContainingIgnoreCase (String titulo);

	public List<Postagem> getByTituloContainingIgnoreCase (String titulo);

	//public List<Postagem> getById (long id);

	
	// public List<Postagem> encontraById(int id);

	//public List<Postagem> batata(long id);


	//public List<Postagem> getById(long id);
	
	
	
	
}
