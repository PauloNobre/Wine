package com.algaworks.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.projeto.dto.Foto;
import com.algaworks.projeto.model.Vinho;
import com.algaworks.projeto.service.VinhoService;
import com.algaworks.projeto.storage.FotoReader;

@RestController
@RequestMapping("/fotos")
public class FotosController {
	
	@Autowired
	private VinhoService vinhoService;
	
	@Autowired(required = false)
	private FotoReader fotoReader;

	@RequestMapping(value="/{codigo}", method = RequestMethod.POST)
	public Foto upload(@PathVariable("codigo") Vinho vinho,
			@RequestParam("files[]") MultipartFile[] files) {
		
		String url = vinhoService.adicionarFoto(vinho, files[0]);
		return new Foto(url);
	}
	
	@RequestMapping("/{nome:.*}")
	public byte[] recuperarFoto(@PathVariable String nome) {
		return fotoReader.recuperar(nome);
	}
}
