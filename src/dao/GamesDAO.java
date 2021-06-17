/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.IDAOT;
import apoio.conexaoBD;
import apoio.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import negocios.games;

/**
 *
 * @author patri
 */
public class GamesDAO implements IDAOT <games> {
    
    private ResultSet resultado = null;

    @Override
    public boolean salvar(games o) {
        try {
            Statement st = conexaoBD.getInstance().getConnection().createStatement();
            String sql = "";
            if (o.getId_game()== 0){
                sql = "INSERT INTO public.game (id_game, nome, descricao, valor, situacao, id_genero, )"
                        + "VALUES ("
                        + "DEFAULT, "
                        +"'"+ o.getNome() +"'," + "'"+ o.getDescricao() +"',"+ "'"+ o.getValor()+"'," + "'"+ o.getSituacao() +"',"+ "" + o.getId_genero() +")";
            }
            

            int resultado = st.executeUpdate(sql);
            
            return true;
        }
        catch (Exception e){
            System.out.println("Erro ao se cadastrar = " + e);
            return false;
        }
    }
    
    public boolean salvarImagem(games o) {
        boolean retorno = false;
        /*String sql = "UPDATE game "
                    + "SET imagem = ?"
                    + "WHERE id_game = " + o.getId_game() + "";*/
        
        String  sql = "INSERT INTO public.game (id_game, nome, descricao, valor, situacao, id_genero, imagem)"
                        + "VALUES ("
                        + "DEFAULT, "
                        +"'"+ o.getNome() +"'," + "'"+ o.getDescricao() +"',"+ "'"+ o.getValor()+"'," + "'"+ o.getSituacao() +"',"+ "" + o.getId_genero() +", ?)";
        
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
            pst.setBytes(1, o.getImagem());
             pst.executeUpdate();
             retorno = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return retorno;
    }
    
    public games buscar(Integer id)
    {
        games retorno = null;
        String sql = "SELECT imagem from game where id_game =?";
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        
        try {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                retorno = new games();
                retorno.setImagem(rs.getBytes("imagem"));
            
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            retorno = null;
        }
        
        return retorno;
    
    }

    @Override
    public boolean atualizar(games o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluir(int id) {
        boolean a = false;
        Statement st;
        
        try {
            st = conexaoBD.getInstance().getConnection().createStatement();
             
             String sql = "UPDATE game "
                    + "SET situacao = 'I'"
                    + "WHERE id_game = " +"" +id + "";


            // executa consulta
            st.executeQuery(sql);
            

        }
        catch (Exception e) {
            a = false;
        }
        return a;
    }
    

    @Override
    public ArrayList<games> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<games> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public games consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public int consultar_id (String a){
        int i = 0;
        Object[][] dadosTabela = null;
        int lin = 0; 
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT id_game "
                    + "FROM game "
                    + "WHERE "
                    + "nome ILIKE '%" + a + "%'");
            
            while (resultado.next()) {
                String g = (""+resultado.getString("id_game"));
                i = Integer.parseInt(g);
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
                    + "FROM game "
                    + "WHERE "
                    + "id_game = " + a + "");
            
            while (resultado.next()) {
                String g = (""+resultado.getString("nome"));
                i = g;
            }

            

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }
        return i;
    }
    
    
    
    public boolean ativar(int id) {
        boolean a = false;
        Statement st;
         try {
             st = conexaoBD.getInstance().getConnection().createStatement();
             
             String sql = "UPDATE game "
                    + "SET situacao = 'D' "
                    + "WHERE id_game = " +"" +id + "";


            // executa consulta
            st.executeQuery(sql);
            a = true;
         } catch (Exception e) {
         }
        return a;
    }
    
    
    public boolean atualizarCadastro(games g) {
        boolean a = false;
        Statement st;
         try {
             st = conexaoBD.getInstance().getConnection().createStatement();
             
             String sql = "UPDATE game "
                    + "SET nome = "+"'"+ g.getNome() + "'"
                    + " WHERE id_game = " +"" +g.getId_game() + "";
             a = true;

            // executa consulta
            st.executeQuery(sql);
            
            
         } catch (Exception e) {
         }
         try {
             st = conexaoBD.getInstance().getConnection().createStatement();
             
             String sql = "UPDATE game "
                    + "SET descricao = "+"'"+ g.getDescricao() + "'"
                    + " WHERE id_game = " +"" +g.getId_game() + "";

             a = true;
            // executa consulta
            st.executeQuery(sql);
            
            
         } catch (Exception e) {
         }
         try {
             st = conexaoBD.getInstance().getConnection().createStatement();
             
             String sql = "UPDATE game "
                    + "SET valor = "+""+ g.getValor() + ""
                    + " WHERE id_game = " +"" + g.getId_game() + "";

             a = true;
            // executa consulta
            st.executeQuery(sql);
            
            
         } catch (Exception e) {
         }
         try {
             st = conexaoBD.getInstance().getConnection().createStatement();
             
             String sql = "UPDATE game "
                    + "SET id_genero = "+""+ g.getId_genero() + ""
                    + " WHERE id_game = " +"" + g.getId_game() + "";

             a = true;
            // executa consulta
            st.executeQuery(sql);
            
            
         } catch (Exception e) {
         }
        return a;
    }
    public void popularTabela_tela_principal(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[4];
        cabecalho[0] = "Nome";
        cabecalho[1] = "Descrição";
        cabecalho[2] = "Categoria";
        cabecalho[3] = "Valor";


        // cria matriz de acordo com nº de registros da tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) "
                    + "FROM game "
                    + "WHERE situacao = 'D' and "
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
                    + "FROM game "
                    + "WHERE situacao = 'D' and "
                    + "nome ILIKE'%" + criterio + "%'" + " ORDER BY nome ");
            

            while (resultado.next()) {
                GeneroDAO genero = new GeneroDAO ();

                dadosTabela[lin][0] = resultado.getString("nome");
                dadosTabela[lin][1] = resultado.getString("descricao");
                dadosTabela[lin][2] = genero.consultar_nome(resultado.getInt("id_genero"));
                dadosTabela[lin][3] = resultado.getString("valor");
                


                // caso a coluna precise exibir uma imagem
//                if (resultadoQ.getBoolean("Situacao")) {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_ativo.png"));
//                } else {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_inativo.png"));
//                }
                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela games...");
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
                    column.setPreferredWidth(350);
                    break;
                case 1:
                    column.setPreferredWidth(450);
                    break;
//                case 2:
//                    column.setPreferredWidth(14);
//                    break;
            }
        }
    }
    
    public void popularTabela(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[6];
        cabecalho[0] = "Código";
        cabecalho[1] = "Nome";
        cabecalho[2] = "descrição";
        cabecalho[3] = "valor";
        cabecalho[4] = "situação";
        cabecalho[5] = "categoria";


        // cria matriz de acordo com nº de registros da tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) "
                    + "FROM game "
                    + "WHERE "
                    + "nome ILIKE '%" + criterio + "%'");

            resultado.next();

            dadosTabela = new Object[resultado.getInt(1)][6];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * "
                    + "FROM game "
                    + "WHERE "
                    + "nome ILIKE '%" + criterio + "%'" + "ORDER BY situacao,nome ");
            

            while (resultado.next()) {
                GeneroDAO genero = new GeneroDAO ();

                dadosTabela[lin][0] = resultado.getInt("id_game");
                dadosTabela[lin][1] = resultado.getString("nome");
                dadosTabela[lin][2] = resultado.getString("descricao");
                dadosTabela[lin][3] = resultado.getString("valor");
                dadosTabela[lin][4] = resultado.getString("situacao");
                dadosTabela[lin][5] = genero.consultar_nome(resultado.getInt("id_genero"));


                // caso a coluna precise exibir uma imagem
//                if (resultadoQ.getBoolean("Situacao")) {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_ativo.png"));
//                } else {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_inativo.png"));
//                }
                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela games...");
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
        // renderizacao das linhas da tabela = mudar a cor
        /*tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected,
                        hasFocus, row, column);
                if (row % 2 == 0) {
                    setBackground(Color.GREEN);
                } else {
                    setBackground(Color.LIGHT_GRAY);
                }
                return this;
            }
        });*/
    }
}
