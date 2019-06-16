package com.algaworks.api.service;

import com.algaworks.api.model.Pessoa;
import com.algaworks.api.repository.PessoaRepository;
import com.algaworks.api.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
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
                "Pessoa nao encontrada"
        ));
    }

    public Pessoa criar (Pessoa p) {
        return  pessoaRepository.save(p);
    }

    public void deletar(Long codigo){
        Pessoa pessoa = busrdarPorId(codigo);
        pessoaRepository.delete(pessoa);
    }

    public Pessoa atualizar(Pessoa p) {
        Pessoa pessoa = busrdarPorId(p.getCodigo());

        BeanUtils.copyProperties(p, pessoa, "codigo");

        return pessoaRepository.save(pessoa);
    }

    public void atualizarStatusAtivo(Long codigo, Boolean ativo){
        Pessoa pessoa = busrdarPorId(codigo);
        pessoa.setAtivo(ativo);
        pessoaRepository.save(pessoa);
    }
}
