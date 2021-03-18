package br.com.alura.mvc.mudi.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author danilo
 *
 */
@Entity
@Table(name = "segredo")
public class Segredo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String breveDescricao;
	private String tipo;
	private String descricao;
	private Date dataSecredo;
	private String validade;
	private Double codidentifica;
	
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataSecredo() {
		return dataSecredo;
	}

	public void setDataSecredo(Date data) {
		this.dataSecredo = data;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

	public Double getCodidentifica() {
		return codidentifica;
	}

	public void setCodidentifica(Double codidentifica) {
		this.codidentifica = codidentifica;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBreveDescricao() {
		return breveDescricao;
	}

	public void setBreveDescricao(String breveDescricao) {
		this.breveDescricao = breveDescricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



}
