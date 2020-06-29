package com.mercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.mercado.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	@Query("select u from Pessoa u where u.login = ?1")
	Pessoa findByLogin(String login);

	@Query(value = "SELECT constraint_name from information_schema.constraint_column_usage where table_name = 'pessoas_role' and column_name = 'role_id' and constraint_name <> 'unique_role_user'", nativeQuery = true)
	String consultaConstraintRole();

	@Transactional
	@Modifying
	@Query(value = "insert into pessoas_role (pessoa_id, role_id) values (?1, (select id from where nome_role = 'ROLE_USER'));", nativeQuery = true)
	void insereAcessoRolePadrao(Long id);
}
