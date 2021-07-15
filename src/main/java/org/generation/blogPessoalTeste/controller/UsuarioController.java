package org.generation.blogPessoalTeste.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoalTeste.model.Usuario;
import org.generation.blogPessoalTeste.model.dtos.UsuarioDTO;
import org.generation.blogPessoalTeste.model.dtos.UsuarioLoginDTO;
import org.generation.blogPessoalTeste.repository.UsuarioRepository;
import org.generation.blogPessoalTeste.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

	
	private @Autowired UsuarioService service;
	
	private @Autowired UsuarioRepository repositoryU;

	
	@GetMapping("/getAll") // Método para pegar tudo
	public ResponseEntity<List<Usuario>> findAll() {

		return ResponseEntity.ok(repositoryU.findAll());
	}

	

	@PostMapping ("/salvar")
	public ResponseEntity<Object> salvar(@Valid @RequestBody Usuario novoUsuario) {

		Optional<Object> cadastrarUsuario = service.cadastrarUsuario(novoUsuario);

		if (cadastrarUsuario.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existente!");

		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado.");

		}

	}

	@PostMapping("/credenciais")
	public ResponseEntity<?> autorizar(@Valid @RequestBody UsuarioLoginDTO dadosParaLogar){
		return service.pegarCredenciais(dadosParaLogar)
				.map(usuarioCredenciado -> ResponseEntity.ok(usuarioCredenciado))
				.orElse(ResponseEntity.badRequest().build());
		
	}
	
	@PutMapping("/{id}/atualizar")
	public ResponseEntity<Usuario> atualizarUsuario(@Valid @PathVariable(value = "id") Long idUsuario,
			@Valid @RequestBody UsuarioDTO usuarioParaAtualizar) {

		return service.atualizarUsuario(idUsuario, usuarioParaAtualizar)
				.map(usuarioAtualizado -> ResponseEntity.ok().body(usuarioAtualizado))
				.orElse(ResponseEntity.badRequest().build());

	}



	
}
