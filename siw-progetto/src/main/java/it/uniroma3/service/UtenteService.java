package it.uniroma3.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.model.Allievo;
import it.uniroma3.model.Utente;
import it.uniroma3.repository.UtenteRepository;

@Transactional
@Service
public class UtenteService {
	
	@Autowired
	private UtenteRepository UtenteRepository;
	
	public Utente save(Utente utente) {
		return this.UtenteRepository.save(utente);
	}
	
	public List<Utente> findByName(String name){
		return this.UtenteRepository.findByName(name);
	}
	
	public List<Utente> findAll(){
		return (List<Utente>) this.UtenteRepository.findAll();
	}
	
	public List<Utente> findByCentro_id(Long id){
		return this.UtenteRepository.findByCentro_Id(id);
	}
	
	public List<Utente> findByAzienda_id(Long id){
		return this.UtenteRepository.findByAzienda_Id(id);
	}
	
	public Utente findById(Long id) {
		Optional<Utente> utente = this.UtenteRepository.findById(id);
		if(utente.isPresent())
			return utente.get();
		else
			return null;
	}

	public boolean alreadyExists(@Valid Utente utente) {
		List<Utente> utenti = this.UtenteRepository.findByName(utente.getName());
		if(utenti.size() > 0)
			return true;
		else
			return false;
	}
}
