package com.algoworks.Ncobrancao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algoworks.Ncobrancao.model.Titulo;

public interface Titulos extends JpaRepository<Titulo, Long> {
	
	public List<Titulo> findByDescricaoContaining(String descricao);

}
