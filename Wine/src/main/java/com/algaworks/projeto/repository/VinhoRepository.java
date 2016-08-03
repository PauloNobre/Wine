package com.algaworks.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.projeto.model.Vinho;

public interface VinhoRepository extends JpaRepository<Vinho, Long>{

}
