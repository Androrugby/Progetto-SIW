package it.uniroma3.repository;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.Allievo;
import it.uniroma3.model.Attivita;

public interface AttivitaRepository extends CrudRepository<Attivita,Long>{
	
	public Optional<Attivita> findById(Long id);	
	

	public List<Attivita> findAll();
	public List<Attivita> findByName(String name);
	public List<Attivita> findByAllievi_id(Long id);


	public List<Attivita> findByCentri_Id(Long id);


	
}
