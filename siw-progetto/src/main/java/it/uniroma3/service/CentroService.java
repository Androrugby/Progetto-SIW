package it.uniroma3.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.model.Attivita;
import it.uniroma3.model.Centro;
import it.uniroma3.repository.CentroRepository;

@Transactional
@Service
public class CentroService {
	
	@Autowired
	private CentroRepository centroRepository;
	
	public Centro save(Centro centro) {
		return this.centroRepository.save(centro);
	}
	
	public List<Centro> findAll(){
		return (List<Centro>) this.centroRepository.findAll();
	}
	
	public List<Centro> findByName(String name) {
		return this.centroRepository.findByName(name);
	}
	
	public List<Centro> findByIndirizzo(String indirizzo){
		return this.centroRepository.findByIndirizzo(indirizzo);
	}
	
	public List<Centro> findByAzienda_Id(Long id){
		return this.centroRepository.findByAzienda_Id(id);
	}
	
	public List<Centro> findByAttivita_Id(Long id){
		return this.centroRepository.findByAttivita_Id(id);
	}
	
	public Centro findById(Long id) {
		Optional<Centro> centro = this.centroRepository.findById(id);
		if(centro.isPresent())
			return centro.get();
		else
			return null;
	}
	
	public boolean alreadyExists(Centro centro) {
		List<Centro> centri = this.centroRepository.findByName(centro.getName());
		if(centri.size() > 0)
			return true;
		else
			return false;
	}

	public List<Centro> findByUtenti_Id(Long id) {
		return this.centroRepository.findByUtenti_Id(id);
	}

}
