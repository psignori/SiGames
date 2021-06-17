/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.conexaoBD;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import negocios.pedidos;

/**
 *
 * @author patri
 */
public class PedidosVideoDAO {
    private ResultSet resultado = null;
    
    public void popular_tabela_passandoData (JTable tabela, pedidos p){
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
                    + "SELECT count(*)"
                    +"FROM pedido "
                    +"WHERE  data BETWEEN '"+ p.getDataIni() +"' AND '"+ p.getDataFim() +"'") ;

            resultado.next();

            dadosTabela = new Object[resultado.getInt(1)][8];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT *"
                    +"FROM pedido "
                    +"WHERE  data BETWEEN '"+ p.getDataIni() +"' AND '"+ p.getDataFim() +"'") ;
            
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

                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela pedidos...");
            System.out.println(e);
        }
        
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
    
    public void popular_tabela_passandoNomePessoa (JTable tabela, pedidos p){
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
        
        int nome_pessoa = new PessoasDAO().conslutarNome2(p.getNomePessoa());

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*)"
                    +"FROM pedido "
                    +"WHERE id_pessoa =" + nome_pessoa) ;

            resultado.next();

            dadosTabela = new Object[resultado.getInt(1)][8];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT *"
                    +"FROM pedido "
                    +"WHERE id_pessoa =" + nome_pessoa) ;
            
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

                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela pedidos...");
            System.out.println(e);
        }
        
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
    
    public void popular_tabela_passandoNomeGame (JTable tabela, pedidos p){
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
        
        int nome_game = new GamesDAO ().consultar_id(p.getNomeJogo());

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*)"
                    +"FROM pedido "
                    +"WHERE id_game ="+ nome_game +"") ;

            resultado.next();

            dadosTabela = new Object[resultado.getInt(1)][8];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT *"
                    +"FROM pedido "
                    +"WHERE id_game ="+ nome_game +"") ;
            
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

                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela pedidos...");
            System.out.println(e);
        }
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
    
    public void popular_tabela_passandoID_Pedido (JTable tabela, pedidos p){
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
                    + "SELECT count(*)"
                    +"FROM pedido "
                    +"WHERE id_pedido ="+ p.getId_pedido() +"") ;

            resultado.next();

            dadosTabela = new Object[resultado.getInt(1)][8];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT *"
                    +"FROM pedido "
                    +"WHERE id_pedido ="+ p.getId_pedido() +"") ;
            
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

                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela pedidos...");
            System.out.println(e);
        }
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
    
    public void popular_tabela_passandoSituacao (JTable tabela, pedidos p){
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
                    + "SELECT count(*)"
                    +"FROM pedido "
                    +"WHERE situacao = '"+ p.getSituacao() +"'") ;

            resultado.next();

            dadosTabela = new Object[resultado.getInt(1)][8];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT *"
                    +"FROM pedido "
                    +"WHERE situacao = '"+ p.getSituacao() +"'") ;
            
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

                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela pedidos...");
            System.out.println(e);
        }
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
    
    public void popular_tabela_passandoFormaDePagamento (JTable tabela, pedidos p){
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
                    + "SELECT count(*)"
                    +"FROM pedido "
                    +"WHERE id_forma_de_pagamento = "+ p.getId_forma_pagamento() +"") ;

            resultado.next();

            dadosTabela = new Object[resultado.getInt(1)][8];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT *"
                    +"FROM pedido "
                    +"WHERE id_forma_de_pagamento = "+ p.getId_forma_pagamento() +"") ;
            
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

                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela pedidos...");
            System.out.println(e);
        }
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
    
    public void popular_tabela_passandoNomeJogo_E_Data (JTable tabela, pedidos p){
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
        

        int nome_game = new GamesDAO ().consultar_id(p.getNomeJogo());
        
        // cria matriz de acordo com nº de registros da tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*)"
                    +"FROM pedido "
                    +"WHERE data BETWEEN '"+ p.getDataIni() +"' AND '"+ p.getDataFim() +"' AND id_game ="+ nome_game) ;

            resultado.next();

            dadosTabela = new Object[resultado.getInt(1)][8];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT *"
                    +"FROM pedido "
                    +"WHERE data BETWEEN '"+ p.getDataIni() +"' AND '"+ p.getDataFim() +"' AND id_game ="+ nome_game ) ;
            
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

                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela pedidos...");
            System.out.println(e);
        }
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
    
    public void popular_tabela_passandoFormaDePagamento_E_Data (JTable tabela, pedidos p){
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
                    + "SELECT count(*)"
                    +"FROM pedido "
                    +"WHERE data BETWEEN '"+ p.getDataIni() +"' AND '"+ p.getDataFim() +"' AND id_forma_de_pagamento = "+ p.getId_forma_pagamento() +"") ;

            resultado.next();

            dadosTabela = new Object[resultado.getInt(1)][8];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT *"
                    +"FROM pedido "
                    +"WHERE data BETWEEN '"+ p.getDataIni() +"' AND '"+ p.getDataFim() +"' AND id_forma_de_pagamento = "+ p.getId_forma_pagamento() +"") ;
            
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

                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela pedidos...");
            System.out.println(e);
        }
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
    
    public void popular_tabela_passandoNomePessoa_Data_FormaPagamento (JTable tabela, pedidos p){
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
        
        int nome_pessoa = new PessoasDAO().conslutarNome2(p.getNomePessoa());
        
        // cria matriz de acordo com nº de registros da tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*)"
                    +"FROM pedido "
                    +"WHERE data BETWEEN '"+ p.getDataIni() +"' AND '"+ p.getDataFim() +"' AND id_forma_de_pagamento = "+ p.getId_forma_pagamento() +" AND id_pessoa = "+ nome_pessoa +"") ;

            resultado.next();

            dadosTabela = new Object[resultado.getInt(1)][8];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT *"
                    +"FROM pedido "
                    +"WHERE data BETWEEN '"+ p.getDataIni() +"' AND '"+ p.getDataFim() +"' AND id_forma_de_pagamento = "+ p.getId_forma_pagamento() +" AND id_pessoa = "+ nome_pessoa +"") ;
            
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

                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela pedidos...");
            System.out.println(e);
        }
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
    
    public void popular_tabela_passandoNomePessoa_E_Data (JTable tabela, pedidos p){
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
        
        int nome_pessoa = new PessoasDAO().conslutarNome2(p.getNomePessoa());
        
        // cria matriz de acordo com nº de registros da tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*)"
                    +"FROM pedido "
                    +"WHERE data BETWEEN '"+ p.getDataIni() +"' AND '"+ p.getDataFim() +"' AND id_pessoa = "+ nome_pessoa ) ;

            resultado.next();

            dadosTabela = new Object[resultado.getInt(1)][8];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT *"
                    +"FROM pedido "
                    +"WHERE data BETWEEN '"+ p.getDataIni() +"' AND '"+ p.getDataFim() +"' AND id_pessoa = "+ nome_pessoa ) ;
            
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

                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela pedidos...");
            System.out.println(e);
        }
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
    
    public void popular_tabela_passandoNomePessoa_E_FormaPagamento (JTable tabela, pedidos p){
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
        
        int nome_pessoa = new PessoasDAO().conslutarNome2(p.getNomePessoa());
        
        // cria matriz de acordo com nº de registros da tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*)"
                    +"FROM pedido "
                    +"WHERE id_pessoa = "+ nome_pessoa +" AND id_forma_de_pagamento = "+ p.getId_forma_pagamento() +"") ;

            resultado.next();

            dadosTabela = new Object[resultado.getInt(1)][8];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT *"
                    +"FROM pedido "
                    +"WHERE id_pessoa = "+ nome_pessoa +" AND id_forma_de_pagamento = "+ p.getId_forma_pagamento() +"") ;
            
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

                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela pedidos...");
            System.out.println(e);
        }
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
