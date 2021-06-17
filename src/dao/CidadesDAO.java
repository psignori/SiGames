/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.IDAOT;
import apoio.conexaoBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import negocios.cidade;

/**
 *
 * @author patri
 */
public class CidadesDAO implements IDAOT <cidade> {
    private ResultSet resultado = null;

    @Override
    public boolean salvar(cidade o) {
        try {
            Statement st = conexaoBD.getInstance().getConnection().createStatement();
            String sql = "";
            if (o.getId_cidade() == 0){
                sql = "INSERT INTO public.cidade (id_cidade, nome, cep, id_estado)"
                        + "VALUES ("
                        + "DEFAULT, "
                        +"'"+ o.getNome() +"'," + ""+ o.getCep()+","+ ""+o.getId_estado()+")";
            }
            

            int resultado = st.executeUpdate(sql);
            
            return true;
        }
        catch (Exception e){
            System.out.println("Erro ao se cadastrar = " + e);
            return false;
        }
    }

    @Override
    public boolean atualizar(cidade o) {
        try {
            Statement st = conexaoBD.getInstance().getConnection().createStatement();
            String sql = "";
            sql = "UPDATE cidade SET nome = "+"'"+ o.getNome()+"'," +"cep = "+""+ o.getCep()+","+" id_estado = "
                  +""+ o.getId_estado()+""+ " WHERE id_cidade = "+""+o.getId_cidade()+"";

          
            st.executeQuery(sql);

            
            return true;
        }
        catch (Exception e){
            System.out.println("Erro ao se cadastrar = " + e);
            return false;
        }
    }
    
    public boolean AtualizarRecebendoDADOS (cidade c){
        boolean a = false;
        Statement st;
         try {
             st = conexaoBD.getInstance().getConnection().createStatement();
             
             String sql = "UPDATE cidade "
                    + "SET nome = '"+c.getNome()+"'"
                    + " WHERE id_cidade = " +"" + c.getId_cidade() + "";

             a = true;
             
            // executa consulta
            st.executeQuery(sql);
            
         } catch (Exception e) {
             System.out.println("Erro ao fazer update em cidade = "+ e );
         }
         try {
             st = conexaoBD.getInstance().getConnection().createStatement();
             
             String sql = "UPDATE cidade "
                    + "SET cep = "+c.getCep()+""
                    + " WHERE id_cidade = " +"" + c.getId_cidade() + "";

             a = true;
             
             
            // executa consulta
            st.executeQuery(sql);
            
         } catch (Exception e) {
             System.out.println("Erro ao fazer update em cidade= "+ e );
         }
         try {
             st = conexaoBD.getInstance().getConnection().createStatement();
             
             String sql = "UPDATE cidade "
                    + "SET id_estado = "+c.getId_estado()+""
                    + " WHERE id_cidade = " +"" + c.getId_cidade() + "";

             a = true;
             
             
            // executa consulta
            st.executeQuery(sql);
            
         } catch (Exception e) {
             System.out.println("Erro ao fazer update em cidade= "+ e );
         }
        return a;
    }
    @Override
    public boolean excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<cidade> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<cidade> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public cidade consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public String consultar_nome (int a){
        String i = null;
        Object[][] dadosTabela = null;
        int lin = 0; 
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT nome "
                    + "FROM cidade "
                    + "WHERE "
                    + "id_cidade = " + a + "");
            
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
                    + "SELECT id_cidade "
                    + "FROM cidade "
                    + "WHERE "
                    + "nome = '" + a + "'");
            
            while (resultado.next()) {
                String g = (""+resultado.getString("id_cidade"));
                i = Integer.parseInt(g);
            }

            

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }
        return i;
    }
    
    public void popularTabela(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[4];
        cabecalho[0] = "Código";
        cabecalho[1] = "Nome";
        cabecalho[2] = "cep";
        cabecalho[3] = "estado";

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) "
                    + "FROM cidade "
                    + "WHERE "
                    + "nome ILIKE '%" + criterio + "%'");

            resultado.next();

            dadosTabela = new Object[resultado.getInt(1)][4];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * "
                    + "FROM cidade "
                    + "WHERE "
                    + "nome ILIKE '%" + criterio + "%'"+"order by nome");
            

            while (resultado.next()) {
                EstadoDAO estado = new EstadoDAO ();

                dadosTabela[lin][0] = resultado.getInt("id_cidade");
                dadosTabela[lin][1] = resultado.getString("nome");
                dadosTabela[lin][2] = resultado.getString("cep");
                dadosTabela[lin][3] = estado.consultar_nome_sigla(resultado.getInt("id_estado"));


                // caso a coluna precise exibir uma imagem
//                if (resultadoQ.getBoolean("Situacao")) {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_ativo.png"));
//                } else {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_inativo.png"));
//                }
                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela cidades...");
            System.out.println(e);
        }

        // configuracoes adicionais no componente tabela
        tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {
            @Override
            // quando retorno for FALSE, a tabela nao é editavel
            public boolean isCellEditable(int row, int column) {
                return false;
                /*  
                 if (column == 3) {  // apenas a coluna 3 sera editavel
                 return true;
                 } else {
                 return false;
                 }
                 */
            }

            // alteracao no metodo que determina a coluna em que o objeto ImageIcon devera aparecer
            @Override
            public Class getColumnClass(int column) {

                if (column == 2) {
//                    return ImageIcon.class;
                }
                return Object.class;
            }
        });

        // permite seleção de apenas uma linha da tabela
        tabela.setSelectionMode(0);

        // redimensiona as colunas de uma tabela
        TableColumn column = null;
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            column = tabela.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(17);
                    break;
                case 1:
                    column.setPreferredWidth(140);
                    break;
//                case 2:
//                    column.setPreferredWidth(14);
//                    break;
            }
        }
    }
}
