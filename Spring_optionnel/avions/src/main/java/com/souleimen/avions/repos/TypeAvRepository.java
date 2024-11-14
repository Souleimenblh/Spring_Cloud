package com.souleimen.avions.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.souleimen.avions.entities.TypeAv;

@RepositoryRestResource(path = "typ")
@CrossOrigin(origins ="http://localhost:4200/") //pour autoriser angular 
public interface TypeAvRepository extends JpaRepository<TypeAv, Long> 
{
}
