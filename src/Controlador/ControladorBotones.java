/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Libreria.CentrarScreen;
import Libreria.FileChooserOpenPersonalizado;
import Libreria.FileChooserSavePersonalizado;
import Modelo.PRO.Proyecto;
import Modelo.RAN.ENodeB;
import Modelo.RAN.Radio;
import Modelo.SIM.Outdoor.OutdoorLink;
import Modelo.SIM.Outdoor.OutdoorLinkRAN;
import Vista.general.About;
import Vista.ventanas.Configuracion.VentanaConfiguracionProjectBook;
import Vista.ventanas.EstacionesMovil.VentanaNuevoEquipoUsuario;
import Vista.ventanas.Sitio.VentanaEditarSitio;
import Vista.ventanas.Proyecto.VentanaEliminarProyecto;
import Vista.ventanas.RAN.RAN.VentanaEliminarRAN;
import Vista.ventanas.Sitio.VentanaEliminarSitio;
import Vista.ventanas.Proyecto.VentanaNuevoProyecto;
import Vista.ventanas.Proyecto.VentanaEstablecerProyecto;
import Vista.ventanas.RAN.Components.indoorLink.VentanaGestionIndoorLinks;
import Vista.ventanas.RAN.Components.antenas.VentanaGestionAntenas;
import Vista.ventanas.RAN.Components.radios.VentanaGestionEquiposRadio;
import Vista.ventanas.RAN.Components.atenuadores.VentanaGestionElementosAtenuacion;
import Vista.ventanas.RAN.Components.radios.interfacesRadio.VentanaGestionTarjetasRadio;
import Vista.ventanas.RAN.ENodeB.VentanaEditarENodeB;
import Vista.ventanas.RAN.ENodeB.VentanaEliminarENodeB;
import Vista.ventanas.RAN.RAN.VentanaEditarRAN;
import Vista.ventanas.RAN.RAN.VentanaNuevaRAN;
import Vista.ventanas.RAN.ENodeB.VentanaNuevoENodeB;
import Vista.ventanas.RAN.OutdoorLink.VentanaNuevoOutdoorLink;
import Vista.ventanas.Simulacion.VentanaConfiguracionSimulacion;
import Vista.ventanas.Simulacion.VentanaRunSimulacion;
import Vista.ventanas.Sitio.VentanaNuevoSitio;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Usuario
 */
public class ControladorBotones {
    
    private ControladorGeneral controlador;
   
    public ControladorBotones(ControladorGeneral controlador){
        this.controlador = controlador;
    }
    
    public void crearVentanaNuevoProyecto(){
        VentanaNuevoProyecto ventanaNuevoProyecto = new VentanaNuevoProyecto(new Frame(), true, controlador);
        CentrarScreen.centrarVentana(ventanaNuevoProyecto);
        ventanaNuevoProyecto.setVisible(true);
    }
    
    public void crearVentanaEstablecerProyectoPredeterminado(){
        VentanaEstablecerProyecto ventana = new VentanaEstablecerProyecto(new JFrame(), true, controlador);
        ventana.setSize(525, 210);
        CentrarScreen.centrarVentana(ventana);
        ventana.setVisible(true);
    }
    
    public void crearVentanaConfiguracionProjectBook(){
        VentanaConfiguracionProjectBook ventanaPB = new VentanaConfiguracionProjectBook(new JFrame(), true, controlador);
    }

    public void nuevaVentanaNuevoSitio(){
        VentanaNuevoSitio ventana = new VentanaNuevoSitio(new JFrame(), true, controlador);
    }
    
    public void crearVentanaEliminarProyecto(){
        VentanaEliminarProyecto ventana = new VentanaEliminarProyecto(new JFrame(), true, controlador);
    }
    
    public void crearVentanaEliminarSitio(){
        VentanaEliminarSitio ventana = new VentanaEliminarSitio(new JFrame(), true, controlador);
        CentrarScreen.centrarVentana(ventana);
        ventana.setVisible(true);
    }
    
    public void crearVentanaEditarSitio(){
        VentanaEditarSitio ventana = new VentanaEditarSitio(new JFrame(), true, controlador);
        CentrarScreen.centrarVentana(ventana);
        ventana.setVisible(true);
    }
    
    public void crearVentanaNuevoRAN(){
         VentanaNuevaRAN ventana = new VentanaNuevaRAN(new JFrame(), true, controlador);
         CentrarScreen.centrarVentana(ventana);
         ventana.setVisible(true);
    }

    public void crearVentanaEliminarRAN() {
         VentanaEliminarRAN ventana = new VentanaEliminarRAN(new JFrame(), true, controlador);
         CentrarScreen.centrarVentana(ventana);
         ventana.setVisible(true);
    }
    
    public void crearVentanaEditarRAN(){
         VentanaEditarRAN ventana = new VentanaEditarRAN(new JFrame(), true, controlador);
         CentrarScreen.centrarVentana(ventana);
         ventana.setVisible(true);
    }

    public void crearVentanaNuevoNodeB() {
         VentanaNuevoENodeB ventana = new VentanaNuevoENodeB(new JFrame(), true, controlador);
         CentrarScreen.centrarVentana(ventana);
         ventana.setVisible(true);
    }
    
    public void crearVentanaEditarNodoB(){
         VentanaEditarENodeB ventana = new VentanaEditarENodeB(new JFrame(), true, controlador);
         CentrarScreen.centrarVentana(ventana);
         ventana.setVisible(true);
    }
    
    public void crearVentanaEliminarNodoB(){
         VentanaEliminarENodeB ventana = new VentanaEliminarENodeB(new JFrame(), true, controlador);
         CentrarScreen.centrarVentana(ventana);
         ventana.setVisible(true);
    }
    
    public void actualizarGestionAntenas(String nombreRAN, String nombreENodeB) {
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        for(int i=0; i < proyectos.size(); i++){
            //Si el proyecto es predeterminado
             if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                 for(int j=0; j < proyectos.get(i).getGrupoRANs().size(); j++){
                     //Si el proyecto es la RAN Seleccionada
                     if(proyectos.get(i).getGrupoRANs().get(j).getNombre().equals(nombreRAN)){
                         LinkedList <ENodeB> listaNodos = proyectos.get(i).getGrupoRANs().get(j).getGrupoNodosB();
                         for(int k=0; k < listaNodos.size(); k++){
                             //Si es el Nodo Seleccionado
                             if(listaNodos.get(k).getNombre().equals(nombreENodeB)){
                                ventanaGestionAntena.actualizarTablaAntenas(listaNodos.get(k).getAntenas());
                             } 
                         }
                     }
                 }
             }
        }
    }
    
    public void actualizarGestionRadios(String nombreRAN, String nombreENodeB) {
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        for(int i=0; i < proyectos.size(); i++){
            //Si el proyecto es predeterminado
             if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                 for(int j=0; j < proyectos.get(i).getGrupoRANs().size(); j++){
                     //Si el proyecto es la RAN Seleccionada
                     if(proyectos.get(i).getGrupoRANs().get(j).getNombre().equals(nombreRAN)){
                         LinkedList <ENodeB> listaNodos = proyectos.get(i).getGrupoRANs().get(j).getGrupoNodosB();
                         for(int k=0; k < listaNodos.size(); k++){
                             //Si es el Nodo Seleccionado
                             if(listaNodos.get(k).getNombre().equals(nombreENodeB)){
                                ventanaGestionEquiposRadio.actualizarTablaRadios(listaNodos.get(k).getRadios());
                             } 
                         }
                     }
                 }
             }
        }
    }
    
    public void actualizarGestionTargetasDeEquipos(String nombreRAN, String nombreENodeB, String nombreEquipo) {
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        for(int i=0; i < proyectos.size(); i++){
            //Si el proyecto es predeterminado
             if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                 for(int j=0; j < proyectos.get(i).getGrupoRANs().size(); j++){
                     //Si el proyecto es la RAN Seleccionada
                     if(proyectos.get(i).getGrupoRANs().get(j).getNombre().equals(nombreRAN)){
                         LinkedList <ENodeB> listaNodos = proyectos.get(i).getGrupoRANs().get(j).getGrupoNodosB();
                         for(int k=0; k < listaNodos.size(); k++){
                             //Si es el Nodo Seleccionado
                             if(listaNodos.get(k).getNombre().equals(nombreENodeB)){
                                 LinkedList <Radio> radios= listaNodos.get(k).getRadios();
                                 for(int l=0; l < radios.size(); l++){
                                    if(radios.get(l).getNombre().equals(nombreEquipo)){
                                      ventanaGestionTargetas.actualizarTablaTarjetasRadio(radios.get(l).obtenerTargetasRadioSlotOcupados());
                                      ventanaGestionTargetas.actualizarPanelTargetas(radios.get(l).obtenerTargetasRadioSlotOcupados(), radios.get(l).obtenerTargetasRadioSlotVacios());
                                    }
                                 }
                             } 
                         }
                     }
                 }
             }
        }
    }
    
    public void actualizarGestionElementosAtenuacion(String nombreRAN, String nombreENodeB) {
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        for(int i=0; i < proyectos.size(); i++){
            //Si el proyecto es predeterminado
             if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                 for(int j=0; j < proyectos.get(i).getGrupoRANs().size(); j++){
                     //Si el proyecto es la RAN Seleccionada
                     if(proyectos.get(i).getGrupoRANs().get(j).getNombre().equals(nombreRAN)){
                         LinkedList <ENodeB> listaNodos = proyectos.get(i).getGrupoRANs().get(j).getGrupoNodosB();
                         for(int k=0; k < listaNodos.size(); k++){
                             //Si es el Nodo Seleccionado
                             if(listaNodos.get(k).getNombre().equals(nombreENodeB)){
                                ventanaGestionDispositivosAtenuacion.actualizarTablaAtenuadores(listaNodos.get(k).getAtenuadores());
                             }
                         }
                     }
                 }
             }
         }
    }
    
    public void actualizarGestionIndoorLink(String nombreRAN, String nombreENodeB) {
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        for(int i=0; i < proyectos.size(); i++){
            //Si el proyecto es predeterminado
             if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                 for(int j=0; j < proyectos.get(i).getGrupoRANs().size(); j++){
                     //Si el proyecto es la RAN Seleccionada
                     if(proyectos.get(i).getGrupoRANs().get(j).getNombre().equals(nombreRAN)){
                         LinkedList <ENodeB> listaNodos = proyectos.get(i).getGrupoRANs().get(j).getGrupoNodosB();
                         for(int k=0; k < listaNodos.size(); k++){
                             //Si es el Nodo Seleccionado
                             if(listaNodos.get(k).getNombre().equals(nombreENodeB)){
                                ventanaGestionIndoorLinks.actualizarTablaIndoorLink(listaNodos.get(k).getIndoorLinks());
                             }
                         }
                     }
                 }
             }
         }
    }
    
    public void actualizarPathsConexionOutdoorPredeterminada() {
        
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        
        for(int i=0; i < proyectos.size(); i++){
            //Si el proyecto es predeterminado
             if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                 OutdoorLinkRAN link = (OutdoorLinkRAN)proyectos.get(i).getSimuladorPropagacion().getOutdoorLinkPredeterminado(); 
                 ventanaSim.actualizarTablaPaths(link.getListaPaths());
                 System.out.println("Test Impresion Paths");
             }
        }
        
    }
    
    public void crearVenatanaEU() {
        VentanaNuevoEquipoUsuario ventana = new VentanaNuevoEquipoUsuario(new JFrame(), true, controlador);
        CentrarScreen.centrarVentana(ventana);
        ventana.setVisible(true);
    }
    
    private VentanaGestionAntenas ventanaGestionAntena;
    public void crearVenatanGestionDeAntenas(){
        ventanaGestionAntena = new VentanaGestionAntenas(new JFrame(), true, controlador);
        CentrarScreen.centrarVentana(ventanaGestionAntena);
        ventanaGestionAntena.setVisible(true);
    }
    
    private VentanaGestionEquiposRadio ventanaGestionEquiposRadio;
    public void crearVentanaGestionEquiposRadio(){
        ventanaGestionEquiposRadio = new VentanaGestionEquiposRadio(new JFrame(), true, controlador);
        CentrarScreen.centrarVentana(ventanaGestionEquiposRadio);
        ventanaGestionEquiposRadio.setVisible(true);
    }
    
    private VentanaGestionTarjetasRadio ventanaGestionTargetas;
    public void crearVentanaGestionTargetas(ControladorGeneral controlador, String nombreRAN, String nombreENodeB, String nombreEquipoRadio){
        ventanaGestionTargetas = new VentanaGestionTarjetasRadio(new JFrame(), true, controlador, nombreRAN, nombreENodeB, nombreEquipoRadio);
        CentrarScreen.centrarVentana(ventanaGestionTargetas);
        actualizarGestionTargetasDeEquipos(nombreRAN, nombreENodeB, nombreEquipoRadio);
        ventanaGestionTargetas.setVisible(true);
        
    }
        
    private VentanaGestionElementosAtenuacion ventanaGestionDispositivosAtenuacion;
    public void crearVenatanaGestionAtenuadores(){
        ventanaGestionDispositivosAtenuacion = new VentanaGestionElementosAtenuacion(new JFrame(), true, controlador);
        CentrarScreen.centrarVentana(ventanaGestionDispositivosAtenuacion);
        ventanaGestionDispositivosAtenuacion.setVisible(true);
    }

    private VentanaGestionIndoorLinks ventanaGestionIndoorLinks;
    public void crearVenatanaGestionIndoorLink() {
        ventanaGestionIndoorLinks = new VentanaGestionIndoorLinks(new JFrame(), true, controlador);
        CentrarScreen.centrarVentana(ventanaGestionIndoorLinks);
        ventanaGestionIndoorLinks.setVisible(true);
    }

    public void crearVentanaNuevoEnlaceOutdoor() {
        VentanaNuevoOutdoorLink ventana = new VentanaNuevoOutdoorLink(new JFrame(), true, controlador);
        CentrarScreen.centrarVentana(ventana);
        ventana.setVisible(true);
    }

    private VentanaConfiguracionSimulacion ventanaSim;
    public void crearVentanaConfiguracionSimulacion() {
        ventanaSim = new VentanaConfiguracionSimulacion(new JFrame(), true, controlador);
        CentrarScreen.centrarVentana(ventanaSim);
        ventanaSim.setVisible(true);
    }
    
    public void crearVentanaAbout() {
         About ventana = new About(new JFrame(), true);
         CentrarScreen.centrarVentana(ventana);
         ventana.setVisible(true);
    }

    public void guardarProyecto() {
        
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        if(proyectos.size() > 0){
            for(int i=0; i < proyectos.size(); i++){
                if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                   if(proyectos.get(i).getURLProyecto().equals("")){
                        FileChooserSavePersonalizado fileChooser = new FileChooserSavePersonalizado(new Frame(), true, controlador, proyectos.get(i));
                   }else{
                       controlador.getLibroDeProyectos().guardarProyectoPredeterminado(proyectos.get(i), proyectos.get(i).getURLProyecto());
                   }
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "'El proyecto predeterminado no existe, o no hay ningun proyecto en el Project Book'", "Error 028: Sin Proyecto Asociado", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void guardarProyectoComo() {
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        for(int i=0; i < proyectos.size(); i++){
            if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                FileChooserSavePersonalizado fileChooser = new FileChooserSavePersonalizado(new Frame(), true, controlador, proyectos.get(i));
            }
        }
    }
    
    public void abrirProyecto(){
        FileChooserOpenPersonalizado fileChooserOpen = new FileChooserOpenPersonalizado(new Frame(), true, controlador);
    }
    
}
