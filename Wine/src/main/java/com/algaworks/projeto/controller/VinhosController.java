package com.algaworks.projeto.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public ModelAndView salvar(@Valid Vinho vinho, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return novo(vinho);
		}
		
		vinhoService.salvar(vinho);
		attributes.addFlashAttribute("mensagem", "Vinho cadastrado com sucesso!");
		return new ModelAndView("redirect:/vinhos/novo");
	}
	
	@RequestMapping("/{codigo}")
	public ModelAndView detalhes(@PathVariable("codigo") Vinho vinho) {
		ModelAndView mv= new ModelAndView("/vinho/VisualizarVinho");
		mv.addObject("vinho", vinho);
		
		if(vinho.temFoto()) {
			vinho.setUrl("http://localhost:9444/s3/wine/" + vinho.getFoto() + "?noAuth=true");
		}
		return mv;
	}
}
