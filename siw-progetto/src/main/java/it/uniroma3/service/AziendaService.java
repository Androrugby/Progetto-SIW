package it.uniroma3.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.model.Azienda;
import it.uniroma3.repository.AziendaRepository;

@Transactional
@Service
public class AziendaService {
	
	@Autowired
	private AziendaRepository aziendaRepository;
	
	public Azienda save(Azienda azienda) {
		return this.aziendaRepository.save(azienda);
	}
	
	public List<Azienda> findAll(){
		return (List<Azienda>) this.aziendaRepository.findAll();
	}
	public List<Azienda> findByName(String name){
		return this.aziendaRepository.findByName(name);
	}
	
	public Azienda findById(Long id) {
		Optional<Azienda> aziende = this.aziendaRepository.findById(id);
		if(aziende.isPresent())
			return aziende.get();
		else
			return null;
	}
}
