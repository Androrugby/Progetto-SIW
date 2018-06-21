package it.uniroma3.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.Attivita;
import it.uniroma3.model.Centro;

public interface CentroRepository extends CrudRepository<Centro, Long>{
	
	public Optional<Centro> findById(Long id);	
	public List<Centro> findByNameAndIndirizzo(String name, String indirizzo);
	public List<Centro> findByName(String name);
	public List<Centro> findByIndirizzo(String indirizzo);
	public List<Centro> findAll();

	public List<Centro> findByAzienda_Id(Long id);
	public List<Centro> findByAttivita_Id(Long id);
	public List<Centro> findByUtenti_Id(Long id);
	
}
