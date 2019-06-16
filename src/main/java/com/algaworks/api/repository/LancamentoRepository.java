package com.algaworks.api.repository;

import com.algaworks.api.model.Lancamento;
import com.algaworks.api.repository.lancamento.LancamentoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> , LancamentoRepositoryQuery {


}
