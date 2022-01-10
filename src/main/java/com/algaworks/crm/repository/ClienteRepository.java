package com.algaworks.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.crm.model.ClienteEntity;

@Repository
public interface ClienteRepository  extends JpaRepository<ClienteEntity, Long>{
	



}
