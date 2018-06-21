package it.uniroma3;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.uniroma3.model.Allievo;
import it.uniroma3.model.Attivita;
import it.uniroma3.model.Centro;
import it.uniroma3.model.Utente;
import it.uniroma3.service.AllievoService;
import it.uniroma3.service.AttivitaService;
import it.uniroma3.service.CentroService;
import it.uniroma3.service.UtenteService;

@SpringBootApplication
public class SiwSpringApplication {
	
	@Autowired
	private AllievoService allievoService;
	
	@Autowired
	private CentroService centroService;
	
	@Autowired
	private AttivitaService attivitaService;
	
	@Autowired
	private UtenteService utenteService;
	
	public static void main(String[] args) {
		SpringApplication.run(SiwSpringApplication.class, args);
	}
	
	@PostConstruct
	 public void init() {
		  Allievo allievo = new Allievo("Giorgio", "Fausto", "gio@live.it", 5555, new Date(11), "Roma");
		  Allievo allievo2 = new Allievo("Lebron", "James", "leb@live.it", 1259, new Date(14), "Congo");
		  Allievo allievo3 = new Allievo("Steph", "Curry", "steph@live.it", 7589, new Date(89),  "Roma");
		  allievoService.save(allievo);
		  allievoService.save(allievo2);
		  allievoService.save(allievo3);
		  
		  Centro centro = new Centro("arezzo&CO", "via merlo 52", "m52@live.it", 2409, 20);
		  centroService.save(centro);
		  
		  Attivita attivita = new Attivita("tessile", "maggio", "15:00");
		  this.attivitaService.save(attivita);
		  
		  Utente utente = new Utente("Totti", 10);
		  this.utenteService.save(utente);
		
		
	}

}
