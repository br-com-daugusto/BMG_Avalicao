package com.algoworks.Ncobrancao.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algoworks.Ncobrancao.model.StatusTitulo;
import com.algoworks.Ncobrancao.model.Titulo;
import com.algoworks.Ncobrancao.repository.Titulos;
import com.algoworks.Ncobrancao.repository.filter.TituloFilter;

@Service
public class CadastroTituloService {

	@Autowired
	private Titulos titulos;
	
	public void salvar(Titulo titulo) {
		try {
		titulos.save(titulo);
		}catch (DataIntegrityViolationException e) {
			// TODO: handle exception
			throw new IllegalArgumentException("Formato de data invalido");
		}
	}

	public void excluir(Long codigo) {
		titulos.deleteById(codigo);
	}

	public String receber(Long codigo) {
		Titulo titulo = titulos.getOne(codigo);
		titulo.setStatus(StatusTitulo.RECEBIDO);
		titulos.save(titulo);
		
		return StatusTitulo.RECEBIDO.getDescricao();
		
	}
	
	public List<Titulo> filtrar(TituloFilter filtro){		
		String descricao = filtro.getDescricao() == null ? "%" : filtro.getDescricao();
		return titulos.findByDescricaoContaining(descricao);
	}
}
