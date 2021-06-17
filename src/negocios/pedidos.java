/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocios;
import java.util.*;

/**
 *
 * @author patri
 */
public class pedidos {
    private int id_pedido;
    private double valor_total;
    private  Date compra = new Date ();
    private String situacao;
    private int id_pessoa;
    private int id_forma_pagamento;
    private int id_game;
    private int quantidade_games;
    
    private Date DataIni = new Date ();
    private Date DataFim = new Date ();
    private String nomeJogo;
    private String nomePessoa;
    private int nomePagamento;

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public int getNomePagamento() {
        return nomePagamento;
    }

    public void setNomePagamento(int nomePagamento) {
        this.nomePagamento = nomePagamento;
    }

    public String getNomeJogo() {
        return nomeJogo;
    }

    public void setNomeJogo(String nomeJogo) {
        this.nomeJogo = nomeJogo;
    }

    public Date getDataIni() {
        return DataIni;
    }

    public void setDataIni(Date DataIni) {
        this.DataIni = DataIni;
    }

    public Date getDataFim() {
        return DataFim;
    }

    public void setDataFim(Date DataFim) {
        this.DataFim = DataFim;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public double getValor_total() {
        return valor_total;
    }

    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }

    public Date getCompra() {
        return compra;
    }

    public void setCompra(Date compra) {
        this.compra = compra;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public int getId_pessoa() {
        return id_pessoa;
    }

    public void setId_pessoa(int id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    public int getId_forma_pagamento() {
        return id_forma_pagamento;
    }

    public void setId_forma_pagamento(int id_forma_pagamento) {
        this.id_forma_pagamento = id_forma_pagamento;
    }

    public int getId_game() {
        return id_game;
    }

    public void setId_game(int id_game) {
        this.id_game = id_game;
    }

    public int getQuantidade_games() {
        return quantidade_games;
    }

    public void setQuantidade_games(int quantidade_games) {
        this.quantidade_games = quantidade_games;
    }
}
