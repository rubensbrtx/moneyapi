package com.algaworks.api.service;

import com.algaworks.api.model.Pessoa;
import com.algaworks.api.repository.PessoaRepository;
import com.algaworks.api.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;


    public List<Pessoa> listar (){
        return pessoaRepository.findAll();
    }

    public Pessoa busrdarPorId(Long id){

        Optional<Pessoa> pessoa = pessoaRepository.findById(id);

        return pessoa.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto nao encontrado"
        ));
    }

    public Pessoa criar (Pessoa p) {
        return  pessoaRepository.save(p);
    }
}
