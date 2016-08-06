package com.algaworks.projeto.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.algaworks.projeto.model.TipoVinho;
import com.algaworks.projeto.model.Vinho;
import com.algaworks.projeto.repository.VinhoRepository;
import com.algaworks.projeto.service.VinhoService;

@Controller
@RequestMapping("/vinhos")
public class VinhosController {

	@Autowired
	private VinhoRepository vinhoRepository;
	
	@Autowired
	private VinhoService vinhoService;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Vinho vinho) {
		ModelAndView mv = new ModelAndView("/vinho/CadastroVinho");
		mv.addObject("tipos", TipoVinho.values());
		return mv;
	}
	
	@RequestMapping
	public ModelAndView pesquisa() {
		ModelAndView mv = new ModelAndView("/vinho/ListagemVinhos");
		mv.addObject("vinhos", vinhoRepository.findAll());
		return mv;
	}
	
	@RequestMapping(value="/novo", method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Vinho vinho, BindingResult result) {
		
		if(result.hasErrors()) {
			return novo(vinho);
		}
		
		vinhoService.salvar(vinho);
		return new ModelAndView("redirect:/vinhos/novo");
	}
	
}
