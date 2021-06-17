/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.conexaoBD;
import java.sql.ResultSet;

/**
 *
 * @author patri
 */
public class FormaPagamentoDAO {
    private ResultSet resultado = null;
    
    public String consultar_nome (int a){
        String i = null;
        Object[][] dadosTabela = null;
        int lin = 0; 
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT nome "
                    + "FROM forma_de_pagamento "
                    + "WHERE "
                    + "id_forma_de_pagamento = " + a + "");
            
            while (resultado.next()) {
                String g = (""+resultado.getString("nome"));
                i = g;
            }

            

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }
        return i;
    }
}
