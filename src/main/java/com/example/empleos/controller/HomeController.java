package com.example.empleos.controller;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.empleos.model.Vacante;
import com.example.empleos.service.IVacanteService;

@Controller
public class HomeController {
	
	
	@Autowired
	private IVacanteService serviceVacantes;
	
	
	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		List<Vacante>lista = serviceVacantes.buscarTodas();
		model.addAttribute("vacantes", lista);

		return "tabla";
	}
	
	@GetMapping("/")
	public String mostrarHome(Model model) {
		List<Vacante>lista = serviceVacantes.buscarTodas();
		model.addAttribute("vacantes", lista);
		
		
		return "home";
	}
	
	@GetMapping("/listado")
	public String mostarListado(Model model){
		List<String> lista = new LinkedList<String>();
		lista.add("Ingeniero de sistemas");
		lista.add("Auxiliar de Contabilidad");
		lista.add("Vendedor");
		lista.add("Arquitecto");
		
		model.addAttribute("empleos", lista);
		
		
		
		return "listado";
	}	 
	@GetMapping("/detalle")
	public String mostrarDetalle(Model model){
		Vacante vacante = new Vacante();
		vacante.setNombre("Ingeniero de comunicaciones");
		vacante.setDescripcion("Se solicita ingeniero para dar soporte a intranet");
		vacante.setFecha(new Date());
		vacante.setSalario(9000.0);
		model.addAttribute("vacante",vacante);
		
		
		
		
		return "detalle";
	}
	
}