/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * test.java
 *
 * Created on 22-feb-2012, 2:29:08
 */
package Libreria;

import Controlador.ControladorGeneral;
import Modelo.PRO.Proyecto;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author User
 */
public class FileChooserSavePersonalizado extends javax.swing.JDialog {

    /** Creates new form test */
    public FileChooserSavePersonalizado(java.awt.Frame parent, boolean modal, ControladorGeneral controlador, Proyecto proyecto) {
        super(parent, modal);
        initComponents();
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de Mobile Network Designer 1.0 (*.mnd)", "mnd", "MND");
        fileChooser.setFileFilter(filter);
        int seleccion = fileChooser.showSaveDialog(this);
                    
        if (seleccion == JFileChooser.APPROVE_OPTION){
            File fichero = fileChooser.getSelectedFile();
            controlador.getLibroDeProyectos().guardarProyectoPredeterminado(proyecto, fichero.getPath()+".mnd");
            this.dispose();
        }
        
        this.setTitle("Guardar como");
        CentrarScreen.centrarVentana(this);
        //this.setVisible(true);
        
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Guardar como");
        setIconImage(new ImageIcon("src/Images/iconos16x16/disk.png").getImage());

        fileChooser.setCurrentDirectory(new java.io.File("C:\\Users\\User\\Documents\\MND Projects"));
        fileChooser.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        fileChooser.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setName("fileChooser"); // NOI18N
        fileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileChooserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(fileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
        );

        fileChooser.getAccessibleContext().setAccessibleParent(fileChooser);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void fileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileChooserActionPerformed
// TODO add your handling code here:
   
}//GEN-LAST:event_fileChooserActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser fileChooser;
    // End of variables declaration//GEN-END:variables
}
