package it.uniroma3.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Azienda {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@OneToMany
	private List<Utente> utenti;
	
	@OneToMany
	private List<Centro> centri;
	
	public Azienda() {
		this.utenti = new ArrayList<>();
		this.centri = new ArrayList<>();
	}
	
	public Azienda(String name) {
		this.name=name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Utente> getUtenti(){
		return this.utenti;
	}
	public void addUtente(Utente utente) {
		this.utenti.add(utente);
	}
	public void setUtenti(List<Utente> utenti) {
		this.utenti = utenti;
	}
	
	public List<Centro> getCentri(){
		return this.centri;
	}
	public void addCentro(Centro centro) {
		this.centri.add(centro);
	}
	public void setCentri(List<Centro> centro) {
		this.centri= centro;
	}
	
}
