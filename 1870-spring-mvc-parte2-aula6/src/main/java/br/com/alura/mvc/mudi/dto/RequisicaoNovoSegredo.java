package br.com.alura.mvc.mudi.dto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import br.com.alura.mvc.mudi.model.Segredo;
import br.com.alura.mvc.mudi.model.User;

public class RequisicaoNovoSegredo {

	@NotBlank
	private String breveDescricao;
	@NotBlank
	private String tipo;

	@Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$")
	@NotBlank
	private String validade;
	@NotBlank
	private String descricao;

	private CommonsMultipartFile file;

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

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



	public CommonsMultipartFile getFile() {
		return file;
	}

	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}

	public Segredo toSegredo() {

		
		Date data = new Date();
		
		Random random = new Random();
		double valor = random.nextDouble() * 10;
		Segredo seg = new Segredo();

		seg.setDescricao(descricao);
		seg.setTipo(tipo);
		seg.setBreveDescricao(breveDescricao);
		seg.setDataSecredo(data);
		seg.setValidade(validade);
		seg.setCodidentifica(valor);
		//seg.setFile(file);

		return seg;
	}

}
