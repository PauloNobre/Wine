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

@RestController
@RequestMapping("/fotos")
public class FotosController {
	
	@Autowired
	private VinhoService vinhoService;

	@RequestMapping(value="/{codigo}", method = RequestMethod.POST)
	public Foto upload(@PathVariable("codigo") Vinho vinho,
			@RequestParam("files[]") MultipartFile[] files) {
		
		vinhoService.adicionarFoto(vinho, files[0].getOriginalFilename());
		return new Foto(files[0].getOriginalFilename());
	}
}
