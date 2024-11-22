package com.estoquebel.webservice_api.repositorios;

import com.estoquebel.webservice_api.entidades.Ferramentas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FerramentasRepository extends JpaRepository<Ferramentas, Long> {
}
