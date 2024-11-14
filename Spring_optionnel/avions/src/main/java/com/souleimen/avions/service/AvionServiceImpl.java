package com.souleimen.avions.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.souleimen.avions.entities.Avion;
import com.souleimen.avions.entities.TypeAv;
import com.souleimen.avions.repos.AvionRepository;
import com.souleimen.avions.repos.ImageRepository;
import com.souleimen.avions.repos.TypeAvRepository;

@Service
public class AvionServiceImpl implements AvionService {

    @Autowired
    AvionRepository avionRepository;

    @Autowired
    TypeAvRepository typeAvRepository;

    @Autowired
    ImageRepository imageRepository;

    @Override
    public Avion saveAvion(Avion p) {
        return avionRepository.save(p);
    }

    @Override
    public Avion updateAvion(Avion p) {
        return avionRepository.save(p);
    }

    @Override
    @Transactional
    public void deleteAvion(Avion p) {
        // Delete related images first
        imageRepository.deleteById(p.getIdAvion());
        // Then delete the avion
        avionRepository.delete(p);
    }

    @Override
    @Transactional
    public void deleteAvionById(Long id) {
    	  //supprimer l'image avant de supprimer le produit
	    Avion p = getAvion(id);
	   try {
		Files.delete(Paths.get(System.getProperty("user.home")+"/images/"+p.getImagePath()));
	} catch (IOException e) {
		
		e.printStackTrace();
	}
		avionRepository.deleteById(id);
		
	}

    @Override
    public Avion getAvion(Long id) {
        return avionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Avion> getAllAvions() {
        return avionRepository.findAll();
    }

    @Override
    public Page<Avion> getAllAvionsParPage(int page, int size) {
        return avionRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<Avion> findByMatriculeAvion(String matriculeAvion) {
        return avionRepository.findByMatriculeAvion(matriculeAvion);
    }

    @Override
    public List<Avion> findByMatriculeAvionContains(String matriculeAvion) {
        return avionRepository.findByMatriculeAvionContains(matriculeAvion);
    }

    @Override
    public List<Avion> findByfindByMatriculeCelometrage(String matriculeAvion, int celometrageAvion) {
        return avionRepository.findByMatriculeCelometrage(matriculeAvion, celometrageAvion);
    }

    @Override
    public List<Avion> findByTypeAv(TypeAv typeAv) {
        return avionRepository.findByTypeAv(typeAv);
    }

    @Override
    public List<Avion> findByTypeAvIdA(Long id) {
        return avionRepository.findByTypeAvIdAv(id);
    }

    @Override
    public List<Avion> findByOrderByMatriculeAvAsc() {
        return avionRepository.findByOrderByMatriculeAvionAsc();
    }

    @Override
    public List<Avion> trierAvionsMatriculeCelometrage() {
        return avionRepository.trierAvionsMatriculeCelometrage();
    }

    @Override
    public List<TypeAv> getAllTypeAvs() {
        return typeAvRepository.findAll();
    }
}
