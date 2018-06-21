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
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.controller.validator.AllievoValidator;
import it.uniroma3.model.Allievo;
import it.uniroma3.model.Attivita;
import it.uniroma3.service.AllievoService;
import it.uniroma3.service.AttivitaService;

@Controller
public class AllievoController {

	@Autowired
	private AllievoService allievoService;

	@Autowired
	private AttivitaService attivitaService;

	@Autowired
	private AllievoValidator validator;


	@GetMapping("/allievoForm")
	public String addAllievo(Model model) {
		model.addAttribute("allievo", new Allievo());
		return "allievoForm";
	}

	@PostMapping("/allievo")
	public String newAllievo(@Valid @ModelAttribute("allievo") Allievo allievo,
			Model model,HttpSession session, BindingResult bindingResult) {
		this.validator.validate(allievo, bindingResult);
		if(this.allievoService.alreadyExists(allievo)) {
			model.addAttribute("exists", "Allievo already exists");
			return "allievoForm";
		}else {
			if(!bindingResult.hasErrors()) {
				this.allievoService.save(allievo);
				session.setAttribute("allievo", allievo);
				return "mostraAllievo";
			}else
				return "allievoForm";
		}
	}

	
	@GetMapping("/allievoList")
	public String allievi(Model model) {
		model.addAttribute("allievi", this.allievoService.findAll());
		return "allievoList";
	}
	
	@GetMapping(value = "/attivitas/{id}")
	public String getAttivita(@PathVariable("id") Long id, Model model, HttpSession session) {
		List<Allievo> iscritti = this.allievoService.findByAttivita((Long) session.getAttribute("id"));
		model.addAttribute("attivitas", this.attivitaService.findById(id));
		model.addAttribute("allievi", iscritti);
		model.addAttribute("numero", iscritti.size());
		return "mostraAttivita";
	}
	
	@GetMapping("/addAllievoAttivita")
	public String goToAddAttivita(@Valid @ModelAttribute("attivita") Attivita attivita, HttpSession session, Model model) {
		model.addAttribute("attivita", this.attivitaService.findAll());
		return "addAllievoAttivita";
	}
	/*@GetMapping("/addAllievoAttivita")
	public String goToAddAttivita(@ModelAttribute("attivita") Attivita attivita, HttpSession session, Model model) {
		Allievo allievo = (Allievo) session.getAttribute("allievo");
		model.addAttribute("allievo", allievo);
		model.addAttribute("attivitas", this.attivitaService.findAll());
		return "addAllievoAttivita";
	}
	*/
	
	@RequestMapping("/formAA")
	public String salvaAllievoNuovoAttivita(@RequestParam("id") Long id, HttpSession session, Model model) {
		Allievo all = (Allievo)session.getAttribute("allievo");
		if(all!=null) {
			Attivita att = attivitaService.findById(id);
			att.getAllievi().add(all);
			all.getAttivita().add(att);
			session.setAttribute("id", id);
			this.attivitaService.save(att);
			this.allievoService.save(all);
			return "inseritoAttivita";
		}else
			return "allievoForm";
	}
	

	
	@GetMapping("/allievoSearchForm")
	public String allievoSearchForm(Model model){
		model.addAttribute("allievo", new Allievo());
		return "allievoSearchForm";
	}
	
	@GetMapping("/allievoSearch")
	public String allievoSearch(Allievo allievo, Model model) {
		model.addAttribute("allievi", this.allievoService.findByName(allievo.getName()));
		return "allievoList";
	}
	
	/*@GetMapping(value = "/allievo/{id}")
	public String getAllievoSearched(@PathVariable("id") Long id, Model model, HttpSession session) {
		List<Attivita> attivita = this.attivitaService.findByAllievi((Long) session.getAttribute("id"));
		model.addAttribute("allievo", this.allievoService.findById(id));
		model.addAttribute("attivita", attivita);
		return "mostraAllievo";
	}*/
	
	/*@GetMapping(value = "/allievo/{id}")
	public String allieviSea(@PathVariable("id") Long id, Model model, HttpSession session) {
		List<Allievo> allievi = this.allievoService.findAll();
		model.addAttribute("allievi", this.allievoService.findById(id));
		session.setAttribute("allievo", allievi);
		return "mostraAllievo";
	}*/
	
}
