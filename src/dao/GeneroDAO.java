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
import negocios.genero;

/**
 *
 * @author patri
 */
public class GeneroDAO implements IDAOT <genero> {
    private ResultSet resultado = null;

    @Override
    public boolean salvar(genero o) {
        try {
            Statement st = conexaoBD.getInstance().getConnection().createStatement();
            String sql = "";
            if (o.getId()== 0){
                sql = "INSERT INTO public.genero (id_genero, nome, descricao)"
                        + "VALUES ("
                        + "DEFAULT, "
                        +"'"+ o.getNome() +"'," + "'"+ o.getDescricao() +"')";
            }
            
            System.out.println("SQL: " + sql);

            int resultado = st.executeUpdate(sql);
            
            return true;
        }
        catch (Exception e){
            System.out.println("Erro ao se cadastrar = " + e);
            return false;
        }
    }

    @Override
    public boolean atualizar(genero o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<genero> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<genero> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public genero consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public String consultar_nome (int a){
        String i = null;
        Object[][] dadosTabela = null;
        int lin = 0; 
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT nome "
                    + "FROM genero "
                    + "WHERE "
                    + "id_genero = " + a + "");
            
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
                    + "SELECT id_genero "
                    + "FROM genero "
                    + "WHERE "
                    + "nome = '" + a + "'");
            
            while (resultado.next()) {
                String g = (""+resultado.getString("id_genero"));
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
        Object[] cabecalho = new Object[11];
        cabecalho[0] = "Código";
        cabecalho[1] = "Nome";
        cabecalho[2] = "Descrição";


        // cria matriz de acordo com nº de registros da tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) "
                    + "FROM genero "
                    + "WHERE "
                    + "nome ILIKE '%" + criterio + "%'");

            resultado.next();

            dadosTabela = new Object[resultado.getInt(1)][11];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * "
                    + "FROM genero "
                    + "WHERE "
                    + "nome ILIKE '%" + criterio + "%'");


            while (resultado.next()) {

                dadosTabela[lin][0] = resultado.getInt("id_genero");
                dadosTabela[lin][1] = resultado.getString("nome");
                dadosTabela[lin][2] = resultado.getString("descricao");


                // caso a coluna precise exibir uma imagem
//                if (resultadoQ.getBoolean("Situacao")) {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_ativo.png"));
//                } else {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_inativo.png"));
//                }
                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela genero...");
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
