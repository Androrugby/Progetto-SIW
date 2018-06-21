package it.uniroma3.controller;

import java.util.List;

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

import it.uniroma3.controller.validator.AttivitaValidator;
import it.uniroma3.model.Allievo;
import it.uniroma3.model.Attivita;
import it.uniroma3.service.AllievoService;
import it.uniroma3.service.AttivitaService;

@Controller
public class AttivitaController {
	
	@Autowired
	private AttivitaService attivitaService;
	
	@Autowired
	private AllievoService allievoService;
	
	@Autowired
	private AttivitaValidator validator;

	@GetMapping("/attivitaForm")
	public String addAttivita(Model model) {
		model.addAttribute("attivita", new Attivita());
		return "attivitaForm";
	}

	@GetMapping("/attivitaList")
	public String attivitas(Model model) {
		model.addAttribute("attivitas", this.attivitaService.findAll());
		return "attivitaList";
	}

	
	@RequestMapping(value = "/allievo/{id}", method = RequestMethod.GET)
	public String getAllievi(@PathVariable("id") Long id, Model model) {
		List<Attivita> iscritti = this.attivitaService.findByAllievi(id);
		model.addAttribute("allievi", this.allievoService.findById(id));
		model.addAttribute("attivita", iscritti);
		model.addAttribute("numero", iscritti.size());
		return "mostraAllievo";
	}
	
	@PostMapping("/attivita")
	public String newAttivita(@Valid @ModelAttribute("attivita") Attivita attivita,
									Model model,HttpSession session, BindingResult bindingResult) {
		this.validator.validate(attivita, bindingResult);
		if(this.attivitaService.alreadyExists(attivita)) {
			model.addAttribute("exists", "Attivita already exists");
			return "attivitaForm";
		}else {
			if(!bindingResult.hasErrors()) {
				this.attivitaService.save(attivita);
				session.setAttribute("attivita", attivita);
				return "mostraAttivita";
			}else
				return "addAttivita";
		}
	}
	
	@GetMapping("/addAttivitaAllievo")
	public String goToAddAllievo(@Valid @ModelAttribute("allievo") Attivita attivita, HttpSession session, Model model) {
		model.addAttribute("allievi", this.allievoService.findAll());
		session.setAttribute("allievo", attivita);
		return "addAttivitaAllievo";
	}
	
	
	@GetMapping("/attivitaSearch")
	public String attivitaSearchFrom(Model model) {
		model.addAttribute("attivita", new Attivita());
		return "attivitaSearchForm";
	}
	
	@GetMapping("/attivitaCerca")
	public String attivitaSearch(Attivita attivita, Model model) {
		model.addAttribute("attivitas", this.attivitaService.findByName(attivita.getName()));
		return "attivitaList";
	}
	
//	@PostMapping("/attivitaCerca")
//	public String attivitaCercata(@Valid @ModelAttribute("attivita") Attivita attivita, 
//				Model model, BindingResult bindingResults) {
//		this.validator.validate(attivita, bindingResults);
//		if(!bindingResults.hasErrors())
//			return "mostraAttivita";
//		else
//			return "attivitaSearchForm";
//	}
	
	@GetMapping(value = "/attivita/{id}")
	public String getAttivitaCercata(@PathVariable("id") Long id, Model model, HttpSession session) {
		List<Allievo> allievi = this.allievoService.findByAttivita(id);
		model.addAttribute("attivita", this.attivitaService.findById(id));
		model.addAttribute("allievi", allievi);
		return "mostraAttivita";
	}

}
