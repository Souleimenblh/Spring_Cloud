package com.souleimen.avions.repos;

import org.springframework.data.jpa.repository.JpaRepository; 
import com.souleimen.avions.entities.Image; 
 
 
public interface ImageRepository extends JpaRepository<Image , Long> { 
} 