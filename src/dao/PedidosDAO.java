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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import negocios.pedidos;

/**
 *
 * @author patri
 */
public class PedidosDAO implements IDAOT <pedidos> {
    
    private ResultSet resultado = null;

    @Override
    public boolean salvar(pedidos o) {
        try {
            Statement st = conexaoBD.getInstance().getConnection().createStatement();
            String sql = "";
            if (o.getId_pedido()== 0){
                sql = "INSERT INTO public.pedido ( valor_total, data, situacao, id_pessoa, id_forma_de_pagamento, id_game, quantidade_games)"
                        + "VALUES ("
                        +"'"+ o.getValor_total() +"', " + "'"+ o.getCompra() +"', "+ "'"+ o.getSituacao()+"', " + ""+ o.getId_pessoa() +", "+ "" + o.getId_forma_pagamento()+ ", "+ o.getId_game()+", "+ ""+ o.getQuantidade_games() +")";
            }
            
            int resultado = st.executeUpdate(sql);
            
            return true;
        }
        catch (Exception e){
            System.out.println("Erro ao realizar a compra = " + e);
            return false;
        }
    }
    public boolean ativar(int id) {
        boolean a = false;
        Statement st;
         try {
             st = conexaoBD.getInstance().getConnection().createStatement();
             
             String sql = "UPDATE pedido "
                    + "SET situacao = 'A' "
                    + "WHERE id_pedido = " +"" +id + "";


            // executa consulta
            st.executeQuery(sql);
            a = true;
         } catch (Exception e) {
         }
        return a;
    }

    @Override
    public boolean atualizar(pedidos o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluir(int id) {
        boolean a = false;
        Statement st;
        
        try {
            st = conexaoBD.getInstance().getConnection().createStatement();
             
             String sql = "UPDATE pedido "
                    + "SET situacao = 'C'"
                    + "WHERE id_pedido = " +"" +id + "";


            // executa consulta
            st.executeQuery(sql);
            

        }
        catch (Exception e) {
            a = false;
        }
        return a;
    }

    @Override
    public ArrayList<pedidos> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<pedidos> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public pedidos consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void popular_tabela_por_pesquisa (JTable tabela, int criterio){
       // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[8];
        cabecalho[0] = "id";
        cabecalho[1] = "Game";
        cabecalho[2] = "Quantidade";
        cabecalho[3] = "Valor";
        cabecalho[4] = "Data";
        cabecalho[5] = "Situação";
        cabecalho[6] = "Pessoa";
        cabecalho[7] = "Forma de Pagamento";


        // cria matriz de acordo com nº de registros da tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) "
                    + "FROM pedido "
                    + "WHERE id_pedido =" + criterio);

            resultado.next();

            dadosTabela = new Object[resultado.getInt(1)][8];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * "
                    + "FROM pedido "
                    + "WHERE id_pedido =" + criterio);
            
            DateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
            while (resultado.next()) {

                GamesDAO games = new GamesDAO ();
                PessoasDAO pessoas = new PessoasDAO ();
                FormaPagamentoDAO forma = new FormaPagamentoDAO();

                dadosTabela[lin][0] = resultado.getInt("id_pedido");
                dadosTabela[lin][1] = games.consultar_nome(resultado.getInt("id_game"));
                dadosTabela[lin][2] = resultado.getInt("quantidade_games");
                dadosTabela[lin][3] = resultado.getDouble("valor_total");
                dadosTabela[lin][4] = formatDate.format(resultado.getDate("data"));
                dadosTabela[lin][5] = resultado.getString("situacao");
                dadosTabela[lin][6] = pessoas.consultar_nome(resultado.getInt("id_pessoa"));
                dadosTabela[lin][7] = forma.consultar_nome(resultado.getInt("id_forma_de_pagamento"));


                // caso a coluna precise exibir uma imagem
//                if (resultadoQ.getBoolean("Situacao")) {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_ativo.png"));
//                } else {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_inativo.png"));
//                }
                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela pedidos...");
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
                    column.setPreferredWidth(8);
                    break;
                case 1:
                    column.setPreferredWidth(100);
                    break;
                case 2:
                    column.setPreferredWidth(8);
                    break;
                case 3:
                    column.setPreferredWidth(12);
                    break;
                case 4:
                    column.setPreferredWidth(18);
                    break;
                case 5:
                    column.setPreferredWidth(8);
                    break;    
            }
        }
    }
    
    public void popular_tabela_tela_principal (JTable tabela, int criterio){
       // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[8];
        cabecalho[0] = "id";
        cabecalho[1] = "Game";
        cabecalho[2] = "Quantidade";
        cabecalho[3] = "Valor";
        cabecalho[4] = "Data";
        cabecalho[5] = "Situação";
        cabecalho[6] = "Pessoa";
        cabecalho[7] = "Forma de Pagamento";


        // cria matriz de acordo com nº de registros da tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) "
                    + "FROM pedido "
                    + "WHERE id_pessoa =" + criterio);

            resultado.next();

            dadosTabela = new Object[resultado.getInt(1)][8];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * "
                    + "FROM pedido "
                    + "WHERE id_pessoa =" + criterio);
            
            DateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
            while (resultado.next()) {

                GamesDAO games = new GamesDAO ();
                PessoasDAO pessoas = new PessoasDAO ();
                FormaPagamentoDAO forma = new FormaPagamentoDAO();

                dadosTabela[lin][0] = resultado.getInt("id_pedido");
                dadosTabela[lin][1] = games.consultar_nome(resultado.getInt("id_game"));
                dadosTabela[lin][2] = resultado.getInt("quantidade_games");
                dadosTabela[lin][3] = resultado.getDouble("valor_total");
                dadosTabela[lin][4] = formatDate.format(resultado.getDate("data"));
                dadosTabela[lin][5] = resultado.getString("situacao");
                dadosTabela[lin][6] = pessoas.consultar_nome(resultado.getInt("id_pessoa"));
                dadosTabela[lin][7] = forma.consultar_nome(resultado.getInt("id_forma_de_pagamento"));


                // caso a coluna precise exibir uma imagem
//                if (resultadoQ.getBoolean("Situacao")) {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_ativo.png"));
//                } else {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_inativo.png"));
//                }
                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela pedidos...");
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
                    column.setPreferredWidth(8);
                    break;
                case 1:
                    column.setPreferredWidth(100);
                    break;
                case 2:
                    column.setPreferredWidth(8);
                    break;
                case 3:
                    column.setPreferredWidth(12);
                    break;
                case 4:
                    column.setPreferredWidth(18);
                    break;
                case 5:
                    column.setPreferredWidth(8);
                    break;    
            }
        }
    }
    
    public void popularTabela(JTable tabela) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[8];
        cabecalho[0] = "id";
        cabecalho[1] = "Game";
        cabecalho[2] = "Quantidade";
        cabecalho[3] = "Valor";
        cabecalho[4] = "Data";
        cabecalho[5] = "Situação";
        cabecalho[6] = "Pessoa";
        cabecalho[7] = "Forma de Pagamento";


        // cria matriz de acordo com nº de registros da tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) "
                    + "FROM pedido ");

            resultado.next();

            dadosTabela = new Object[resultado.getInt(1)][8];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * "
                    + "FROM pedido "
                    + "ORDER BY situacao,id_pedido ");
            
            DateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
            while (resultado.next()) {
                GamesDAO games = new GamesDAO ();
                PessoasDAO pessoas = new PessoasDAO ();
                FormaPagamentoDAO forma = new FormaPagamentoDAO();

                dadosTabela[lin][0] = resultado.getInt("id_pedido");
                dadosTabela[lin][1] = games.consultar_nome(resultado.getInt("id_game"));
                dadosTabela[lin][2] = resultado.getInt("quantidade_games");
                dadosTabela[lin][3] = resultado.getDouble("valor_total");
                dadosTabela[lin][4] = formatDate.format(resultado.getDate("data"));
                dadosTabela[lin][5] = resultado.getString("situacao");
                dadosTabela[lin][6] = pessoas.consultar_nome(resultado.getInt("id_pessoa"));
                dadosTabela[lin][7] = forma.consultar_nome(resultado.getInt("id_forma_de_pagamento"));


                // caso a coluna precise exibir uma imagem
//                if (resultadoQ.getBoolean("Situacao")) {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_ativo.png"));
//                } else {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_inativo.png"));
//                }
                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela pedidos...");
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
                    column.setPreferredWidth(8);
                    break;
                case 1:
                    column.setPreferredWidth(100);
                    break;
                case 2:
                    column.setPreferredWidth(8);
                    break;
                case 3:
                    column.setPreferredWidth(12);
                    break;
                case 4:
                    column.setPreferredWidth(18);
                    break;
                case 5:
                    column.setPreferredWidth(8);
                    break;    
            }
        }
    }
}
