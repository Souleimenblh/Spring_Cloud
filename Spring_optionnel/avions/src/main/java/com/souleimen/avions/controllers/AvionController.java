package com.souleimen.avions.controllers;

import org.springframework.web.bind.annotation.RequestMapping;




//import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

//import com.souleimen.avions.dto.Avion;
import com.souleimen.avions.entities.Avion;
import com.souleimen.avions.entities.TypeAv;
import com.souleimen.avions.service.AvionService;

import jakarta.validation.Valid;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AvionController {
	@Autowired
	AvionService avionService;
	
	@GetMapping("/accessDenied")
	public String error()
	{
	return "accessDenied";
	}


	@RequestMapping("/ListeAvions")
	public String listeAvions(ModelMap modelMap,
			@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size", defaultValue = "3") int size)
 {
		Page<Avion> avios = avionService.getAllAvionsParPage(page, size);
		modelMap.addAttribute("avions", avios);
		modelMap.addAttribute("pages", new int[avios.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("size", size);

		return "listeAvions";
	}

	@RequestMapping("/showCreate")
	public String showCreate(ModelMap modelMap) {
		List<TypeAv> typs = avionService.getAllTypeAvs();
		modelMap.addAttribute("avion", new Avion());
		modelMap.addAttribute("mode", "new");
		modelMap.addAttribute("typeAvs", typs);

		return "formAvion";
	}
	
	@RequestMapping("/saveAvion")
	public String saveAvion(@Valid Avion avion, BindingResult bindingResult, 
			@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size", defaultValue = "2") int size)
 {

		int currentPage;
		boolean isNew = false;

		if (bindingResult.hasErrors()) return "formAvion";
		
		if (avion.getIdAvion()==null) //ajout
			isNew=true;
		
		
		avionService.saveAvion(avion);
		
		if (isNew) //ajout
		{
		Page<Avion> avios = avionService.getAllAvionsParPage(page, size);
		currentPage = avios.getTotalPages()-1;
		}
		else //modif
		currentPage=page;

		//return "formAvion";
		return ("redirect:/ListeAvions?page="+currentPage+"&size="+size);

	}
	

	@RequestMapping("/supprimerAvion")
	public String supprimerAvion(@RequestParam("id") Long id,
							ModelMap modelMap,
							@RequestParam (name="page",defaultValue = "0") int page,
							@RequestParam (name="size", defaultValue = "2") int size)
		 {
		avionService.deleteAvionById(id);
		
		Page<Avion> avios = avionService.getAllAvionsParPage(page, 
				size);
				modelMap.addAttribute("avions", avios);
				modelMap.addAttribute("pages", new int[avios.getTotalPages()]);
				modelMap.addAttribute("currentPage", page);
				modelMap.addAttribute("size", size);
				
		return "listeAvions";
	}

	@RequestMapping("/modifierAvion")
	public String editerAvion(@RequestParam("id") Long id, ModelMap modelMap,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {
		List<TypeAv> typs = avionService.getAllTypeAvs();
		Avion p = avionService.getAvion(id);
		modelMap.addAttribute("avion", p);
		modelMap.addAttribute("mode", "edit");
		modelMap.addAttribute("typeAvs", typs);
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("size", size);


		return "formAvion";
	}

	@RequestMapping("/updateAvion")
	public String updateAvion(@ModelAttribute("avion") Avion avion, @RequestParam("date") String date, ModelMap modelMap) throws ParseException {
		//conversion de la date 
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateFabrication = dateformat.parse(String.valueOf(date));
		avion.setDateFabrication(dateFabrication);

		avionService.updateAvion(avion);
		List<Avion> avios = avionService.getAllAvions();
		//List<Avion> avios = avionService.getAllAvions();
		
		modelMap.addAttribute("avions", avios);
		
		return "listeAvions";
	}
	
	@GetMapping(value = "/")
	public String welcome() {
	 return "index";
	 
	 /*
	 @PostMapping("/createAvion")
	 public String createAvion(@Valid Avion avion, BindingResult bindingResult, ModelMap modelMap) {
	     if (bindingResult.hasErrors()) {
	         // If there are validation errors, return to the form with error messages
	         modelMap.addAttribute("avion", avion);
	         modelMap.addAttribute("typeAvs", avionService.getAllTypeAvs());
	         return "formAvion";
	     }

	     avionService.saveAvion(avion);
	     modelMap.addAttribute("message", "Avion créé avec succès");
	     return "success";
	 }
	}
	*/
}
}