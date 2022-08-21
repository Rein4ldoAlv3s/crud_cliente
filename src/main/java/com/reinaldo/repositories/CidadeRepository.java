package com.reinaldo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reinaldo.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

}
