package com.example.empleos.controller;





import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.empleos.model.Vacante;
import com.example.empleos.service.ICategoriasService;
import com.example.empleos.service.IVacanteService;
import com.example.empleos.util.Utileria;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {
	
	@Value("${jobfinderapp.ruta.imagenes}")
	private String ruta;
	
	@Autowired
	private IVacanteService serviceVacante;
	@Autowired
	private ICategoriasService serviceCategorias;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Vacante> listaVacantes = serviceVacante.buscarTodas();
		model.addAttribute("listaVacantes",listaVacantes);
		
		return "vacantes/listVacantes";
	}
	
	
	
	
	
	@GetMapping("/create")
	public String crear(Vacante vacante,Model model) {
		
		model.addAttribute("categorias",serviceCategorias.buscarTodas());
		System.out.print("Lista"+serviceCategorias.buscarTodas());
		
		return "vacantes/formVacante";
	}
	@PostMapping("/save")
	public String guardar(Vacante vacante,BindingResult result,RedirectAttributes attributes,@RequestParam("archivoImagen")MultipartFile multipart ) {
		if(result.hasErrors()) {
			for(ObjectError error:result.getAllErrors()) {
				System.out.println("Error: "+error);
				return "vacantes/formVacante";
			}
		}
		if(!multipart.isEmpty()) {
			//String ruta ="d:/CURSOS/SPRING BOOT/MATERIAL/img-vacantes/";
			String nombreImagen = Utileria.guardarArchivo(multipart, ruta);
			if(nombreImagen != null) {
				vacante.setLogotipo(nombreImagen);
			}
		}
		
		serviceVacante.guardar(vacante);
		attributes.addFlashAttribute("msg","Registro guardado");
		System.out.println("Vacante"+vacante);	
		return "redirect:/vacantes/index";
	}
	
	/*
	@PostMapping("/save")
	public String guardar(@RequestParam("nombre")String nombre, @RequestParam("descripcion")String descripcion,@RequestParam("categoria")String categoria,
							@RequestParam("estatus") String estatus,@RequestParam("fecha") String fecha,@RequestParam("destacado") int destacado,
							@RequestParam("salario") double salario,@RequestParam("detalles")String detalles) {
		System.out.println("Nombre vacante: "+ nombre);
		System.out.println("Descripcion: "+ descripcion);
		System.out.println("Categoria: "+ categoria);
		System.out.println("Estatus: "+ estatus);
		System.out.println("Fecha publicacion "+ fecha);
		System.out.println("Vacante destacada: "+ destacado);
		System.out.println("Salario: "+ salario);
		System.out.println("Detalles vacante: "+ detalles);
		return "vacantes/listVacantes";
	}*/
	
	@GetMapping("/delete")
	public String eliminar(@RequestParam("id") int idVacante, Model model) {
		System.out.println("Borrado vacante con id: "+ idVacante);
		model.addAttribute("id",idVacante);
		return "mensaje";
	}
	
	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") int idVacante,Model model) {
		
		Vacante vacante = serviceVacante.buscarPorId(idVacante);
		System.out.println("Vacante: "+vacante);
		model.addAttribute("vacante",vacante);
		
		//Buscar los detalles de la vacante en la base de datos
		
		return "detalle";
	}
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,false));
	}
	
	
}
