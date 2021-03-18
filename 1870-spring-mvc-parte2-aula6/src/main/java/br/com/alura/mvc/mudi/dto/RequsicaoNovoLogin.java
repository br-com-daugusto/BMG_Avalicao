package br.com.alura.mvc.mudi.dto;

import java.util.Base64;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.model.User;

public class RequsicaoNovoLogin {

	@NotBlank
	@Email(message = "Email incorreto")
	private String username;
	
	@NotBlank
	
	private String password;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String sobreNome;

	private Boolean tipo;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public String getUsername() {
		return username;
	}
	

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public Boolean getTipo() {
		return tipo;
	}

	public void setTipo(Boolean tipo) {
		this.tipo = tipo;
	}

	public User toUsuario() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		//String senha = Base64.getEncoder().encodeToString(password.getBytes());
		String senha = encoder.encode(password);
		User user = new User();
		user.setUsername(username);
		user.setPassword(senha);
		user.setNome(nome);
		user.setSobreNome(sobreNome);
		user.setEnabled(true);
		user.setTipo(tipo);

		return user;
	}
		
}
