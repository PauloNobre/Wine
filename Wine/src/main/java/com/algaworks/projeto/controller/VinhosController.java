package com.algaworks.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.algaworks.projeto.repository.VinhoRepository;

@Controller
@RequestMapping("/vinhos")
public class VinhosController {

	@Autowired
	private VinhoRepository vinhoRepository;
	
	@RequestMapping("/novo")
	public String novo() {
		return "/vinho/CadastroVinho";
	}
	
	@RequestMapping
	public ModelAndView pesquisa() {
		ModelAndView mv = new ModelAndView("/vinho/ListagemVinhos");
		mv.addObject("vinhos", vinhoRepository.findAll());
		return mv;
	}
	
}
