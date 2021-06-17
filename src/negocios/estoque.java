/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocios;

/**
 *
 * @author patri
 */
public class estoque {
    private int id_estoque;
    private int id_jogos;
    private int quantidade;

    public int getId_estoque() {
        return id_estoque;
    }

    public void setId_estoque(int id_estoque) {
        this.id_estoque = id_estoque;
    }

    public int getId_jogos() {
        return id_jogos;
    }

    public void setId_jogos(int id_jogos) {
        this.id_jogos = id_jogos;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
