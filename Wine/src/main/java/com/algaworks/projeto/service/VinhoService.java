package com.algaworks.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.projeto.model.Vinho;
import com.algaworks.projeto.repository.VinhoRepository;

@Service
public class VinhoService {

	@Autowired
	VinhoRepository vinhoRepository;
	public void salvar(Vinho vinho) {
		this.vinhoRepository.save(vinho);
	}
	public void adicionarFoto(Vinho vinho, String foto) {
		vinho.setFoto(foto);
		vinhoRepository.save(vinho);
	}
}
