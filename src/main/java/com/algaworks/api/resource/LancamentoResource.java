package com.algaworks.api.resource;

import com.algaworks.api.dto.LancamentoDTO;
import com.algaworks.api.event.RecursoCriadoEvent;
import com.algaworks.api.model.Lancamento;
import com.algaworks.api.repository.filter.LancamentoFilter;
import com.algaworks.api.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    private LancamentoService lancamentoService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public Page<Lancamento> listarLancamentos(LancamentoFilter lancamentoFilter, Pageable pageable){

        return lancamentoService.pesquisar(lancamentoFilter, pageable);
    }

    @GetMapping(value = "/{codigo}")
    public ResponseEntity<Lancamento> listarLancamentoPorID(@PathVariable Long codigo) {
        Lancamento lancamento = lancamentoService.listarByID(codigo);

        return ResponseEntity.ok().body(lancamento);
    }

    @PostMapping
    public ResponseEntity<Lancamento> cadastrarLancamento(@Valid  @RequestBody LancamentoDTO objDTO, HttpServletResponse response) {

        Lancamento lancamentoSalvo = lancamentoService.criar(objDTO);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));

        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
        lancamentoService.deletar(codigo);
    }
}
