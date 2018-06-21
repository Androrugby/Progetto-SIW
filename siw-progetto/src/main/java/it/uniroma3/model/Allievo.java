package it.uniroma3.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Allievo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String lastName;
	
	private String email;
	
	private int phone;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date giorno;
	
	private String place;
	
	@ManyToMany(mappedBy="allievi")
	private List<Attivita> attivita;
	
	@ManyToOne
	private Centro centro;
	
	public Allievo() {
		this.attivita = new ArrayList<>();
	}
	
	public Allievo(String name, String lastName,String email, int phone,Date giorno, String place) {
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.giorno = giorno;
		this.place = place;
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
		this.name =name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public Date getGiorno() {
		return giorno;
	}

	public void setGiorno(Date giorno) {
		this.giorno = giorno;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public void addAttivita(Attivita attivita) {
		this.attivita.add(attivita);		
	}	
	public List<Attivita> getAttivita(){
		return this.attivita;
	}
	
	public Centro getCentro() {
		return this.centro;
	}
	
	
	
}
