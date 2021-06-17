/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.IDAOT;
import apoio.conexaoBD;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import negocios.estoque;

/**
 *
 * @author patri
 */
public class EstoqueDAO implements IDAOT <estoque> {
    private ResultSet resultado = null;

    @Override
    public boolean salvar(estoque o) {
        try {
            Statement st = conexaoBD.getInstance().getConnection().createStatement();
            String sql = "";
            if (o.getId_estoque()== 0){
                sql = "INSERT INTO public.estoque ( id_game, quantidade)"
                        + "VALUES ("
                        +""+ o.getId_jogos() +", " +" 0 )";
            }
            
            int resultado = st.executeUpdate(sql);
            
            return true;
        }
        catch (Exception e){
            System.out.println("Erro ao adicionar estoque = " + e);
            return false;
        }
    }

    @Override
    public boolean atualizar(estoque o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<estoque> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<estoque> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public estoque consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean atualizar(int id_game, int quantidade) {
        boolean a = false;
        Statement st;
         try {
             st = conexaoBD.getInstance().getConnection().createStatement();
             
             String sql = "UPDATE estoque "
                    + "SET quantidade =  " + quantidade
                    + "WHERE id_game = " + id_game ;


            // executa consulta
            st.executeQuery(sql);
            a = true;
         } catch (Exception e) {
         }
        return a;
    }
    
    public int consultar_quantidade (int j){
        int i = 0;
        Object[][] dadosTabela = null;
        int lin = 0; 
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT quantidade "
                    + "FROM estoque "
                    + "WHERE "
                    + "id_game = " + j + "");
            
            while (resultado.next()) {
                String g = (""+resultado.getString("quantidade"));
                i = Integer.parseInt(g);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }
        return i;
    }
    
    public void popularTabela(JTable tabela) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[3];
        cabecalho[0] = "Código";
        cabecalho[1] = "nome_game";
        cabecalho[2] = "Quantidade";


        // cria matriz de acordo com nº de registros da tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) "
                    + "FROM estoque ");

            resultado.next();

            dadosTabela = new Object[resultado.getInt(1)][3];

        } catch (Exception e) {
            System.out.println("problemas para popular tabela estoque...");
            System.out.println(e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * "
                    + "FROM estoque ");
            

            while (resultado.next()) {
                GamesDAO game = new GamesDAO ();

                dadosTabela[lin][0] = resultado.getInt("id_estoque");
                dadosTabela[lin][1] = game.consultar_nome(resultado.getInt("id_game"));
                dadosTabela[lin][2] = resultado.getInt("quantidade");


                // caso a coluna precise exibir uma imagem
//                if (resultadoQ.getBoolean("Situacao")) {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_ativo.png"));
//                } else {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_inativo.png"));
//                }
                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela estoque...");
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
        
    }
    public void popularTabela_por_pesquisa(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[3];
        cabecalho[0] = "Código";
        cabecalho[1] = "nome_game";
        cabecalho[2] = "Quantidade";


        // cria matriz de acordo com nº de registros da tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) "
                    + "FROM estoque "
                    + "WHERE "
                    + "id_game = " + criterio + "");

            resultado.next();

            dadosTabela = new Object[resultado.getInt(1)][3];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * "
                    + "FROM estoque "
                    + "WHERE "
                    + "id_game = " + criterio + "");
            

            while (resultado.next()) {
                GamesDAO game = new GamesDAO ();

                dadosTabela[lin][0] = resultado.getInt("id_estoque");
                dadosTabela[lin][1] = game.consultar_nome(resultado.getInt("id_game"));
                dadosTabela[lin][2] = resultado.getInt("quantidade");


                // caso a coluna precise exibir uma imagem
//                if (resultadoQ.getBoolean("Situacao")) {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_ativo.png"));
//                } else {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_inativo.png"));
//                }
                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela estoque...");
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
