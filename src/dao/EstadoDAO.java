/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.conexaoBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import negocios.estado;

/**
 *
 * @author patri
 */
public class EstadoDAO {
    
    private ResultSet resultado = null;
    
    
    public boolean salvar(estado es) {
        try {
            Statement st = conexaoBD.getInstance().getConnection().createStatement();
            String sql = "";
            if (es.getId_estado() == 0){
                sql = "INSERT INTO public.estado (id_estado, nome, sigla)"
                        + "VALUES ("
                        + "DEFAULT, "
                        +"'"+ es.getNome() +"'," + "'"+ es.getSigla() +"')";
            }
            

            int resultado = st.executeUpdate(sql);
            
            return true;
        }
        catch (Exception e){
            System.out.println("Erro ao se cadastrar = " + e);
            return false;
        }
    }
    
    public String consultar_nome_sigla (int a){
        String i = null;
        Object[][] dadosTabela = null;
        int lin = 0; 
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT sigla "
                    + "FROM estado "
                    + "WHERE "
                    + "id_estado = " + a + "");
            
            while (resultado.next()) {
                String g = (""+resultado.getString("sigla"));
                i = g;
            }

            

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }
        return i;
    }
    public String consultar_nome (int a){
        String i = null;
        Object[][] dadosTabela = null;
        int lin = 0; 
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT nome "
                    + "FROM estado "
                    + "WHERE "
                    + "id_estado = " + a + "");
            
            while (resultado.next()) {
                String g = (""+resultado.getString("nome"));
                i = g;
            }

            

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }
        return i;
    }
    public int consultar_id (String a){
        int i = 0;
        Object[][] dadosTabela = null;
        int lin = 0; 
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT id_estado "
                    + "FROM estado "
                    + "WHERE "
                    + "sigla = '" + a + "'");
            
            while (resultado.next()) {
                String g = (""+resultado.getString("id_estado"));
                i = Integer.parseInt(g);
            }

            

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }
        return i;
    }
    public String consultar_nome_passando_sigla (String a){
        String i = null;
        Object[][] dadosTabela = null;
        int lin = 0; 
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT nome "
                    + "FROM estado "
                    + "WHERE "
                    + "sigla = '" + a + "'");
            
            while (resultado.next()) {
                i = (""+resultado.getString("nome"));
            }

            

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }
        return i;
    }
}
