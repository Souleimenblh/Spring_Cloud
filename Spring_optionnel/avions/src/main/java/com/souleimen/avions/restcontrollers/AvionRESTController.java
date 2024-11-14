package com.souleimen.avions.restcontrollers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import com.souleimen.avions.dto.Avion;
import com.souleimen.avions.entities.Avion;
import com.souleimen.avions.repos.AvionRepository;
import com.souleimen.avions.service.AvionService;
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
//@CrossOrigin(origins = "http://localhost:4200")

public class AvionRESTController {
@Autowired
AvionService avionService;

//@RequestMapping(method = RequestMethod.GET)
//public List<Avion> getAllAvions() {
//return avionService.getAllAvions();
//}

@RequestMapping(path="all",method =RequestMethod.GET)
public List<Avion> getAllAvions() {
return avionService.getAllAvions();
}
@RequestMapping(value="/getbyid/{id}",method = RequestMethod.GET)
public Avion getAvionById(@PathVariable("id") Long id) {
return avionService.getAvion(id);
}
@RequestMapping(path="/addavio",method = RequestMethod.POST)
public Avion createAvion(@RequestBody Avion avion) {
return avionService.saveAvion(avion);
}
@RequestMapping(path="/updateavio",method = RequestMethod.PUT)
public Avion updateAvion(@RequestBody Avion avion) {
return avionService.updateAvion(avion);
}
@RequestMapping(value="/delavio/{id}",method = RequestMethod.DELETE)
public void deleteAvion(@PathVariable("id") Long id)
{
avionService.deleteAvionById(id);
}
@RequestMapping(value="/aviotyp/{idTyp}",method = RequestMethod.GET)
public List<Avion> getAvionsByTypId(@PathVariable("idTyp") Long idTyp) {
return avionService.findByTypeAvIdA(idTyp);
}

}
