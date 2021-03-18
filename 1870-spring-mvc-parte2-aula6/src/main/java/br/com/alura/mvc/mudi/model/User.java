package br.com.alura.mvc.mudi.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {

	@Id
	private String username;
	private String password;
	private String nome;
	private String sobreNome;
	private Boolean enabled;
	private Boolean tipo;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy =  "user", fetch = FetchType.LAZY)
	private List<Segredo> segredo;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
	private List<DBFile> dbFiles;
	
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
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Boolean getTipo() {
		return tipo;
	}
	public void setTipo(Boolean tipo) {
		this.tipo = tipo;
	}
	public List<Segredo> getSegredo() {
		return segredo;
	}
	public void setSegredo(List<Segredo> segredo) {
		this.segredo = segredo;
	}
	public List<DBFile> getDbFiles() {
		return dbFiles;
	}
	public void setDbFiles(List<DBFile> dbFiles) {
		this.dbFiles = dbFiles;
	}
		
	
	
	
}
