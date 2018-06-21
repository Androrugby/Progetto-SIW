package it.uniroma3.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Attivita {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String day;
	
	private String orario;
	
	@ManyToMany
	private List<Allievo> allievi;
	
	@ManyToMany
	private List<Centro> centri;
	
	public Attivita() {
		this.allievi = new ArrayList<>();
		this.centri = new ArrayList<>();
	}
	
	public Attivita(String name, String data, String orario) {
		this.name=name;
		this.day=data;
		this.orario = orario;
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

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getOrario() {
		return orario;
	}

	public void setOrario(String orario) {
		this.orario = orario;
	}
	
	public void addAllievo(Allievo allievo) {
		this.allievi.add(allievo);
	}
	
	public List<Allievo> getAllievi(){
		return allievi;
	}
	
	public void setAllievi(List<Allievo> allievi) {
		this.allievi = allievi;
	}
	
	public List<Centro> getCentri(){
		return centri;
	}
	
	public void addCentro(Centro centro) {
		this.centri.add(centro);
	}
	public void setCentri(List<Centro> centri) {
		this.centri = centri;
	}
}
