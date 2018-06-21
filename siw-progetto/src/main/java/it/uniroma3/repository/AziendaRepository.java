package it.uniroma3.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.Azienda;

public interface AziendaRepository extends CrudRepository<Azienda, Long>{
	
	public Optional<Azienda> findById(Long id);
	
	public List<Azienda> findByName(String nome);
}
