package org.generation.blogPessoalTeste.controller;

import java.util.List;

import org.generation.blogPessoalTeste.model.Tema;
import org.generation.blogPessoalTeste.repository.TemaRepository;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/tema")
public class TemaController {

	@Autowired
	private TemaRepository repository;

	@GetMapping
	public ResponseEntity<List<Tema>> findAllTema() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tema> findByIdTema(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Tema>> findByDescricaoPostagem(@PathVariable String nome) {
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(nome));
	}

	@PostMapping
	public ResponseEntity<Tema> postTema(@RequestBody Tema tema) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
	}

	@PutMapping
	public ResponseEntity<Tema> putTema(@RequestBody Tema tema) {
		return ResponseEntity.ok(repository.save(tema));
	}

	@DeleteMapping("/{id}")
	public void deleteTema(@PathVariable long id) {
		repository.deleteById(id);
	}
}
