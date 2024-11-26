package com.estoquebel.webservice_api.entidades;

import jakarta.persistence.*;

@Entity
public class Movimentacao {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String tipo;

    @Column
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "ferramenta_id")
    private Ferramentas ferramenta;

    @Column
    private Long codBarras;

    public Long getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(Long codBarras) {
        this.codBarras = codBarras;
    }

    public Ferramentas getFerramenta() {
        return ferramenta;
    }

    public void setFerramenta(Ferramentas ferramenta) {
        this.ferramenta = ferramenta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
