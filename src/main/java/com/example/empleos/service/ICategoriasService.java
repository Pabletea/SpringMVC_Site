package com.example.empleos.service;

import java.util.List;

import com.example.empleos.model.Categoria;


public interface ICategoriasService {

	List<Categoria> buscarTodas();
	Categoria buscarPorId(Integer idCategoria);
	void guardar(Categoria categoria);
}

