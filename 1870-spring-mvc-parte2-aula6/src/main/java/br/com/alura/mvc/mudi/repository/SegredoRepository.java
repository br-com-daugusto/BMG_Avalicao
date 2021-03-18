package br.com.alura.mvc.mudi.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.com.alura.mvc.mudi.model.Segredo;
import br.com.alura.mvc.mudi.model.User;

public interface SegredoRepository extends JpaRepository<Segredo, Long> {

	//@Query("select count(*) from SEGREDO")
	//long count(@Param("username")String username);
	

	@Query("select COUNT(*) from Segredo where user_username = :username")
	long count(@Param("username")String username);
	
	



}
