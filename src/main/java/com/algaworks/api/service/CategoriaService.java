package com.algaworks.api.service;

import com.algaworks.api.model.Categoria;
import com.algaworks.api.repository.CategoriaRepository;
import com.algaworks.api.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List <Categoria> listar () {

        return categoriaRepository.findAll();
    }

    public Categoria buscarPorId (Long id) {

        Optional <Categoria> categoria = categoriaRepository.findById(id);

        return categoria.orElseThrow(() -> new ObjectNotFoundException("Categoria nao encontrada."));
    }

    public Categoria criar (Categoria categoria){
        return categoriaRepository.save(categoria);
    }


}
