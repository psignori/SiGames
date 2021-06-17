/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SiGames;

import apoio.conexaoBD;
import negocios.pessoas;
import telas.TelaPessoas;
import telas.TelaLogin;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import telas.TelaPrincipal1;





/**
 *
 * @author patri
 */
public class SiGames {
    conexaoBD conexao = new conexaoBD();
    
    
    public static void main (String [] args){
        if (conexaoBD.getInstance() != null) {
            pessoas p = new pessoas (); 
            TelaLogin tl = new TelaLogin ();
            tl.setVisible(true);
        }else {
            JOptionPane.showMessageDialog(null, "Erro ao conectar no banco!");
        }
    }
    
    public boolean abrirTc (){
        TelaPessoas tc = new TelaPessoas ();
        tc.setVisible(true);
        return true;
    }
    public boolean abrirTl (){
        TelaLogin tl = new TelaLogin();
        tl.setVisible(true);
        return true;
    }
    public boolean abrirTp (pessoas p){
        TelaPrincipal1 tp = new TelaPrincipal1 (p);
        tp.setVisible(true);
        return true;
    }
}
