package com.mercado.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.mercado.model.Mercado;


public interface MercadoRepository extends JpaRepository<Mercado, Long>{

	
}
