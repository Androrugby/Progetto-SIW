package it.uniroma3.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Utente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private int numero;
	
	@ManyToOne
	private Centro centro;
	
	@ManyToOne
	private Azienda azienda;
	
	public Utente() {
		this.centro = new Centro();
		this.azienda = new Azienda();
	}
	
	public Utente(String name, int numero) {
		this.name=name;
		this.numero = numero;
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
	
	public Azienda getAzienda() {
		return this.azienda;
	}
	
	public Centro getCentro() {
		return this.centro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
	}
}
