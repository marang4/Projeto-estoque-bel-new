package com.estoquebel.webservice_api.controllers;

import com.estoquebel.webservice_api.entidades.Ferramentas;
import com.estoquebel.webservice_api.entidades.Movimentacao;
import com.estoquebel.webservice_api.repositorios.FerramentasRepository;
import com.estoquebel.webservice_api.repositorios.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoController {

    @Autowired
    private FerramentasRepository ferramentaRepository;

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;


    @PostMapping("/entrada")
    public String registrarEntrada(@RequestBody Movimentacao request) {

        Ferramentas ferramenta = ferramentaRepository.findByCodBarras(request.getCodBarras());


        if (ferramenta == null) {
            return "Ferramenta não encontrada!";
        }


        ferramenta.setEstoque(ferramenta.getEstoque() + request.getQuantidade());
        ferramentaRepository.save(ferramenta);


        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setTipo("ENTRADA");
        movimentacao.setQuantidade(request.getQuantidade());
        movimentacao.setFerramenta(ferramenta);
        movimentacao.setCodBarras(request.getCodBarras());

        movimentacaoRepository.save(movimentacao);

        return "Entrada registrada com sucesso!";
    }


    @PostMapping("/saida")
    public String registrarSaida(@RequestBody Movimentacao request) {

        Ferramentas ferramenta = ferramentaRepository.findByCodBarras(request.getCodBarras());

        if (ferramenta == null) {
            return "Ferramenta não encontrada!";
        }

        if (ferramenta.getEstoque() < request.getQuantidade()) {
            return "Quantidade insuficiente!";
        }

        ferramenta.setEstoque(ferramenta.getEstoque() - request.getQuantidade());
        ferramentaRepository.save(ferramenta);

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setTipo("SAIDA");
        movimentacao.setQuantidade(request.getQuantidade());
        movimentacao.setFerramenta(ferramenta);
        movimentacao.setCodBarras(request.getCodBarras());

        movimentacaoRepository.save(movimentacao);

        return "Saída registrada com sucesso!";
    }


   @GetMapping("/listmov/{codBarras}")
    public ResponseEntity<List<Movimentacao>> listarFerramentas(){
        List<Movimentacao> movimentacaoRetorno =
               movimentacaoRepository.findAll();

        return ResponseEntity.ok().body(movimentacaoRetorno);
    }
}
