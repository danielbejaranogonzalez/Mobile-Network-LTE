/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VentanaEliminarProyecto.java
 *
 * Created on 17/08/2011, 11:57:45 PM
 */
package Vista.ventanas.Proyecto;

import Controlador.ControladorGeneral;
import Libreria.CentrarScreen;
import Modelo.PRO.Proyecto;
import java.util.LinkedList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
 
/**
 *
 * @author Usuario
 */
public class VentanaEliminarProyecto extends javax.swing.JDialog {

    private ControladorGeneral controlador;
    private String listaProyectos[];
    
    /** Creates new form VentanaEliminarProyecto */
    public VentanaEliminarProyecto(java.awt.Frame parent, boolean modal, ControladorGeneral controlador) {
        super(parent, modal);
        this.controlador = controlador;
        initComponents();
        
        String proyectoPredeterminado = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        listaProyectos = new String[proyectos.size()];
        
        for(int i=0; i < proyectos.size(); i++){
            listaProyectos[i] = proyectos.get(i).getNombre();
        }
        
        comboBoxListaProyectos1.setModel(new DefaultComboBoxModel(listaProyectos));
        comboBoxListaProyectos2.setModel(new DefaultComboBoxModel(listaProyectos));
        comboBoxListaProyectos2.setSelectedItem(proyectoPredeterminado);
        
        if(listaProyectos.length <= 1){
            comboBoxListaProyectos2.setModel(new DefaultComboBoxModel());
        }
        
        CentrarScreen.centrarVentana(this);
        this.setVisible(true);
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        comboBoxListaProyectos2 = new javax.swing.JComboBox();
        comboBoxListaProyectos1 = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        botonCanelar = new javax.swing.JButton();
        botonAceptar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos32x32/package_delete.png"))); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Eliminar Proyecto");
        setIconImage(new ImageIcon("src/Images/iconos16x16/package_delete.png").getImage());
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Eliminar Proyecto:");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText("Nuevo Proyecto Predeterminado:");
        jLabel2.setName("jLabel2"); // NOI18N

        comboBoxListaProyectos2.setName("comboBoxListaProyectos2"); // NOI18N

        comboBoxListaProyectos1.setName("comboBoxListaProyectos1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(comboBoxListaProyectos1, 0, 290, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboBoxListaProyectos2, 0, 294, Short.MAX_VALUE)
                        .addGap(8, 8, 8))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxListaProyectos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxListaProyectos2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setName("jPanel2"); // NOI18N

        botonCanelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos16x16/cross-script.png"))); // NOI18N
        botonCanelar.setText(" Cancelar");
        botonCanelar.setName("botonCanelar"); // NOI18N
        botonCanelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCanelarActionPerformed(evt);
            }
        });

        botonAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos16x16/tick.png"))); // NOI18N
        botonAceptar.setText(" Aceptar");
        botonAceptar.setName("botonAceptar"); // NOI18N
        botonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(260, Short.MAX_VALUE)
                .addComponent(botonCanelar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(botonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(botonCanelar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator2.setName("jSeparator2"); // NOI18N
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 525, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos32x32/package_delete.png"))); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("Asistente para la eliminacion de un proyecto existente");
        jLabel4.setName("jLabel4"); // NOI18N
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 17, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos32x32/error.png"))); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonCanelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCanelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
}//GEN-LAST:event_botonCanelarActionPerformed

    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        // TODO add your handling code here:
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        listaProyectos = new String[proyectos.size()];
        
        for(int i=0; i < proyectos.size(); i++){
            listaProyectos[i] = proyectos.get(i).getNombre();
            if(listaProyectos[i].equals(comboBoxListaProyectos1.getSelectedItem().toString())){
                 controlador.getControladorVentanas().eliminarProyecto(i, (String)comboBoxListaProyectos2.getSelectedItem());
            }
        }
        this.dispose();
}//GEN-LAST:event_botonAceptarActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAceptar;
    private javax.swing.JButton botonCanelar;
    private javax.swing.JComboBox comboBoxListaProyectos1;
    private javax.swing.JComboBox comboBoxListaProyectos2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
