package br.com.alura.mvc.mudi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alura.mvc.mudi.model.DBFile;

	@Repository
	public interface DBFileRepository extends JpaRepository<DBFile, String> {
		
		@Query("select COUNT(*) from DBFile where user_username = :username")
		long countM(@Param("username")String username);
}
