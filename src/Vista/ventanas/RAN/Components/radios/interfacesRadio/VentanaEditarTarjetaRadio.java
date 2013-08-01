/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VentanaNuevoRadio.java
 *
 * Created on 29-sep-2011, 22:34:55
 */
package Vista.ventanas.RAN.Components.radios.interfacesRadio;

import Vista.ventanas.RAN.Components.radios.*;
import Controlador.ControladorGeneral;
import Modelo.PRO.Proyecto;
import Modelo.RAN.ENodeB;
import Modelo.RAN.Radio;
import Modelo.RAN.RatioAccessNetwork_RAN;
import Modelo.RAN.TarjetaRadio;
import java.util.LinkedList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

/**
 *
 * @author User
 */
public class VentanaEditarTarjetaRadio extends javax.swing.JDialog {

    private ControladorGeneral controlador;
    private String nombreRAN, nombreENodeB, nombreRadio;
    private int numeroSlotActual;
    
    /** Creates new form VentanaNuevoRadio */
    public VentanaEditarTarjetaRadio(java.awt.Frame parent, boolean modal, ControladorGeneral controlador, String nombreRAN, 
            String nombreENodeB, String nombreRadio, String numeroSlotActual) {
        super(parent, modal);
        this.controlador = controlador;
        this.nombreENodeB = nombreENodeB;
        this.nombreRAN = nombreRAN;
        this.nombreRadio = nombreRadio;
        this.numeroSlotActual = Integer.parseInt(numeroSlotActual);
        initComponents();
        
        
        tecnologia.setModel(new DefaultComboBoxModel(RatioAccessNetwork_RAN.TECNOLOGIAS));
        fabricante.setModel(new DefaultComboBoxModel(Radio.getFabricantes()));
        tipoTargeta.setModel(new DefaultComboBoxModel(TarjetaRadio.getTIPOS_TARJETAS_RADIO()));
        modulacion.setModel(new DefaultComboBoxModel(TarjetaRadio.getMODULACIONES()));
        tecnologiaMultiplexacion.setModel(new DefaultComboBoxModel(TarjetaRadio.getTECNOLOGIAS_MULTIPLEXACION()));
        duplexMode.setModel(new DefaultComboBoxModel(TarjetaRadio.getDUPLEX_MODES()));
        bandaFrecuencia.setModel(new DefaultComboBoxModel(TarjetaRadio.getBANDAS_DE_FRECUENCIA_FDD()));
        
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        for(int i=0; i < proyectos.size(); i++){
            //si el proyecto es predeterminado
             if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                 for(int j=0; j < proyectos.get(i).getGrupoRANs().size(); j++){
                     if(proyectos.get(i).getGrupoRANs().get(j).getNombre().equals(nombreRAN)){
                         LinkedList <ENodeB> listaNodos = proyectos.get(i).getGrupoRANs().get(j).getGrupoNodosB();
                         tecnologia.setSelectedItem(proyectos.get(i).getGrupoRANs().get(i).getTecnologia());
                         for(int k=0; k < listaNodos.size(); k++){
                             if(listaNodos.get(k).getNombre().equals(nombreENodeB)){
                                 LinkedList <Radio> radios= listaNodos.get(k).getRadios();
                                 for(int l=0; l < radios.size(); l++){
                                    if(radios.get(l).getNombre().equals(nombreRadio)){
                                        fabricante.setSelectedItem(radios.get(l).getFabricante());
                                        numeroSlot.setModel(new DefaultComboBoxModel(radios.get(l).obtenerTargetasRadioSlotVaciosConActual(this.numeroSlotActual)));
                                        
                                        TarjetaRadio listaTargetas[] = radios.get(l).getInterfacesRadio();
                                        
                                        numeroSlot.setSelectedItem("" + this.numeroSlotActual);
                                        fabricante.setSelectedItem(listaTargetas[this.numeroSlotActual].getFabricante());
                                        tecnologia.setSelectedItem(listaTargetas[this.numeroSlotActual].getTecnologia());
                                        tipoTargeta.setSelectedItem(listaTargetas[this.numeroSlotActual].getTipoTargeta());
                                        modulacion.setSelectedItem(listaTargetas[this.numeroSlotActual].getModulacion());
                                        tecnologiaMultiplexacion.setSelectedItem(listaTargetas[this.numeroSlotActual].getTecnologiaMultiplexacion());
                                        potenciaMax.setValue(listaTargetas[this.numeroSlotActual].getPotenciaMaximaTX());
                                        sensivilidadMax.setValue(listaTargetas[this.numeroSlotActual].getSensibilidad());
                                        duplexMode.setSelectedItem(listaTargetas[this.numeroSlotActual].getDuplexMode());
                                        bandaFrecuencia.setSelectedItem(listaTargetas[this.numeroSlotActual].getBandaFrecuencia());
                                        frecuenciaInferiorUpLink.setText("" + listaTargetas[this.numeroSlotActual].getFrecuenciaInferiorUpLink());
                                        frecuenciaSuperiorUpLink.setText("" + listaTargetas[this.numeroSlotActual].getFrecuenciaInferiorUpLink());
                                        anchoBandaUpLink.setText("" + listaTargetas[this.numeroSlotActual].getAnchoBandaUpLink());
                                        frecuenciaInferiorDownLink.setText("" + listaTargetas[this.numeroSlotActual].getFrecuenciaInferiorDownLink());
                                        frecuenciaSuperiorDownLink.setText("" + listaTargetas[this.numeroSlotActual].getFrecuenciaSuperiorDownLink());
                                        anchoBandaDownLink.setText("" + listaTargetas[this.numeroSlotActual].getAnchoBandaDownLink());
                                    }
                                 }
                             }
                         }
                     }
                 }
             }
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

        jPanel3 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        botonAceptar = new javax.swing.JButton();
        botonCanelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        fabricante = new javax.swing.JComboBox();
        numeroSlot = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tecnologia = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        modulacion = new javax.swing.JComboBox();
        tecnologiaMultiplexacion = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        tipoTargeta = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        potenciaMax = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        sensivilidadMax = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        bandaFrecuencia = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        duplexMode = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        frecuenciaInferiorDownLink = new javax.swing.JTextField();
        frecuenciaSuperiorDownLink = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        anchoBandaDownLink = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        frecuenciaInferiorUpLink = new javax.swing.JTextField();
        frecuenciaSuperiorUpLink = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        anchoBandaUpLink = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo Equipo de Radio");
        setIconImage(new ImageIcon("src/Images/iconos16x16/card-add.png").getImage());
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setName("jPanel3"); // NOI18N

        jSeparator2.setName("jSeparator2"); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos32x32/card-dit.png"))); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Asistente para la edicion de una Tarjeta de Radio existente");
        jLabel2.setName("jLabel2"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addContainerGap(236, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)))
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(367, Short.MAX_VALUE)
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion General"));
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel3.setText("Numero de Slot:");
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText("Fabricante:");
        jLabel4.setName("jLabel4"); // NOI18N

        fabricante.setEnabled(false);
        fabricante.setName("fabricante"); // NOI18N

        numeroSlot.setName("numeroSlot"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numeroSlot, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fabricante, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numeroSlot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fabricante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Panel Parametros Tecnicos"));
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel5.setText("Tecnologia:");
        jLabel5.setName("jLabel5"); // NOI18N

        tecnologia.setEnabled(false);
        tecnologia.setName("tecnologia"); // NOI18N

        jLabel6.setText("Modulacion:");
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel7.setText("Tecnologia de Multiplexacion: ");
        jLabel7.setName("jLabel7"); // NOI18N

        modulacion.setName("modulacion"); // NOI18N

        tecnologiaMultiplexacion.setName("tecnologiaMultiplexacion"); // NOI18N

        jLabel8.setText("Tipo de Targeta:");
        jLabel8.setName("jLabel8"); // NOI18N

        tipoTargeta.setName("tipoTargeta"); // NOI18N

        jLabel9.setText("Nivel de Potencia Max. Tx:");
        jLabel9.setName("jLabel9"); // NOI18N

        potenciaMax.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(20.0f), Float.valueOf(-50.0f), Float.valueOf(100.0f), Float.valueOf(0.5f)));
        potenciaMax.setName("potenciaMax"); // NOI18N

        jLabel10.setText("Sensibilididad Rx:");
        jLabel10.setName("jLabel10"); // NOI18N

        sensivilidadMax.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(-50.0f), Float.valueOf(-200.0f), Float.valueOf(0.0f), Float.valueOf(0.5f)));
        sensivilidadMax.setToolTipText("");
        sensivilidadMax.setName("sensivilidadMax"); // NOI18N

        jLabel11.setText("[dBm]");
        jLabel11.setName("jLabel11"); // NOI18N

        jLabel12.setText("[dBm]");
        jLabel12.setName("jLabel12"); // NOI18N

        jLabel13.setText("Banda de Frecuencias:");
        jLabel13.setName("jLabel13"); // NOI18N

        bandaFrecuencia.setName("bandaFrecuencia"); // NOI18N
        bandaFrecuencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bandaFrecuenciaActionPerformed(evt);
            }
        });

        jLabel14.setText("Tecnologia Modo Duplex:");
        jLabel14.setName("jLabel14"); // NOI18N

        duplexMode.setName("duplexMode"); // NOI18N
        duplexMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                duplexModeActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("DownLink"));
        jPanel5.setName("jPanel5"); // NOI18N

        jLabel19.setText("Hasta:");
        jLabel19.setName("jLabel19"); // NOI18N

        jLabel20.setText("Desde :");
        jLabel20.setName("jLabel20"); // NOI18N

        frecuenciaInferiorDownLink.setEnabled(false);
        frecuenciaInferiorDownLink.setName("frecuenciaInferiorDownLink"); // NOI18N

        frecuenciaSuperiorDownLink.setEnabled(false);
        frecuenciaSuperiorDownLink.setName("frecuenciaSuperiorDownLink"); // NOI18N

        jLabel21.setText("[MHz]");
        jLabel21.setName("jLabel21"); // NOI18N

        jLabel22.setText("[MHz]");
        jLabel22.setName("jLabel22"); // NOI18N

        jLabel25.setText("Ancho de Banda (Max) :");
        jLabel25.setName("jLabel25"); // NOI18N

        anchoBandaDownLink.setEnabled(false);
        anchoBandaDownLink.setName("anchoBandaDownLink"); // NOI18N

        jLabel26.setText("[MHz]");
        jLabel26.setName("jLabel26"); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(frecuenciaSuperiorDownLink, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(frecuenciaInferiorDownLink, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22))
                        .addGap(24, 24, 24))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(3, 3, 3)
                        .addComponent(anchoBandaDownLink, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(frecuenciaInferiorDownLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(frecuenciaSuperiorDownLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(anchoBandaDownLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("UpLink"));
        jPanel6.setName("jPanel6"); // NOI18N

        jLabel15.setText("Desde :");
        jLabel15.setName("jLabel15"); // NOI18N

        jLabel16.setText("Hasta :");
        jLabel16.setName("jLabel16"); // NOI18N

        frecuenciaInferiorUpLink.setEnabled(false);
        frecuenciaInferiorUpLink.setName("frecuenciaInferiorUpLink"); // NOI18N

        frecuenciaSuperiorUpLink.setEnabled(false);
        frecuenciaSuperiorUpLink.setName("frecuenciaSuperiorUpLink"); // NOI18N

        jLabel17.setText("[MHz]");
        jLabel17.setName("jLabel17"); // NOI18N

        jLabel18.setText("[MHz]");
        jLabel18.setName("jLabel18"); // NOI18N

        anchoBandaUpLink.setEnabled(false);
        anchoBandaUpLink.setName("anchoBandaUpLink"); // NOI18N

        jLabel23.setText("Ancho de Banda (Max) :");
        jLabel23.setName("jLabel23"); // NOI18N

        jLabel24.setText("[MHz]");
        jLabel24.setName("jLabel24"); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(3, 3, 3)
                        .addComponent(anchoBandaUpLink, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel24))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(frecuenciaInferiorUpLink))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(10, 10, 10)
                                .addComponent(frecuenciaSuperiorUpLink, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(frecuenciaInferiorUpLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(frecuenciaSuperiorUpLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(anchoBandaUpLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tecnologia, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tipoTargeta, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6)
                        .addGap(12, 12, 12)
                        .addComponent(modulacion, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addGap(9, 9, 9)
                        .addComponent(tecnologiaMultiplexacion, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel9)
                        .addGap(6, 6, 6)
                        .addComponent(potenciaMax, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jLabel11)
                        .addGap(62, 62, 62)
                        .addComponent(jLabel10)
                        .addGap(7, 7, 7)
                        .addComponent(sensivilidadMax, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel12))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel14)
                        .addGap(10, 10, 10)
                        .addComponent(duplexMode, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel13)
                        .addGap(11, 11, 11)
                        .addComponent(bandaFrecuencia, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipoTargeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tecnologia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(modulacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(tecnologiaMultiplexacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(potenciaMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(sensivilidadMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(duplexMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bandaFrecuencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        
        controlador.getControladorVentanas().editarTargetaEquipoRadio(nombreRAN, nombreENodeB, nombreRadio, numeroSlotActual, Integer.parseInt(numeroSlot.getSelectedItem().toString()), 
                fabricante.getSelectedItem().toString(), tecnologia.getSelectedItem().toString(), tipoTargeta.getSelectedItem().toString(), modulacion.getSelectedItem().toString(),
                tecnologiaMultiplexacion.getSelectedItem().toString(), Double.parseDouble(potenciaMax.getValue().toString()), Double.parseDouble(sensivilidadMax.getValue().toString()), 
                duplexMode.getSelectedItem().toString(), bandaFrecuencia.getSelectedItem().toString(), Double.parseDouble(frecuenciaInferiorUpLink.getText()), Double.parseDouble(frecuenciaSuperiorUpLink.getText()),
                Double.parseDouble(anchoBandaUpLink.getText()), Double.parseDouble(frecuenciaInferiorDownLink.getText()), Double.parseDouble(frecuenciaSuperiorDownLink.getText()),
                Double.parseDouble(anchoBandaDownLink.getText()));
        this.dispose();
}//GEN-LAST:event_botonAceptarActionPerformed

private void botonCanelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCanelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
}//GEN-LAST:event_botonCanelarActionPerformed

private void duplexModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_duplexModeActionPerformed
// TODO add your handling code here:
    if(duplexMode.getSelectedItem().toString().equals("FDD")){
        bandaFrecuencia.setModel(new DefaultComboBoxModel(TarjetaRadio.getBANDAS_DE_FRECUENCIA_FDD()));
    }else if(duplexMode.getSelectedItem().toString().equals("TDD")){
          bandaFrecuencia.setModel(new DefaultComboBoxModel(TarjetaRadio.getBANDAS_DE_FRECUENCIA_TDD()));
     }
}//GEN-LAST:event_duplexModeActionPerformed

private void bandaFrecuenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bandaFrecuenciaActionPerformed
 
    if(duplexMode.getSelectedItem().toString().equals("FDD")){
        for(int i=0; i < TarjetaRadio.getBANDAS_DE_FRECUENCIA_FDD().length; i++){
            if( TarjetaRadio.getBANDAS_DE_FRECUENCIA_FDD()[i].equals(bandaFrecuencia.getSelectedItem().toString())){
                frecuenciaInferiorUpLink.setText(""+TarjetaRadio.getFRECUANCIA_INFERIOR_UPLINK()[i]);
                frecuenciaSuperiorUpLink.setText(""+TarjetaRadio.getFRECUANCIA_SUPERIOR_UPLINK()[i]);
                frecuenciaInferiorDownLink.setText(""+TarjetaRadio.getFRECUANCIA_INFERIOR_DOWNLINK()[i]);
                frecuenciaSuperiorDownLink.setText(""+TarjetaRadio.getFRECUANCIA_SUPERIOR_DOWNLINK()[i]);
                
                anchoBandaUpLink.setText("" + ((TarjetaRadio.getFRECUANCIA_SUPERIOR_UPLINK()[i])-(TarjetaRadio.getFRECUANCIA_INFERIOR_UPLINK()[i])));
                anchoBandaDownLink.setText("" + ((TarjetaRadio.getFRECUANCIA_SUPERIOR_DOWNLINK()[i])-(TarjetaRadio.getFRECUANCIA_INFERIOR_DOWNLINK()[i])));
            }
        }
    }
    else if(duplexMode.getSelectedItem().toString().equals("TDD")){
          for(int i=0; i < TarjetaRadio.getBANDAS_DE_FRECUENCIA_TDD().length; i++){
            if( TarjetaRadio.getBANDAS_DE_FRECUENCIA_TDD()[i].equals(bandaFrecuencia.getSelectedItem().toString())){
                frecuenciaInferiorUpLink.setText(""+TarjetaRadio.getFRECUANCIA_INFERIOR_ALLOCATION_TDD()[i]);
                frecuenciaSuperiorUpLink.setText(""+TarjetaRadio.getFRECUANCIA_SUPERIOR_ALLOCATION_TDD()[i]);
                frecuenciaInferiorDownLink.setText(""+TarjetaRadio.getFRECUANCIA_INFERIOR_ALLOCATION_TDD()[i]);
                frecuenciaSuperiorDownLink.setText(""+TarjetaRadio.getFRECUANCIA_SUPERIOR_ALLOCATION_TDD()[i]);
                
                anchoBandaUpLink.setText("" + ((TarjetaRadio.getFRECUANCIA_SUPERIOR_UPLINK()[i])-(TarjetaRadio.getFRECUANCIA_INFERIOR_UPLINK()[i])));
                anchoBandaDownLink.setText("" + ((TarjetaRadio.getFRECUANCIA_SUPERIOR_DOWNLINK()[i])-(TarjetaRadio.getFRECUANCIA_INFERIOR_DOWNLINK()[i])));
            }
        }
     }
    
}//GEN-LAST:event_bandaFrecuenciaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField anchoBandaDownLink;
    private javax.swing.JTextField anchoBandaUpLink;
    private javax.swing.JComboBox bandaFrecuencia;
    private javax.swing.JButton botonAceptar;
    private javax.swing.JButton botonCanelar;
    private javax.swing.JComboBox duplexMode;
    private javax.swing.JComboBox fabricante;
    private javax.swing.JTextField frecuenciaInferiorDownLink;
    private javax.swing.JTextField frecuenciaInferiorUpLink;
    private javax.swing.JTextField frecuenciaSuperiorDownLink;
    private javax.swing.JTextField frecuenciaSuperiorUpLink;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JComboBox modulacion;
    private javax.swing.JComboBox numeroSlot;
    private javax.swing.JSpinner potenciaMax;
    private javax.swing.JSpinner sensivilidadMax;
    private javax.swing.JComboBox tecnologia;
    private javax.swing.JComboBox tecnologiaMultiplexacion;
    private javax.swing.JComboBox tipoTargeta;
    // End of variables declaration//GEN-END:variables
}
