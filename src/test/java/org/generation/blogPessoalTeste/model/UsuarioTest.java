package org.generation.blogPessoalTeste.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UsuarioTest {

	public Usuario usuario;

	@Autowired
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validador = factory.getValidator();

	@BeforeEach
	public void start() {
		usuario = new Usuario("AmandaBoaz", "134652","amanguinha","amandaboaz@email.com");
	}

	@Test
	void testValidaAtributosNaoRetornaErro() {
		Set<ConstraintViolation<Usuario>> validacao = validador.validate(usuario);
		assertTrue(validacao.isEmpty());
	}

	@Test
	void testValidaAtributosRetornaErro() {
		Usuario usuarioErro = new Usuario();
		usuarioErro.setNome("Guilherme Boaz");
		Set<ConstraintViolation<Usuario>> validacao = validador.validate(usuarioErro);
		assertFalse(validacao.isEmpty());
	}

}
