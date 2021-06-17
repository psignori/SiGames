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
public class FormaPagamento {
    
    private int id_forma_pagamento ;
    private String nome;

    public int getId_forma_pagamento() {
        return id_forma_pagamento;
    }

    public void setId_forma_pagamento(int id_forma_pagamento) {
        this.id_forma_pagamento = id_forma_pagamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }   
}
