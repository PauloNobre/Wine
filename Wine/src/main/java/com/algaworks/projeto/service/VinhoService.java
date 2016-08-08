package com.algaworks.projeto.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.projeto.model.Vinho;
import com.algaworks.projeto.repository.VinhoRepository;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class VinhoService {

	@Autowired
	VinhoRepository vinhoRepository;
	
	@Autowired
	private AmazonS3 s3Client;
	
	public void salvar(Vinho vinho) {
		this.vinhoRepository.save(vinho);
	}
	public String adicionarFoto(Vinho vinho, MultipartFile file) {
		String nomeFoto = file.getOriginalFilename();
		vinho.setFoto(nomeFoto);
		vinhoRepository.save(vinho);
		
		try {
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(file.getContentType());
			metadata.setContentLength(file.getSize());
			s3Client.putObject("wine", nomeFoto, file.getInputStream(), metadata);
		} catch (AmazonServiceException | IOException e) {
			throw new RuntimeException("Erro salvando arquivo S3", e);
		}
		
		return "http://localhost:9444/s3/wine/" + nomeFoto + "?noAuth=true";
	}
}
