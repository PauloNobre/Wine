package com.algaworks.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.projeto.model.Vinho;
import com.algaworks.projeto.repository.VinhoRepository;
import com.algaworks.projeto.storage.FotoStorage;

@Service
public class VinhoService {

	@Autowired
	VinhoRepository vinhoRepository;
	
	@Autowired
	private FotoStorage fotoStorage;
	
	public void salvar(Vinho vinho) {
		this.vinhoRepository.save(vinho);
	}
	public String adicionarFoto(Vinho vinho, MultipartFile file) {
		String nomeFoto = fotoStorage.salvar(file);
		
		vinho.setFoto(nomeFoto);
		vinhoRepository.save(vinho);
		
		return fotoStorage.getUrl(nomeFoto);
	}
}
