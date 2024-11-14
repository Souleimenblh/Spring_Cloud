package com.souleimen.avions.entities;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "matriculeAvio", types = { Avion.class })
public interface AvionProjection {
	public String getMatriculeAvion();

}
