package org.generation.blogPessoalTeste.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.blogPessoalTeste.model.Usuario;
import org.generation.blogPessoalTeste.model.dtos.UsuarioDTO;
import org.generation.blogPessoalTeste.model.dtos.UsuarioLoginDTO;
import org.generation.blogPessoalTeste.repository.TemaRepository;
import org.generation.blogPessoalTeste.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repositoryU;
	@Autowired
	private TemaRepository repositoryT;

	/**
	 * Método para cadastrar um novo usuário
	 * 
	 * @param novoUsuario do tipo Usuario
	 * @return Optional com Usuario ou Optional Empty
	 * @since 1.0
	 * @author Bruno
	 * 
	 */
	public Optional<Object> cadastrarUsuario(Usuario novoUsuario) {
		return repositoryU.findByEmail(novoUsuario.getEmail()).map(usuarioExistente -> {
			return Optional.empty();
		}).orElseGet(() -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaCriptografada = encoder.encode(novoUsuario.getSenha());
			novoUsuario.setSenha(senhaCriptografada);

			return Optional.ofNullable(repositoryU.save(novoUsuario));
		});

	}

	/**
	 * Método para atualizar um usuário
	 * 
	 * @param usuarioParaAtualizar, tipo UsuarioDTO para trasnferencia de nome
	 * 								senha
	 * @return Optional com entidade de Usuario e Optional vazio
	 * @since 1.0
	 * @author Bruno
	 * 
	 */
	public Optional<Usuario> atualizarUsuario(Long idUsuario, UsuarioDTO usuarioParaAtualizar) {
		return repositoryU.findById(idUsuario).map(usuarioExistente -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaCriptografada = encoder.encode(usuarioParaAtualizar.getSenha());
			usuarioParaAtualizar.setNome(usuarioParaAtualizar.getNome());
			usuarioParaAtualizar.setSenha(senhaCriptografada);
			return Optional.ofNullable(repositoryU.save(usuarioExistente));

		}).orElseGet(() -> {

			return Optional.empty();
		});

	}

	public Optional<?> pegarCredenciais(UsuarioLoginDTO dadosParaLogar) {
		return repositoryU.findByEmail(dadosParaLogar.getEmail())
				.map(usuarioExistente -> {
					BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
					if (encoder.matches(dadosParaLogar.getSenha(), usuarioExistente.getSenha())) {
						String estruturaBasic = dadosParaLogar.getEmail() + ":"+ dadosParaLogar.getSenha();
						byte[] autorizacaoBase64 = Base64.encodeBase64(estruturaBasic.getBytes(Charset.forName("US-ASCII")));
						String autorizacaoHeader = "Basic "+ new String(autorizacaoBase64);
						dadosParaLogar.setToken(autorizacaoHeader);
						dadosParaLogar.setIdUsuario(usuarioExistente.getIdUsuario());
						dadosParaLogar.setNome(usuarioExistente.getNome());
						dadosParaLogar.setSenha(usuarioExistente.getSenha());
						return Optional.ofNullable(dadosParaLogar);
					} else {
						return Optional.empty();
					}
					
				}).orElse(Optional.empty());
	}
	
}
