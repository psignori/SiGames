/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import apoio.ComboItem;
import apoio.CombosDAO;
import apoio.Formatacao;
import dao.CidadesDAO;
import dao.EstadoDAO;
import javax.swing.JOptionPane;
import negocios.cidade;

/**
 *
 * @author patri
 */
public class TelaCadastrarCidades extends javax.swing.JInternalFrame {
    
    private int set = 0;
    private String nome = "sem";
    private int cep = 0;
    private String CadAtu = null;
    private String estado = null;

    /**
     * Creates new form TelaCadastrarCidades
     */
    
    public TelaCadastrarCidades(int a, String n, int c, String j, int e) {
        initComponents();
        new CombosDAO ().popularCombo("estado", "id_estado", "sigla", jComboCadCidade, "");
        new Formatacao ().formatarCEP(CampoCadCepCidade); 
        EstadoDAO est = new EstadoDAO ();
        set = a;
        nome = n;
        cep = c;
        CadAtu = j;
        estado = est.consultar_nome(e);
        jButtonCadCidades1.setText(j);
        if (! j.equals("Cadastrar")){
            int u;
            CampoCadastrarNomeCidade.setText(nome);
            CampoCadCepCidade.setText(""+cep);
            u = busca(estado);
            jComboCadCidade.setSelectedIndex(u);
        }
    }
    
    public int busca (String nome){
        int a = 1;
        boolean d = false;
        while (d != true){
            jComboCadCidade.setSelectedIndex(a);
            String s = jComboCadCidade.getSelectedItem().toString();
            EstadoDAO estado = new EstadoDAO ();
            s = estado.consultar_nome_passando_sigla(s);
            if (s.equals(nome)){
                d = true;
            } else {
               a++; 
            }
            
            
        }
        return a;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        CampoCadastrarNomeCidade = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        CampoCadCepCidade = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jComboCadCidade = new javax.swing.JComboBox<>();
        jButtonCadCidades = new javax.swing.JButton();
        jButtonCadCidades1 = new javax.swing.JButton();

        setClosable(true);
        setTitle("SiGames: Cadastrar Cidades");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Nome:");

        CampoCadastrarNomeCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoCadastrarNomeCidadeActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("CEP:");

        CampoCadCepCidade.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CampoCadCepCidade.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("Estado:");

        jComboCadCidade.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jComboCadCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboCadCidadeActionPerformed(evt);
            }
        });

        jButtonCadCidades.setBackground(new java.awt.Color(64, 77, 137));
        jButtonCadCidades.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButtonCadCidades.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCadCidades.setText("Cadastrar");
        jButtonCadCidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadCidadesActionPerformed(evt);
            }
        });

        jButtonCadCidades1.setBackground(new java.awt.Color(64, 77, 137));
        jButtonCadCidades1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButtonCadCidades1.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCadCidades1.setText("Cadastrar");
        jButtonCadCidades1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadCidades1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonCadCidades1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jComboCadCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(CampoCadastrarNomeCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CampoCadCepCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addGap(18, 18, 18))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(685, 685, 685)
                    .addComponent(jButtonCadCidades, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CampoCadastrarNomeCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CampoCadCepCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboCadCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(jButtonCadCidades1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(329, Short.MAX_VALUE)
                    .addComponent(jButtonCadCidades, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        setBounds(350, 50, 600, 400);
    }// </editor-fold>//GEN-END:initComponents

    private void CampoCadastrarNomeCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoCadastrarNomeCidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoCadastrarNomeCidadeActionPerformed

    private void jComboCadCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboCadCidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboCadCidadeActionPerformed

    private void jButtonCadCidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadCidadesActionPerformed

    }//GEN-LAST:event_jButtonCadCidadesActionPerformed

    private void jButtonCadCidades1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadCidades1ActionPerformed
        cidade cid = new cidade ();
        Formatacao format = new Formatacao ();
        CidadesDAO cidDao = new CidadesDAO ();
        String ver ;
        boolean ver1 = false;

        ver = this.CampoCadastrarNomeCidade.getText();
        ver = ver.replace(" ", "");
        if (ver.isEmpty()){
            ver1 = true;
        }
        if (ver1 == false){
            ver = this.CampoCadCepCidade.getText();
            ver = ver.replace(" ", "");
            if (ver.isEmpty()){
                ver1 = true;
            }
            if (ver1 == false){
                if (CadAtu.equals("Cadastrar")){

                    cid.setNome(this.CampoCadastrarNomeCidade.getText());
                    cid.setCep(Integer.parseInt(format.removerFormatacao(this.CampoCadCepCidade.getText())));
                    ComboItem ci = (ComboItem) this.jComboCadCidade.getSelectedItem();
                    cid.setId_estado(ci.getCodigo());

                    if (cidDao.salvar(cid)){
                        JOptionPane.showMessageDialog(null, "Registro salvo com sucesso!");
                        this.dispose();

                    } else {
                        JOptionPane.showMessageDialog(null, "Problemas ao salvar registro!");
                    }
                } else {
                    cid.setId_cidade(set);
                    cid.setNome(CampoCadastrarNomeCidade.getText());
                    cid.setCep(Integer.parseInt(format.removerFormatacao(this.CampoCadCepCidade.getText())));
                    ComboItem ci = (ComboItem) this.jComboCadCidade.getSelectedItem();
                    cid.setId_estado(ci.getCodigo());

                    if (cidDao.AtualizarRecebendoDADOS(cid)){
                        JOptionPane.showMessageDialog(null, "Registro atualizado com sucesso!");
                        this.dispose();

                    } else {
                        JOptionPane.showMessageDialog(null, "Problemas ao salvar registro!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Preencha o CEP da cidade!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Preencha o nome da cidade!");
        }


    }//GEN-LAST:event_jButtonCadCidades1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField CampoCadCepCidade;
    private javax.swing.JTextField CampoCadastrarNomeCidade;
    private javax.swing.JButton jButtonCadCidades;
    private javax.swing.JButton jButtonCadCidades1;
    private javax.swing.JComboBox<String> jComboCadCidade;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
