package it.uniroma3.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.model.Allievo;
import it.uniroma3.repository.AllievoRepository;

@Transactional
@Service
public class AllievoService {
	
	@Autowired
	public AllievoRepository allievoRepository;
	
	
	public Allievo save(Allievo allievo) {
		return this.allievoRepository.save(allievo);		
	}

	public List<Allievo> findAll() {
		return (List<Allievo>) this.allievoRepository.findAll();
	}

	public List<Allievo> findByName(String name){
		return this.allievoRepository.findByName(name);
	}
	
	public List<Allievo> findByLastName(String lastName){
		return this.allievoRepository.findByLastName(lastName);
	}
	
	public List<Allievo> findByPlace(String place){
		return this.allievoRepository.findByPlace(place);
	}
	
	public List<Allievo> findByAttivita(Long id){
		return this.allievoRepository.findByAttivita_Id(id);
	}

	public Allievo findById(Long id) {
		Optional<Allievo> allievo = this.allievoRepository.findById(id);
		if(allievo.isPresent())
			return allievo.get();
		else
			return null;
	}

	public boolean alreadyExists(Allievo allievo) {
		List<Allievo> allievi = this.allievoRepository.findByName(allievo.getName());
		if(allievi.size() >0)
			return true;
		else
			return false;
	}

	/*public List<Allievo> findByNameAndLastName(String name, String lastName) {
		return this.allievoRepository.findByNameAndLastName(name, lastName);
	}*/

//	public List<Attivita> addAttivita(String name){
//		return this.allievoRepository.addAttivita(name);
//	}
	
	/*@Query("select * from allievo a")
	public List<Allievo> findAttivitaByNameAndLastName(String name, String lastName){
		return this.allievoRepository.findByNameAndLastName(name, lastName);
	}*/

}
