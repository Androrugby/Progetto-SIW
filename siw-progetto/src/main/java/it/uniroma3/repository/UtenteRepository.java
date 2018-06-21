package it.uniroma3.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long>{

	public Optional<Utente> findById(Long id);
	public List<Utente> findByName(String name);
	public List<Utente> findAll();

	public List<Utente> findByAzienda_Id(Long id);
	public List<Utente> findByCentro_Id(Long id);
	
}
