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
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.controller.validator.CentroValidator;
import it.uniroma3.model.Allievo;
import it.uniroma3.model.Attivita;
import it.uniroma3.model.Centro;
import it.uniroma3.service.AllievoService;
import it.uniroma3.service.AttivitaService;
import it.uniroma3.service.CentroService;

@Controller
public class CentroController {
	
	@Autowired
	public CentroService centroService;
	
	@Autowired
	public CentroValidator validator;

	@Autowired
	private AttivitaService attivitaService;

	@Autowired
	private AllievoService allievoService;
	
	@RequestMapping("/centriList")
	public String centri(Model model) {
		model.addAttribute("centri", this.centroService.findAll());
		return "centroList";
	}
	
	@RequestMapping("/tornaindex")
	public String backHome() {
			return "index";
	}
	
	@GetMapping("/centroForm")
	public String addAllievo(Model model) {
		model.addAttribute("centro", new Allievo());
		return "centroForm";
	}

	@PostMapping("/centro")
	public String newCentro(@Valid @ModelAttribute("centro") Centro centro,
			Model model,HttpSession session, BindingResult bindingResult) {
		this.validator.validate(centro, bindingResult);
		if(this.centroService.alreadyExists(centro)) {
			model.addAttribute("exists", "Centro already exists");
			return "centroForm";
		}else {
			if(!bindingResult.hasErrors()) {
				this.centroService.save(centro);
				session.setAttribute("centro", centro);
				return "mostraCentro";
			}else
				return "centroForm";
		}
	}
	
	/*@RequestMapping(value = "/centro/{id}", method = RequestMethod.GET)
	public String getCentro(@PathVariable("id") Long id, Model model) {
		model.addAttribute("centro", this.centroService.findById(id));
		return "mostraCentro";
	}*/
	
	/*@GetMapping(value="/attivita/{id}")
	public String getAttivita2(@PathVariable("id") Long id, Model model) {
		List<Centro> iscritti = this.centroService.findByAttivita_Id(id);
		model.addAttribute("attivita", this.attivitaService.findById(id));
		model.addAttribute("centri", iscritti);
		return "mostraAttivita";
	}*/
	
	@GetMapping("/addCentroAttivita")
	public String goToAddAttivita(@Valid @ModelAttribute("attivita") Attivita attivita, HttpSession session, Model model) {
		model.addAttribute("attivitas", this.attivitaService.findAll());
		return "addCentroAttivita";
	}
	
	@RequestMapping("/formCAtt")
	public String salvaCentroNuovoAttivita(@RequestParam("id") Long id, HttpSession session, Model model) {
		Centro all = (Centro)session.getAttribute("centro");
		if(all!=null) {
			Attivita att = attivitaService.findById(id);
			att.getCentri().add(all);
			all.getAttivita().add(att);
			this.attivitaService.save(att);
			this.centroService.save(all);
			return "inseritoCentroAttivita";
		}else
			return "centroForm";
	}
	
	//search
	@GetMapping("/centroSearchForm")
	public String centroSearchForm(Model model) {
		model.addAttribute("centro", new Centro());
		return "centroSearchForm";
	}
	@GetMapping("/centroSearch")
	public String centroSearch(Centro centro, Model model) {
		model.addAttribute("centri", this.centroService.findByName(centro.getName()));
		return "centroList";
	}
	@GetMapping(value="/centro/{id}")
	public String getCentro(@PathVariable("id") Long id, Model model) {
		List<Attivita> attivita =this.attivitaService.findByCentri_Id(id);
		model.addAttribute("centro", this.centroService.findById(id));
		model.addAttribute("attivita", attivita);
		return "mostraCentro";
	}
}
