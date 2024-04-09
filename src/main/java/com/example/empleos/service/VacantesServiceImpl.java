package com.example.empleos.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.empleos.model.Vacante;

@Service
public class VacantesServiceImpl implements IVacanteService{
	
	private List<Vacante> lista = null;
	
	public VacantesServiceImpl() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
		lista = new LinkedList<Vacante>();
		try {
			Vacante vacante = new Vacante();
			vacante.setId(1);
			vacante.setNombre("Ingeniero de comunicaciones");
			vacante.setDescripcion("Se solicita ingeniero para dar soporte a intranet");
			vacante.setFecha(sdf.parse("09-02-2024"));
			vacante.setSalario(14000.0);
			vacante.setDestacado(1);
			vacante.setLogotipo("empresa1.png");
			
			
			Vacante vacante2 = new Vacante();
			vacante2.setId(2);
			vacante2.setNombre("Contador Publico");
			vacante2.setDescripcion("Empresa solicita contador publico con 5 años de experiencia");
			vacante2.setFecha(sdf.parse("05-01-2024"));
			vacante2.setSalario(12000.0);
			vacante2.setDestacado(0);
			vacante2.setLogotipo("empresa2.png");
			
			Vacante vacante3 = new Vacante();
			vacante3.setId(3);
			vacante3.setNombre("Ingeniero Electrico");
			vacante3.setDescripcion("Empresa internacional solicita ingeniero para mantenimiento de la instalacion electrica");
			vacante3.setFecha(sdf.parse("30-12-2023"));
			vacante3.setSalario(10500.0);
			vacante3.setDestacado(0);
			
			
			
			Vacante vacante4 = new Vacante();
			vacante4.setId(4);
			vacante4.setNombre("Diseñador grafico");
			vacante4.setDescripcion("Se busca diseñador grafico titulado para la publicidad de la empresa");
			vacante4.setFecha(sdf.parse("23-11-2023"));
			vacante4.setSalario(7500.0);
			vacante4.setDestacado(1);
			vacante4.setLogotipo("empresa3.png");
			
			lista.add(vacante);
			lista.add(vacante2);
			lista.add(vacante3);
			lista.add(vacante4);
			
			
			
		}catch(ParseException e){
			System.out.println("Error:  "+e.getMessage());
		}
	}

	@Override
	public List<Vacante> buscarTodas() {
		
		return lista;
	}

	@Override
	public Vacante buscarPorId(Integer idVacante) {
		
		for(Vacante v :lista) {
			if(v.getId()==idVacante) {
				return v;
			}
		}
	
		return null;
	}

	@Override
	public void guardar(Vacante vacante) {
		lista.add(vacante);
		
	}

}
