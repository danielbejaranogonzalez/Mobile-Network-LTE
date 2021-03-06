/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TestEstado.java
 *
 * Created on 27/12/2010, 11:16:54 PM
 */

package Vista.barras;

import Controlador.ControladorGeneral;
import javax.swing.ImageIcon;

/**
 *
 * @author Bejaranos
 */
public class BarraEstado extends javax.swing.JPanel {

    private ImageIcon iconoSinInternet;
    private ImageIcon iconoConInternet;
    private ControladorGeneral controlador;
    
    
    /** Creates new form TestEstado */
    public BarraEstado(ControladorGeneral controlador) {
        initComponents();
        this.controlador = controlador;
        iconoSinInternet = new ImageIcon("src/Images/iconos16x16/globe--exclamation.png");
        iconoConInternet = new ImageIcon("src/Images/iconos16x16/globe-green.png");
    }

    public void setStatus(int status){
        if(status == 0){
            statusIcon.setIcon(iconoSinInternet);
        }if(status == 1){
            statusIcon.setIcon(iconoConInternet);
        }
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        barraProgreso = new javax.swing.JProgressBar();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        statusIcon = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setLayout(new java.awt.BorderLayout());

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jToolBar1.add(jSeparator1);
        jToolBar1.add(barraProgreso);
        jToolBar1.add(jSeparator2);

        statusIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos16x16/globe-network.png"))); // NOI18N
        jToolBar1.add(statusIcon);

        add(jToolBar1, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barraProgreso;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel statusIcon;
    // End of variables declaration//GEN-END:variables

}
