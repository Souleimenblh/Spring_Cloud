package com.souleimen.avions.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.souleimen.avions.entities.TypeAv;
import com.souleimen.avions.repos.TypeAvRepository;

@RestController
@RequestMapping("/api/typ")
@CrossOrigin("*")
public class TypeAvRESTController {

    @Autowired
    TypeAvRepository typeAvRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<TypeAv>> getAllCategories() {
        List<TypeAv> categories = typeAvRepository.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TypeAv> getCategoryById(@PathVariable("id") Long id) {
        Optional<TypeAv> category = typeAvRepository.findById(id);
        return category.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TypeAv> createCategory(@RequestBody TypeAv category) {
        TypeAv createdCategory = typeAvRepository.save(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id) {
        if (typeAvRepository.existsById(id)) {
            typeAvRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TypeAv> updateCategory(@PathVariable("id") Long id, @RequestBody TypeAv category) {
        if (typeAvRepository.existsById(id)) {
            category.setIdAv(id);  // Ensure the ID is set correctly
            TypeAv updatedCategory = typeAvRepository.save(category);
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
