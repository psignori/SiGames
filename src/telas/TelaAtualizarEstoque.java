/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import apoio.ComboItem;
import apoio.CombosDAO;
import dao.EstoqueDAO;
import dao.GamesDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author patri
 */
public class TelaAtualizarEstoque extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaAtualizarEstoque
     */
    public TelaAtualizarEstoque() {
        initComponents();
        new CombosDAO ().popularCombo("game", "id_game", "nome", jComboNomeEstoque, "");
        new EstoqueDAO ().popularTabela(jTableAtualizarEstoque);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jComboAtualizarEstoque = new javax.swing.JComboBox<>();
        jComboNomeEstoque = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableAtualizarEstoque = new javax.swing.JTable();
        BtnAtualizarEstoque = new javax.swing.JButton();

        setClosable(true);
        setTitle("SiGames: Atualizar Estoque");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel5.setText("Jogo:");

        jComboAtualizarEstoque.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboAtualizarEstoque.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50" }));

        jComboNomeEstoque.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboNomeEstoque.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel7.setText("Quantidade:");

        jTableAtualizarEstoque.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTableAtualizarEstoque);

        BtnAtualizarEstoque.setBackground(new java.awt.Color(64, 77, 137));
        BtnAtualizarEstoque.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        BtnAtualizarEstoque.setForeground(new java.awt.Color(255, 255, 255));
        BtnAtualizarEstoque.setText("Comprar");
        BtnAtualizarEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAtualizarEstoqueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(BtnAtualizarEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboNomeEstoque, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboAtualizarEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboNomeEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboAtualizarEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(BtnAtualizarEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setBounds(350, 50, 600, 400);
    }// </editor-fold>//GEN-END:initComponents

    private void BtnAtualizarEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAtualizarEstoqueActionPerformed
        String g = null;
        String y = null;
        int c = 0;
        int k = 0;
        g = this.jComboAtualizarEstoque.getSelectedItem().toString();
        c = Integer.parseInt(g);
        ComboItem d = (ComboItem) this.jComboNomeEstoque.getSelectedItem();
        int id = 0;
        GamesDAO game = new GamesDAO ();
        EstoqueDAO est = new EstoqueDAO();

        if (d.getDescricao() != "Selecione"){
            id = game.consultar_id(d.getDescricao());
            k = est.consultar_quantidade(id);
            c = c + k;
            new EstoqueDAO ().atualizar(id, c);
            JOptionPane.showMessageDialog(null, "Estoque atualizado!");
            jComboNomeEstoque.setSelectedIndex(0);
            jComboAtualizarEstoque.setSelectedIndex(0);
            new GamesDAO ().ativar(id);
            new EstoqueDAO ().popularTabela(jTableAtualizarEstoque);
        }
        else {
            JOptionPane.showMessageDialog(null, "Selecione um jogo");
        }
    }//GEN-LAST:event_BtnAtualizarEstoqueActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAtualizarEstoque;
    private javax.swing.JComboBox<String> jComboAtualizarEstoque;
    private javax.swing.JComboBox<String> jComboNomeEstoque;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableAtualizarEstoque;
    // End of variables declaration//GEN-END:variables
}
