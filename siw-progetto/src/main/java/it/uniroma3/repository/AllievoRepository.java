package it.uniroma3.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.NamedNativeQueries;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.Allievo;
import it.uniroma3.model.Attivita;

public interface AllievoRepository extends CrudRepository<Allievo,Long>{
	
	public Optional<Allievo> findById(Long id);

	public List<Allievo> findAll();
	public List<Allievo> findByName(String name);
	public List<Allievo> findByLastName(String lastName);
	public List<Allievo> findByPlace(String place);

	public List<Allievo> findByAttivita_Id(Long id);
	
	
	/*public List<Allievo> findByNameAndLastName(String name, String lastName);
	public List<Allievo> findByName(Allievo name);
	*/
	
	
	
	
}
