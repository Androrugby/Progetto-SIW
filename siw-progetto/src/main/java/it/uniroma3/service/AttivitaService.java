package it.uniroma3.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.model.Attivita;
import it.uniroma3.repository.AttivitaRepository;

@Transactional
@Service
public class AttivitaService {

	@Autowired
	public AttivitaRepository attivitaRepository;

	public Attivita save(Attivita attivita) {
		return this.attivitaRepository.save(attivita);
	}

	public List<Attivita> findByName(String name){
		return this.attivitaRepository.findByName(name);
	}


	public List<Attivita> findAll() {
		return this.attivitaRepository.findAll();
	}
	
	public List<Attivita> findByAllievi(Long id){
		return this.attivitaRepository.findByAllievi_id(id);
	}
	
	public List<Attivita> findByCentri_Id(Long id) {
		return this.attivitaRepository.findByCentri_Id(id);
	}


	public Attivita findById(Long id) {
		Optional<Attivita> attivita = this.attivitaRepository.findById(id);
		if(attivita.isPresent())
			return attivita.get();
		else
			return null;
	}

	public boolean alreadyExists(Attivita attivita) {
		List<Attivita> attivitas = this.attivitaRepository.findByName(attivita.getName());
		if(attivitas.size() >0)
			return true;
		else
			return false;
	}



//	//metodo per attivita=>alunno
//	public List<Attivita> findByAllievo(Long id){
//		Optional<Attivita> a = this.attivitaRepository.findByAllievi(id);
//		if(a.isPresent())
//			return (List<Attivita>) a.get();
//		else
//			return null;
//	}
//
//	public void save(@Valid Allievo allievo) {
//		this.attivitaRepository.save(allievo);
//	}


}
