package it.uniroma3.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Centro {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	private String indirizzo;
	
	private String email;
	
	private int phone;
	
	private int capacity;
	
	@OneToMany
	@JoinColumn(name="centro_id")
	private List<Utente> utenti;
	
	@ManyToOne
	private Azienda azienda;
	
	@OneToMany
	@JoinColumn(name="centro_id")
	private List<Allievo> allievi;
	
	@ManyToMany(mappedBy="centri")
	private List<Attivita> attivita;

	public Centro() {
		this.azienda = new Azienda();
		this.utenti = new ArrayList<>();
		this.attivita = new ArrayList<>();
		this.allievi = new ArrayList<>();
	}
	
	public Centro(String name, String indirizzo, String email, int phone, int capacity) {
		this.name=name;
		this.indirizzo=indirizzo;
		this.email = email;
		this.phone = phone;
		this.capacity=capacity;
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

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public List<Attivita> getAttivita() {
		return attivita;
	}
	public void setAttivita(List<Attivita> attivita) {
		this.attivita = attivita;
	}
	public void addAttivita(Attivita attivita) {
		this.attivita.add(attivita);
	}
	
	public List<Allievo> getAllievi(){
		return allievi;
	}
	public void addAllievo(Allievo allievo) {
		this.allievi.add(allievo);
	}
	public void setAllievo(List<Allievo> allievi) {
		this.allievi = allievi;
	}
	
	public List<Utente> getUtenti(){
		return utenti;
	}
	public void addUtente(Utente utente) {
		this.utenti.add(utente);
	}
	public void setUtente(List<Utente> utenti) {
		this.utenti = utenti;
	}
	
	public Azienda getAzienda() {
		return this.azienda;
	}
}
