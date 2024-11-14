package com.souleimen.avions.entities;


import java.util.Date;
//import java.util.List;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Past;

@Entity
public class Avion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAvion;
	
	@NotNull
	//@Size (min = 5,max = 50)
	private String matriculeAvion;
	
	@Min(value = 1000)
	@Max(value = 1000000000)
	private int celometrageAvion;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past
	private Date dateFabrication;
	
	@ManyToOne
	private TypeAv typeAv;
	
//	 @OneToOne 
//	 private Image image;
	 
	 @OneToMany (mappedBy = "avion") 
	 private List<Image> images; 
	 
	 private String imagePath; 

	 	
	
	public Avion() {
		super();
	}
	
	public Avion(String matriculeAvion, int celometrageAvion, Date dateFabrication) {
		super();
		this.matriculeAvion = matriculeAvion;
		this.celometrageAvion = celometrageAvion;
		this.dateFabrication = dateFabrication;
	}

	public Long getIdAvion() {
		return idAvion;
	}
	public void setIdAvion(Long idAvion) {
		this.idAvion = idAvion;
	}
	public String getMatriculeAvion() {
		return matriculeAvion;
	}
	public void setMatriculeAvion(String matriculeAvion) {
		this.matriculeAvion = matriculeAvion;
	}
	public int getCelometrageAvion() {
		return celometrageAvion;
	}
	public void setCelometrageAvion(int celometrageAvion) {
		this.celometrageAvion = celometrageAvion;
	}
	public Date getDateFabrication() {
		return dateFabrication;
	}
	public void setDateFabrication(Date dateFabrication) {
		this.dateFabrication = dateFabrication;
	}


	@Override
	public String toString() {
		return "Avion [idAvion=" + idAvion + ", matriculeAvion=" + matriculeAvion + ", celometrageAvion="
				+ celometrageAvion + ", dateFabrication=" + dateFabrication + "]";
	}

	public TypeAv getTypeAv() {
		return typeAv;
	}

	public void setTypeAv(TypeAv typeAv) {
		this.typeAv = typeAv;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	
		
	
		
}
