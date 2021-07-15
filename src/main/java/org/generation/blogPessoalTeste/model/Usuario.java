package org.generation.blogPessoalTeste.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;

	@NotEmpty
	@Size(min = 2, max = 100)
	private String nome;

	@NotEmpty
	private String senha;

	@NotEmpty
	@Size(min = 5, max = 100)
	private String usuario;

	@NotEmpty
	@Email
	@Size(min = 5, max = 100)
	private String email;

	public Usuario(@NotEmpty @Size(min = 2, max = 100) String nome, @NotEmpty String senha,
			@NotEmpty @Size(min = 5, max = 100) String usuario,
			@NotEmpty @Email @Size(min = 5, max = 100) String email) {
		super();
		this.nome = nome;
		this.senha = senha;
		this.usuario = usuario;
		this.email = email;
	}

	public Usuario() {
		super();
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setId(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
