/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VentanaNuevaAntena.java
 *
 * Created on 31/08/2011, 10:28:03 PM
 */
package Vista.ventanas.RAN.Components.radios;

import Modelo.RAN.TarjetaRadio;
import Vista.ventanas.RAN.Components.antenas.*;
import Controlador.ControladorGeneral;
import Libreria.CentrarScreen;
import Modelo.PRO.Proyecto;
import Modelo.RAN.Antena;
import Modelo.RAN.ENodeB;
import Modelo.RAN.Radio;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class VentanaGestionEquiposRadio extends javax.swing.JDialog {

    private ControladorGeneral controlador;
    private String arrayRANs[];
    private String eNodosB[];
    private String nombreRANActual;
    
    /** Creates new form VentanaNuevaAntena */
    public VentanaGestionEquiposRadio(java.awt.Frame parent, boolean modal, ControladorGeneral controlador) {
        super(parent, modal);
        this.controlador = controlador;
        initComponents();
        
        String proyectoPredeterminado = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        LinkedList <Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        
        for(int i = 0; i < proyectos.size(); i++){
            
            if(proyectos.get(i).getNombre().equals(proyectoPredeterminado)){
                
                arrayRANs = new String [proyectos.get(i).getGrupoRANs().size()];
                for(int j = 0; j < proyectos.get(i).getGrupoRANs().size(); j++){
                    arrayRANs[j] = proyectos.get(i).getGrupoRANs().get(j).getNombre();
                }
            }
        }
        try{
            nombreRAN.setModel(new DefaultComboBoxModel(arrayRANs));
        }catch(NullPointerException NPE){
            JOptionPane.showMessageDialog(null, "'No exiten sitios en el proyecto predeterminado'","Error 001: NullPointerException",JOptionPane.ERROR_MESSAGE);
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

        jPanel5 = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        encontrar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        botonNuevoRadio = new javax.swing.JButton();
        botonEliminarRadio = new javax.swing.JButton();
        botonEditarRadio = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        botonPanelTargetas = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        encontrar2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        nombreRAN = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        nombreENodeB = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaRadios = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        eNodeBActual = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        botonAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestion de Equipos de Radio");
        setIconImage(new ImageIcon("src/Images/iconos16x16/stanchion.png").getImage());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setName("jPanel5"); // NOI18N

        jSeparator4.setName("jSeparator4"); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos32x32/stanchion.png"))); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Asistente para la Gestion de Equipos de Radio");
        jLabel2.setName("jLabel2"); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addContainerGap(331, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)))
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        encontrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos32x32/find.png"))); // NOI18N
        encontrar.setMnemonic('E');
        encontrar.setToolTipText("Encontrar...");
        encontrar.setFocusable(false);
        encontrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        encontrar.setName("encontrar"); // NOI18N
        encontrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        encontrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encontrarActionPerformed(evt);
            }
        });
        jToolBar1.add(encontrar);

        jSeparator1.setName("jSeparator1"); // NOI18N
        jToolBar1.add(jSeparator1);

        botonNuevoRadio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos32x32/page_white_add.png"))); // NOI18N
        botonNuevoRadio.setToolTipText("Crear Nuevo Equipo de Radio");
        botonNuevoRadio.setEnabled(false);
        botonNuevoRadio.setName("botonNuevoRadio"); // NOI18N
        botonNuevoRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevoRadioActionPerformed(evt);
            }
        });
        jToolBar1.add(botonNuevoRadio);

        botonEliminarRadio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos32x32/page_white_delete.png"))); // NOI18N
        botonEliminarRadio.setToolTipText("Eliminar Equipo de Radio");
        botonEliminarRadio.setEnabled(false);
        botonEliminarRadio.setName("botonEliminarRadio"); // NOI18N
        botonEliminarRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarRadioActionPerformed(evt);
            }
        });
        jToolBar1.add(botonEliminarRadio);

        botonEditarRadio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos32x32/page_white_edit.png"))); // NOI18N
        botonEditarRadio.setToolTipText("Editar Equipo de Radio");
        botonEditarRadio.setEnabled(false);
        botonEditarRadio.setName("botonEditarRadio"); // NOI18N
        botonEditarRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEditarRadioActionPerformed(evt);
            }
        });
        jToolBar1.add(botonEditarRadio);

        jSeparator3.setName("jSeparator3"); // NOI18N
        jToolBar1.add(jSeparator3);

        botonPanelTargetas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos32x32/network_adapter.png"))); // NOI18N
        botonPanelTargetas.setToolTipText("Panel de Gestion de Tarjetas");
        botonPanelTargetas.setEnabled(false);
        botonPanelTargetas.setFocusable(false);
        botonPanelTargetas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonPanelTargetas.setName("botonPanelTargetas"); // NOI18N
        botonPanelTargetas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonPanelTargetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPanelTargetasActionPerformed(evt);
            }
        });
        jToolBar1.add(botonPanelTargetas);

        jPanel2.add(jToolBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 610, 40));

        jSeparator2.setName("jSeparator2"); // NOI18N
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 620, 10));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Panel de Busqueda"));
        jPanel1.setName("jPanel1"); // NOI18N

        encontrar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos16x16/find.png"))); // NOI18N
        encontrar2.setText(" Encontrar");
        encontrar2.setName("encontrar2"); // NOI18N
        encontrar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encontrar2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Radio Access Network:");
        jLabel3.setName("jLabel3"); // NOI18N

        nombreRAN.setName("nombreRAN"); // NOI18N
        nombreRAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreRANActionPerformed(evt);
            }
        });

        jLabel4.setText("eNodeB:");
        jLabel4.setName("jLabel4"); // NOI18N

        nombreENodeB.setName("nombreENodeB"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombreRAN, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombreENodeB, 0, 197, Short.MAX_VALUE))
                    .addComponent(encontrar2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreRAN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreENodeB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(encontrar2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 600, 80));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Panel de Parametros de Equipos de Radio"));
        jPanel3.setName("jPanel3"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tablaRadios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Fabricante", "Cantidad Slots de Radio", "Cantidad Slots de Interconexion", "Estado del Equipo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaRadios.setToolTipText("Tabla Antenas");
        tablaRadios.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaRadios.setGridColor(new java.awt.Color(204, 204, 204));
        tablaRadios.setName("tablaRadios"); // NOI18N
        tablaRadios.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaRadios);
        tablaRadios.getColumnModel().getColumn(0).setPreferredWidth(110);
        tablaRadios.getColumnModel().getColumn(1).setPreferredWidth(90);
        tablaRadios.getColumnModel().getColumn(2).setPreferredWidth(150);
        tablaRadios.getColumnModel().getColumn(3).setPreferredWidth(170);
        tablaRadios.getColumnModel().getColumn(4).setPreferredWidth(140);

        jLabel5.setText("eNodeB:");
        jLabel5.setName("jLabel5"); // NOI18N

        eNodeBActual.setEditable(false);
        eNodeBActual.setName("eNodeBActual"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eNodeBActual, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(eNodeBActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 600, 170));

        jTabbedPane1.addTab("Panel de Gestion de Equipos de Radio", jPanel2);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setName("jPanel4"); // NOI18N

        botonAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos16x16/tick.png"))); // NOI18N
        botonAceptar.setText("Cerrar");
        botonAceptar.setName("botonAceptar"); // NOI18N
        botonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(533, Short.MAX_VALUE)
                .addComponent(botonAceptar))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(botonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Panel de Gestion de Equipos de Radio");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        // TODO add your handling code here:
        //controlador.getControladorVentanas().editarENodeB(listaeNodeB.getSelectedItem().toString(), nombreENodeB.getText(), listaSitios.getSelectedItem().toString(), actualNombreRAN, listaRANs.getSelectedItem().toString(), Double.parseDouble(alturaTorre.getValue().toString()), tiposTorre.getSelectedItem().toString());
        this.dispose();
}//GEN-LAST:event_botonAceptarActionPerformed

    private void botonNuevoRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevoRadioActionPerformed
        // TODO add your handling code here:
        controlador.getControladorVentanas().crearVentanaNuevoEquipoRadio(controlador, nombreRAN.getSelectedItem().toString(), nombreENodeB.getSelectedItem().toString());
    }//GEN-LAST:event_botonNuevoRadioActionPerformed

    private void nombreRANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreRANActionPerformed
        // TODO add your handling code here:
        String RANSeleccionada = nombreRAN.getSelectedItem().toString();
        String proyectoPredeterminado = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        LinkedList <Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        
        for(int i = 0; i < proyectos.size(); i++){
            
            if(proyectos.get(i).getNombre().equals(proyectoPredeterminado)){
                
                for(int j = 0; j < proyectos.get(i).getGrupoRANs().size(); j++){
                    if(proyectos.get(i).getGrupoRANs().get(j).getNombre().equals(RANSeleccionada)){
                        
                        LinkedList <ENodeB> grupoNodos = proyectos.get(i).getGrupoRANs().get(j).getGrupoNodosB();
                        eNodosB = new String [grupoNodos.size()];
                        for(int k = 0; k < grupoNodos.size(); k++){
                            eNodosB[k] = grupoNodos.get(k).getNombre();
                            nombreENodeB.setModel(new DefaultComboBoxModel(eNodosB));
                        }
                        if(grupoNodos.size() == 0){
                            eNodosB = new String [0];
                            nombreENodeB.setModel(new DefaultComboBoxModel(eNodosB));
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_nombreRANActionPerformed

    private void encontrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encontrar2ActionPerformed
        // TODO add your handling code here:
        encontrarENodeB();
    }//GEN-LAST:event_encontrar2ActionPerformed

    private void encontrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encontrarActionPerformed
        // TODO add your handling code here:
        encontrarENodeB();
    }//GEN-LAST:event_encontrarActionPerformed

private void botonEditarRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEditarRadioActionPerformed
// TODO add your handling code here:
    try{
        VentanaEditarRadio ventana = new VentanaEditarRadio(new JFrame(), true, controlador, nombreRANActual ,eNodeBActual.getText(), getFilaSeleccionada());
        CentrarScreen.centrarVentana(ventana);
        ventana.setVisible(true);
    }catch(ArrayIndexOutOfBoundsException AIE){
        JOptionPane.showMessageDialog(null, "'No ha seleccionado ningun Equipo de Radio'","Error 004: NullPointerException",JOptionPane.ERROR_MESSAGE);
    }
}//GEN-LAST:event_botonEditarRadioActionPerformed

private void botonEliminarRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarRadioActionPerformed
// TODO add your handling code here:
    try{
        VentanaEliminarRadio ventana = new VentanaEliminarRadio(new JFrame(), true, controlador, nombreRANActual ,eNodeBActual.getText(), getFilaSeleccionada());
        CentrarScreen.centrarVentana(ventana);
        ventana.setVisible(true);
    }catch(ArrayIndexOutOfBoundsException AIE){
        JOptionPane.showMessageDialog(null, "'No ha seleccionado ninguna Antena'","Error 004: NullPointerException",JOptionPane.ERROR_MESSAGE);
    }
}//GEN-LAST:event_botonEliminarRadioActionPerformed

private void botonPanelTargetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPanelTargetasActionPerformed
// TODO add your handling code here:
    try{
        controlador.getControladorBotones().crearVentanaGestionTargetas(controlador, nombreRANActual ,eNodeBActual.getText(), getFilaSeleccionada());
    }catch(ArrayIndexOutOfBoundsException AIO){
        JOptionPane.showMessageDialog(null, "'No ha seleccionado ningun Equipo de Radio'","Error 006: ArrayIndexOutOfBoundsException", JOptionPane.ERROR_MESSAGE);
    }
    
}//GEN-LAST:event_botonPanelTargetasActionPerformed

    private void encontrarENodeB(){
        String RANSeleccionada;

        try{
        
        RANSeleccionada = nombreRAN.getSelectedItem().toString();
        
        String proyectoPredeterminado = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        LinkedList <Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        
            if(!nombreENodeB.getSelectedItem().toString().equals("")){
                for(int i = 0; i < proyectos.size(); i++){

                    if(proyectos.get(i).getNombre().equals(proyectoPredeterminado)){

                        for(int j = 0; j < proyectos.get(i).getGrupoRANs().size(); j++){
                            if(proyectos.get(i).getGrupoRANs().get(j).getNombre().equals(RANSeleccionada)){

                                LinkedList <ENodeB> grupoNodos = proyectos.get(i).getGrupoRANs().get(j).getGrupoNodosB();
                                for(int k = 0; k < grupoNodos.size(); k++){
                                    if(grupoNodos.get(k).getNombre().equals(nombreENodeB.getSelectedItem().toString())){
                                        botonNuevoRadio.setEnabled(true);
                                        botonEliminarRadio.setEnabled(true);
                                        botonEditarRadio.setEnabled(true);
                                        botonPanelTargetas.setEnabled(true);
                                        nombreRANActual = RANSeleccionada;
                                        
                                        eNodeBActual.setText(grupoNodos.get(k).getNombre());
                                        actualizarTablaRadios(grupoNodos.get(k).getRadios());
                                    }
                                }
                            }
                        }
                    }
                }
            }else{
                eNodeBActual.setText("");
                botonNuevoRadio.setEnabled(false);
            }
        }catch(NullPointerException NPE){
            eNodeBActual.setText("");
            botonNuevoRadio.setEnabled(false);
            botonEliminarRadio.setEnabled(false);
            botonEditarRadio.setEnabled(false);
            
            JOptionPane.showMessageDialog(null, "'No exite ningun ENodeB creado para la RAN: " + nombreRAN.getSelectedItem().toString() + "'","Error 003: NullPointerException",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void actualizarTablaRadios(LinkedList <Radio> equipRadio) {
        
        Object[][] parametros = new Object[equipRadio.size()][5];
        System.out.print("TEST 1: " + equipRadio.size());
                
        String[] columnas = new String [] {
                "Nombre", "Fabricante", "Cantidad Slots de Radio", "Cantidad Slots de Interconexion", "Estado del Equipo"
            };
        
        for(int j = 0; j < equipRadio.size(); j++){
            parametros[j][0] = equipRadio.get(j).getNombre();                 parametros[j][1] = equipRadio.get(j).getFabricante();
            parametros[j][2] = equipRadio.get(j).getInterfacesRadio().length;  parametros[j][3] = equipRadio.get(j).getInterfacesAcceso().length;
            parametros[j][4] = equipRadio.get(j).isEstado();
        }
        DefaultTableModel modelo = new DefaultTableModel(parametros, columnas);
        tablaRadios.setModel(modelo);
        
        tablaRadios.getColumnModel().getColumn(0).setPreferredWidth(110);
        tablaRadios.getColumnModel().getColumn(1).setPreferredWidth(90);
        tablaRadios.getColumnModel().getColumn(2).setPreferredWidth(150);
        tablaRadios.getColumnModel().getColumn(3).setPreferredWidth(170);
        tablaRadios.getColumnModel().getColumn(4).setPreferredWidth(140);
        tablaRadios.setEnabled(true);
    }
    
    public String getFilaSeleccionada()
    {
          return (String) tablaRadios.getValueAt(tablaRadios.getSelectedRow(), 0).toString();
    }
    /**
     * @param args the command line arguments
     */
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAceptar;
    private javax.swing.JButton botonEditarRadio;
    private javax.swing.JButton botonEliminarRadio;
    private javax.swing.JButton botonNuevoRadio;
    private javax.swing.JButton botonPanelTargetas;
    private javax.swing.JTextField eNodeBActual;
    private javax.swing.JButton encontrar;
    private javax.swing.JButton encontrar2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JComboBox nombreENodeB;
    private javax.swing.JComboBox nombreRAN;
    private javax.swing.JTable tablaRadios;
    // End of variables declaration//GEN-END:variables

  

}