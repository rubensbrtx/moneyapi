package com.algaworks.api.resource;

import com.algaworks.api.event.RecursoCriadoEvent;
import com.algaworks.api.model.Pessoa;
import com.algaworks.api.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
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
    private ApplicationEventPublisher publisher;

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<Pessoa> insert(@Valid @RequestBody Pessoa obj,  HttpServletResponse response){
        Pessoa pessoa = pessoaService.criar(obj);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoa.getCodigo()));

        return ResponseEntity.status(HttpStatus.CREATED).body(pessoa);
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


    @DeleteMapping(value = "/{codigo}")
    public ResponseEntity<?> delete(@PathVariable Long codigo) {

        pessoaService.deletar(codigo);

        return ResponseEntity.noContent().build();

    }

    @PutMapping
    public ResponseEntity<Pessoa> atualizar(@Valid @RequestBody Pessoa pessoa){

        Pessoa p = pessoaService.atualizar(pessoa);

        return ResponseEntity.ok().body(p);
    }

}
