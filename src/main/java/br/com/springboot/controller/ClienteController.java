package br.com.springboot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.springboot.bo.ClienteBO;
import br.com.springboot.model.Cliente;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteBO clienteBO;

	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo(ModelMap model) {
		model.addAttribute("cliente", new Cliente());
		return new ModelAndView("/cliente/formulario", model);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String salva(@Valid @ModelAttribute Cliente cliente, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			System.out.println(result);
			return "/cliente/formulario";
		}
		if (cliente.getId() == null) {
			clienteBO.insere(cliente);
			attr.addFlashAttribute("feedback", "Cliente cadastrado com sucesso");
		} else {
			clienteBO.atualiza(cliente);
			attr.addFlashAttribute("feedback", "Cliente atualizado com sucesso");
		}
		return "redirect:/clientes";
	}

	@RequestMapping(value = "/inativa/{id}", method = RequestMethod.GET)
	public String inativa(@PathVariable("id") Long id, RedirectAttributes attr) {
		System.out.println(id);
		try {
			Cliente cliente = clienteBO.pesquisaPeloID(id);
			clienteBO.inativa(cliente);
			attr.addFlashAttribute("feedback", "Cliente inativado com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/clientes";
	}

	@RequestMapping(value = "/ativa/{id}", method = RequestMethod.GET)
	public String ativa(@PathVariable("id") Long id, RedirectAttributes attr) {
	 try {
	 Cliente cliente = clienteBO.pesquisaPeloID(id);
	 clienteBO.ativa(cliente);
	 attr.addFlashAttribute("feedback", "Cliente ativado com sucesso");
	 } catch (Exception e) {
	 e.printStackTrace();
	 }
	 return "redirect:/clientes";
	}
}
