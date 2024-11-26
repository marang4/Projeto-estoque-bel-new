package com.estoquebel.webservice_api.repositorios;

import com.estoquebel.webservice_api.entidades.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
}
