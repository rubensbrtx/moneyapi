package com.algaworks.api.service;

import com.algaworks.api.dto.LancamentoDTO;
import com.algaworks.api.model.Categoria;
import com.algaworks.api.model.Lancamento;
import com.algaworks.api.model.Pessoa;
import com.algaworks.api.repository.LancamentoRepository;
import com.algaworks.api.repository.filter.LancamentoFilter;
import com.algaworks.api.service.exceptions.BadRequestException;
import com.algaworks.api.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private PessoaService pessoaService;

    public List<Lancamento> listarLancamentos(){
        return lancamentoRepository.findAll();
    }

    public Lancamento listarByID(Long codigo) {
        Optional<Lancamento> lancamento = lancamentoRepository.findById(codigo);
        return lancamento.orElseThrow(() -> new ObjectNotFoundException(
                "Lancamento não encontrado."
        ));
    }

    public Lancamento criar (LancamentoDTO objDTO){

        Lancamento lancamento = fromDto(objDTO);

        if(!lancamento.getPessoa().getAtivo()){
            throw  new BadRequestException(
                    "Não é possivel inserir lançamento para pessoa inativa."
            );
        }

        return lancamentoRepository.save(lancamento);
    }

    public void deletar(Long codigo){
        Lancamento lancamento = listarByID(codigo);
        lancamentoRepository.delete(lancamento);
    }

    public Lancamento fromDto(LancamentoDTO objDTO) {
        Categoria categoria = categoriaService.buscarPorId(objDTO.getIdCategoria());
        Pessoa pessoa = pessoaService.busrdarPorId(objDTO.getIdPessoa());

        Lancamento lancamento = new Lancamento(objDTO.getDescricao(), objDTO.getDataVencimento(), objDTO.getValor(), objDTO.getObservacao(),
                objDTO.getTipo(), categoria, pessoa);

        return lancamento;
    }

    public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable) {
        return lancamentoRepository.filtrar(lancamentoFilter, pageable);
    }
}
