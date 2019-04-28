package com.algaworks.api.resource;

import com.algaworks.api.model.Categoria;
import com.algaworks.api.repository.CategoriaRepository;
import com.algaworks.api.service.CategoriaService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;
    @GetMapping
    public ResponseEntity<List<Categoria>> listar () {

        List <Categoria> categorias = categoriaService.listar();

        return ResponseEntity.ok().body(categorias);
    }

    @PostMapping
    public ResponseEntity<Categoria> criar (@Valid @RequestBody Categoria categoria, HttpServletResponse response) {

        Categoria categoriaSalva = categoriaService.criar(categoria);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
                .buildAndExpand(categoria.getCodigo()).toUri();

        return ResponseEntity.created(uri).body(categoriaSalva);
    }

    @GetMapping(value = "/{codigo}")
    public ResponseEntity<Categoria> buscarPorId (@PathVariable Long codigo) {

        Categoria categoria = categoriaService.buscarPorId(codigo);

        return ResponseEntity.ok().body(categoria);
    }
}
