/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VentanaEditarENodeB.java
 *
 * Created on 3/09/2011, 12:21:41 AM
 */
package Vista.ventanas.RAN.ENodeB;

import Controlador.ControladorGeneral;
import Modelo.PRO.Proyecto;
import Modelo.RAN.ENodeB;
import Modelo.RAN.Torre;
import java.util.LinkedList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

/**
 *
 * @author Usuario
 */
public class VentanaEditarENodeB extends javax.swing.JDialog {

    private ControladorGeneral controlador;
    
    private String actualNombreRAN;
    private String actualNombreTorre;
    
    /** Creates new form VentanaEditarENodeB */
    public VentanaEditarENodeB(java.awt.Frame parent, boolean modal, ControladorGeneral controlador) {
        super(parent, modal);
        this.controlador = controlador;
        initComponents();
        
        String proyectoPredeterminado = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        LinkedList <Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String arrayNodosB[];
        
        LinkedList <String> listaNodosB = new LinkedList();
        
        for(int i = 0; i < proyectos.size(); i++){
            
            if(proyectos.get(i).getNombre().equals(proyectoPredeterminado)){
                //arrayRANs = new String [proyectos.get(i).getGrupoRANs().size()];
                for(int j = 0; j < proyectos.get(i).getGrupoRANs().size(); j++){
                   LinkedList<ENodeB> listaNodosBActuales = proyectos.get(i).getGrupoRANs().get(j).getGrupoNodosB();
                   
                   for(int k = 0; k < listaNodosBActuales.size(); k++){
                       listaNodosB.add(listaNodosBActuales.get(k).getNombre());
                   }
                }
            }
        }
        arrayNodosB = new String[listaNodosB.size()];
        for(int i = 0; i < listaNodosB.size(); i++){
            arrayNodosB[i] = listaNodosB.get(i).toString();
            System.out.println("TEST " + i + ": "+ arrayNodosB[i]);
        }
        
        listaeNodeB.setModel(new DefaultComboBoxModel(arrayNodosB));
        this.setIconImage(new ImageIcon("src/Images/iconos16x16/eNB6-16-edit.png").getImage());
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
        jPanel1 = new javax.swing.JPanel();
        nombreENodeB = new javax.swing.JTextField();
        listaSitios = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        listaRANs = new javax.swing.JComboBox();
        jPanel7 = new javax.swing.JPanel();
        tiposTorre = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        alturaTorre = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        botonAceptar = new javax.swing.JButton();
        botonCanelar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        editarButton = new javax.swing.JToggleButton();
        encontrarSitio = new javax.swing.JButton();
        listaeNodeB = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar eNodeB");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setName("jPanel5"); // NOI18N

        jSeparator4.setName("jSeparator4"); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos32x32/eNB6-32edit.png"))); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Asistente para la edicion de un eNodeB existente");
        jLabel2.setName("jLabel2"); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addContainerGap(282, Short.MAX_VALUE))
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Panel Propiedades de eNodeB"));
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPanel1AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        nombreENodeB.setEditable(false);
        nombreENodeB.setName("nombreENodeB"); // NOI18N

        listaSitios.setEnabled(false);
        listaSitios.setName("listaSitios"); // NOI18N

        jLabel3.setText("Sitio Asociado:");
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText("Nombre del eNodeB:");
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel8.setText("RAN Asociada:");
        jLabel8.setName("jLabel8"); // NOI18N

        listaRANs.setEnabled(false);
        listaRANs.setName("listaRANs"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(listaSitios, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(listaRANs, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombreENodeB, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreENodeB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(listaRANs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(listaSitios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Panel Propiedades de Torre"));
        jPanel7.setName("jPanel7"); // NOI18N

        tiposTorre.setEnabled(false);
        tiposTorre.setName("tiposTorre"); // NOI18N

        jLabel5.setText("Tipo de Torre:");
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setText("Altura Total:");
        jLabel6.setName("jLabel6"); // NOI18N

        alturaTorre.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(250.0f), Float.valueOf(0.2f)));
        alturaTorre.setEnabled(false);
        alturaTorre.setName("alturaTorre"); // NOI18N
        alturaTorre.setValue(1);

        jLabel7.setText("[m]");
        jLabel7.setName("jLabel7"); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(alturaTorre, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(41, 41, 41)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tiposTorre, 0, 213, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tiposTorre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(alturaTorre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setName("jPanel4"); // NOI18N

        botonAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos16x16/tick.png"))); // NOI18N
        botonAceptar.setText(" Aceptar");
        botonAceptar.setName("botonAceptar"); // NOI18N
        botonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }
        });

        botonCanelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos16x16/cross-script.png"))); // NOI18N
        botonCanelar.setText(" Cancelar");
        botonCanelar.setName("botonCanelar"); // NOI18N
        botonCanelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCanelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(350, Short.MAX_VALUE)
                .addComponent(botonCanelar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(botonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(botonCanelar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setName("jPanel2"); // NOI18N

        editarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos16x16/pencil.png"))); // NOI18N
        editarButton.setText(" Editar");
        editarButton.setName("editarButton"); // NOI18N
        editarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarButtonActionPerformed(evt);
            }
        });

        encontrarSitio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos16x16/find.png"))); // NOI18N
        encontrarSitio.setText(" Encontrar");
        encontrarSitio.setName("encontrarSitio"); // NOI18N
        encontrarSitio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encontrarSitioActionPerformed(evt);
            }
        });

        listaeNodeB.setName("listaeNodeB"); // NOI18N

        jLabel9.setText("Buscar eNodeB: ");
        jLabel9.setName("jLabel9"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(editarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(encontrarSitio))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(listaeNodeB, 0, 469, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(listaeNodeB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(encontrarSitio)
                    .addComponent(editarButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel1AncestorAdded
        // TODO add your handling code here:
}//GEN-LAST:event_jPanel1AncestorAdded

    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        // TODO add your handling code here:
        controlador.getControladorVentanas().editarENodeB(listaeNodeB.getSelectedItem().toString(), nombreENodeB.getText(), listaSitios.getSelectedItem().toString(), actualNombreRAN, listaRANs.getSelectedItem().toString(), Double.parseDouble(alturaTorre.getValue().toString()), tiposTorre.getSelectedItem().toString());
        this.dispose();
    }//GEN-LAST:event_botonAceptarActionPerformed

    private void botonCanelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCanelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
}//GEN-LAST:event_botonCanelarActionPerformed

    private void editarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarButtonActionPerformed
        // TODO add your handling code here:
        if(editarButton.isSelected()){
            nombreENodeB.setEditable(true);
            listaSitios.setEnabled(true);
            listaRANs.setEnabled(true);
            alturaTorre.setEnabled(true);
            tiposTorre.setEnabled(true);
        }else{
            nombreENodeB.setEditable(false);
            listaSitios.setEnabled(false);
            listaRANs.setEnabled(false);
            alturaTorre.setEnabled(false);
            tiposTorre.setEnabled(false);
        }
}//GEN-LAST:event_editarButtonActionPerformed

    private void encontrarSitioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encontrarSitioActionPerformed
        // TODO add your handling code here:
        String proyectoPredeterminado = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        LinkedList <Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String[] arraySitios = null;
        String[] arrayRANs = null;
        
        for(int i = 0; i < proyectos.size(); i++){
            
            if(proyectos.get(i).getNombre().equals(proyectoPredeterminado)){
                
                arraySitios = new String[proyectos.get(i).getGrupoSitios().size()];
                for(int j = 0; j < proyectos.get(i).getGrupoSitios().size(); j++){
                    arraySitios[j] =  proyectos.get(i).getGrupoSitios().get(j).getNombreSitio();
                }
                listaSitios.setModel(new DefaultComboBoxModel(arraySitios));
                
                arrayRANs = new String[proyectos.get(i).getGrupoRANs().size()];
                for(int j = 0; j < proyectos.get(i).getGrupoRANs().size(); j++){
                    arrayRANs[j] =  proyectos.get(i).getGrupoRANs().get(j).getNombre();
                }
                listaRANs.setModel(new DefaultComboBoxModel(arrayRANs));
                tiposTorre.setModel(new DefaultComboBoxModel(Torre.getTiposTorres()));
                
                for(int j = 0; j < proyectos.get(i).getGrupoRANs().size(); j++){
                   LinkedList<ENodeB> listaNodosBActuales = proyectos.get(i).getGrupoRANs().get(j).getGrupoNodosB();
                   for(int k = 0; k < listaNodosBActuales.size(); k++){
                       if(listaNodosBActuales.get(k).getNombre().equals(listaeNodeB.getSelectedItem().toString())){
                            nombreENodeB.setText(listaNodosBActuales.get(k).getNombre());
                            //listaSitios.setSelectedItem(listaNodosBActuales.get(k).getNombreSitioAsociado());
                            listaRANs.setSelectedItem(proyectos.get(i).getGrupoRANs().get(j).getNombre());
                            alturaTorre.setValue((Object)listaNodosBActuales.get(k).getTorre().getAltura());
                            tiposTorre.setSelectedItem(listaNodosBActuales.get(k).getTorre().getTipoTorre());
                            
                            actualNombreRAN = proyectos.get(i).getGrupoRANs().get(j).getNombre();
                       }
                   }
                }
            }
        }
}//GEN-LAST:event_encontrarSitioActionPerformed

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner alturaTorre;
    private javax.swing.JButton botonAceptar;
    private javax.swing.JButton botonCanelar;
    private javax.swing.JToggleButton editarButton;
    private javax.swing.JButton encontrarSitio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JComboBox listaRANs;
    private javax.swing.JComboBox listaSitios;
    private javax.swing.JComboBox listaeNodeB;
    private javax.swing.JTextField nombreENodeB;
    private javax.swing.JComboBox tiposTorre;
    // End of variables declaration//GEN-END:variables
}
