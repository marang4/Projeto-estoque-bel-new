package com.estoquebel.webservice_api.controllers;

import com.estoquebel.webservice_api.entidades.Usuarios;
import com.estoquebel.webservice_api.repositorios.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
@CrossOrigin
public class UsuariosController {


    @Autowired
    private UsuariosRepository usuariosRepository;

    @PostMapping("/login")
    public ResponseEntity<Usuarios> login(@RequestBody Usuarios usuarios) {
        Usuarios retorno = usuariosRepository.findByEmail(usuarios.getEmail());
        if (retorno != null) {
            if (retorno.getSenha().equals(usuarios.getSenha())) {
                return  ResponseEntity.ok().body(retorno);
            }
        }
        return ResponseEntity.badRequest().body(null);
    }

}
