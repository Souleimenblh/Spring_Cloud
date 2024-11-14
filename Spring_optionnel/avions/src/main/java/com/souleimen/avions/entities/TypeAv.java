package com.souleimen.avions.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
//import lombok.NoArgsConstructor;

@Data
//@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TypeAv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAv;
    private String matriculeAv;
    private String infoAv;

    @OneToMany(mappedBy = "typeAv")
    @JsonIgnore
    private List<Avion> avions;

    public TypeAv() {
        super();
    }

    public Long getIdAv() {
        return idAv;
    }

    public void setIdAv(Long idAv) {
        this.idAv = idAv;
    }

    public String getMatriculeAv() {
        return matriculeAv;
    }

    public void setMatriculeAv(String matriculeAv) {
        this.matriculeAv = matriculeAv;
    }

    public String getInfoAv() {
        return infoAv;
    }

    public void setInfoAv(String infoAv) {
        this.infoAv = infoAv;
    }

    public List<Avion> getAvions() {
        return avions;
    }

    public void setAvions(List<Avion> avions) {
        this.avions = avions;
    }
}