package com.estoquebel.webservice_api.controllers;

import com.estoquebel.webservice_api.entidades.Ferramentas;
import com.estoquebel.webservice_api.repositorios.FerramentasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ferramentas")
@CrossOrigin
public class FerramentasController {

    @Autowired
    private FerramentasRepository ferramentasRepository;

    @GetMapping("/listar")
    public ResponseEntity<List<Ferramentas>> listarFerramentas(){
        List<Ferramentas> ferramentasRetorno =
                ferramentasRepository.findAll();

        return ResponseEntity.ok().body(ferramentasRetorno);
    }

    @PostMapping("/criar")
    public ResponseEntity<Ferramentas> criarFerramentas(
            @RequestBody Ferramentas ferramentas
    ){
        Ferramentas retorno = ferramentasRepository.save(ferramentas);

        if (retorno != null){
            return ResponseEntity.ok().body(retorno);
        }

        return ResponseEntity.badRequest().body(null);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Ferramentas> atualizarFerramentas(
            @RequestBody Ferramentas ferramentas,
            @PathVariable Long id
    ){
        Optional<Ferramentas> retorno = ferramentasRepository.findById(id)
                .map(record -> {
                    record.setNome(ferramentas.getNome());

                    return ferramentasRepository.save(record);
                });
        if (retorno.isPresent()){
            return ResponseEntity.ok().body(retorno.get());

        }
        return ResponseEntity.badRequest().body(null);
    }




}
