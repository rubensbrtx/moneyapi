package com.algaworks.api.resource;

import com.algaworks.api.model.Pessoa;
import com.algaworks.api.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<Pessoa> insert(@Valid @RequestBody Pessoa obj,  HttpServletResponse response){
        Pessoa pessoa = pessoaService.criar(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
                .buildAndExpand(obj.getCodigo()).toUri();

        return ResponseEntity.created(uri).body(pessoa);
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> listar (){

        List<Pessoa> lista = pessoaService.listar();

        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/{codigo}")
    public ResponseEntity<Pessoa> buscarPorID (@PathVariable Long codigo){

        Pessoa pessoa = pessoaService.busrdarPorId(codigo);

        return ResponseEntity.ok().body(pessoa);
    }


}
