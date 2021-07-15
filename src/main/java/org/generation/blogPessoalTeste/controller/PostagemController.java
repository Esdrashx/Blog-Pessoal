package org.generation.blogPessoalTeste.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.generation.blogPessoalTeste.model.Postagem;
import org.generation.blogPessoalTeste.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/postagem")
@CrossOrigin("*")
public class PostagemController {

	@Autowired
	private PostagemRepository repository;

	// METODOS-TESTE QUE IMPLEMENTEI POR BUSCA

	@GetMapping("/all")
	public List<Postagem> findAll() {
		return repository.findAll();
	}

	@GetMapping("/Id")
	public Optional<Postagem> findById() {
		return repository.findById((long) 3);
	}

	@GetMapping("/titulo")
	public List<Postagem> getByTitulo() {
		return repository.getByTituloContainingIgnoreCase("Cores");
	}

	// METODOS PELO PROFESSOR NO VIDEO

	@GetMapping("/GetAll") // método findAll
	public ResponseEntity<List<Postagem>> findAllPostagem() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/GetAllById/{id}") // método findByID
	public ResponseEntity<Postagem> findByIdPostagem(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/GetByTitulo/{titulo}") // método getByTitulo
	public ResponseEntity<List<Postagem>> findByDescricaoTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.getByTituloContainingIgnoreCase(titulo));
	}

	@PostMapping // método POST
	public ResponseEntity<Postagem> postPostagem(@RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}

	@PutMapping // método PUT
	public ResponseEntity<Postagem> putPostagem(@RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}

	@DeleteMapping("/{id}") // método DELETE
	public void deletePostagem(@PathVariable long id) {
		repository.deleteById(id);
	}

	// MÉTODOS-TESTE TENTANDO USAR GETBYID

	@GetMapping("/getByIdteste")
	public Postagem findByIdteste() {
		return repository.getById((long) 3);

	}

	@GetMapping("/getById/{id_usuario}")
	public ResponseEntity<Postagem> batata(@PathVariable(value = "id_usuario") Long id) throws EntityNotFoundException {
		Optional<Postagem> objeto = (repository.findById(id)); // Optional.ofNullable(repository.getById(id));
																// --anterior que nao funcionava
		if (objeto.isPresent()) {
			Postagem nova = objeto.get();
			System.out.println(objeto.toString());
			return ResponseEntity.status(200).body(nova);
		} else {
			return ResponseEntity.status(400).build();
		}
	}

	/*
	 * @GetMapping ("/Id") public Postagem encontraPorId(){ return
	 * repository.getById((long) 3); }
	 */

	/*
	 * @GetMapping ("/Id") public List<Postagem> findAllById(){ return
	 * repository.findAllById(long id); }
	 */

	/*
	 * @GetMapping ("/teste") public List<Postagem> getById(){ return
	 * repository.getById(1); }
	 */

	/*
	 * public void findAll(PostagemRepository repository) { this.repository =
	 * repository; }
	 * 
	 * 
	 */

}
