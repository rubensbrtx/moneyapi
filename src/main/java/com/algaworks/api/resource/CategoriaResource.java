package com.algaworks.api.resource;

import com.algaworks.api.event.RecursoCriadoEvent;
import com.algaworks.api.model.Categoria;
import com.algaworks.api.repository.CategoriaRepository;
import com.algaworks.api.service.CategoriaService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public ResponseEntity<List<Categoria>> listar () {

        List <Categoria> categorias = categoriaService.listar();

        return ResponseEntity.ok().body(categorias);
    }

    @PostMapping
    public ResponseEntity<Categoria> criar (@Valid @RequestBody Categoria categoria, HttpServletResponse response) {

        Categoria categoriaSalva = categoriaService.criar(categoria);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));

        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }

    @GetMapping(value = "/{codigo}")
    public ResponseEntity<Categoria> buscarPorId (@PathVariable Long codigo) {

        Categoria categoria = categoriaService.buscarPorId(codigo);

        return ResponseEntity.ok().body(categoria);
    }
}
