package com.estoquebel.webservice_api.repositorios;

import com.estoquebel.webservice_api.entidades.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
    Usuarios findByEmail(String email);
}
