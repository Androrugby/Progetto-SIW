package it.uniroma3.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.controller.validator.UtenteValidator;
import it.uniroma3.model.Utente;
import it.uniroma3.service.AttivitaService;
import it.uniroma3.service.CentroService;
import it.uniroma3.service.UtenteService;

@Controller
public class UtenteController {
	
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private AttivitaService attivitaService;

	@Autowired
	private CentroService centroService;
	
	@Autowired
	private UtenteValidator validator;
	
	@GetMapping("/utentiList")
	public String utenti(Model model) {
		model.addAttribute("utenti", this.utenteService.findAll());
		return "utenteList";
	}
	
	@GetMapping("/utenteForm")
	public String addUtente(Model model) {
		model.addAttribute("utente", new Utente());
		return "utenteForm";
	}

	@PostMapping("/utente")
	public String newUtente(@Valid @ModelAttribute("utente") Utente utente,
			Model model,HttpSession session, BindingResult bindingResult) {
		this.validator.validate(utente, bindingResult);
		if(this.utenteService.alreadyExists(utente)) {
			model.addAttribute("exists", "Utente already exists");
			return "utenteForm";
		}else {
			if(!bindingResult.hasErrors()) {
				this.utenteService.save(utente);
				session.setAttribute("utente", utente);
				return "mostraUtente";
			}else
				return "utenteForm";
		}
	}
	
	@RequestMapping(value = "/utente/{id}", method = RequestMethod.GET)
	public String getUtenti(@PathVariable("id") Long id, Model model) {
		model.addAttribute("utente", this.utenteService.findById(id));
		return "mostraUtente";
	}
	
	
	//search
		@GetMapping("/utenteSearchForm")
		public String centroSearchForm(Model model) {
			model.addAttribute("utente", new Utente());
			return "utenteSearchForm";
		}
		@GetMapping("/utenteSearch")
		public String centroSearch(Utente utente, Model model) {
			model.addAttribute("utenti", this.utenteService.findByName(utente.getName()));
			return "utenteList";
		}
		/*@GetMapping(value="/utente/{id}")
		public String getUtente(@PathVariable("id") Long id, Model model) {
			List<Centro> centro =this.centroService.findByUtenti_Id(id);
			model.addAttribute("utente", this.utenteService.findById(id));
			model.addAttribute("centri", centro);
			return "mostraUtente";
		}*/
}
